package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.Dev_typeRepository;
import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Dev_type;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import com.example.seekingdevelopers.Services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {
    final private ProjectRepository projectDao;
    final private Dev_typeRepository dev_typeDao;
    private final EmailService emailService;
    private final UserRepository userDao;

    public ProjectController(ProjectRepository projectDao, Dev_typeRepository dev_typeDao, EmailService emailService, UserRepository userDao){
        this.projectDao = projectDao;
        this.dev_typeDao = dev_typeDao;
        this.emailService = emailService;
        this.userDao = userDao;
    }

    @GetMapping("/projects/create")
    public String create(Model model){
        model.addAttribute("project", new Project());
        return "projects/create-foundation";
    }
    @PostMapping("/projects/create")
    public String create(@Valid Project project, Errors validation , Model model, @RequestParam(name = "helpNeeded") Long id){
        if(validation.hasErrors()){
            model.addAttribute("errors",validation);
            return "projects/create-foundation";
        }
        User creator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dev_type dev_type = dev_typeDao.findOne(id);
        project.setDev_type(dev_type);
        project.setCreator(creator);
        projectDao.save(project);
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{id}/single-project")
    public String singleProject(@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findOne(user.getId());
        Project singleProject = projectDao.findDistinctById(id);
        List<User> contributors =  singleProject.getContributors();
        model.addAttribute("currentUser",loggedInUser);
        model.addAttribute("contributors", contributors);
        model.addAttribute("project", singleProject);
        return "projects/single-project-foundation";
    }

    @PostMapping("/projects/{id}/single-project")
    public String contribute(@PathVariable long id, @RequestParam(name = "contributor") String contributor){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findOne(user.getId());
        User userContributor = userDao.findByUsername(contributor);
        Project singleProject = projectDao.findDistinctById(id);
        List<Project> projects = userContributor.getUserProjects();
        for (Project project: projects) {
            if(project.getId() == singleProject.getId()){
                return "redirect:/dashboard";
            }
            
        }
        projects.add(singleProject);
        userContributor.setUserProjects(projects);
        userDao.save(userContributor);
        emailService.prepareAndSend(singleProject, loggedInUser.getUsername() + " added you as a contributor to " + singleProject.getTitle() , "For more information you can email them at " + loggedInUser.getEmail());
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{id}/single-project/edit")
    public String editProject(@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project editProject = projectDao.findDistinctById(id);
        if(user.getId() != editProject.getCreator().getId()){
            return "redirect:/dashboard";
        }
        model.addAttribute("project", editProject);
        return "projects/editProject-foundation";
    }

    @PostMapping("/projects/{id}/single-project/edit")
    public String editProject(@ModelAttribute Project project, @RequestParam(name = "isComplete") boolean isComplete) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getId() != project.getCreator().getId()){
            return "redirect:/dashboard";
        }
        project.setComplete(isComplete);
        projectDao.save(project);
        if(project.isComplete()){
            return "redirect:/projects/"+project.getId() +"/complete";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("projects/delete")
    public String deleteProject(Model model){
        return "projects/delete";
    }
    @PostMapping("projects/delete")
    public String deleteProject(@RequestParam(name = "projectId") Long projectId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project project = projectDao.findDistinctById(projectId);
        if(user.getId() != project.getCreator().getId()){
            return "redirect:/dashboard";
        }
        projectDao.delete(projectId);
        return "projects/delete";
    }

    @GetMapping("projects/{id}/complete")
    public String completeProject(@PathVariable Long id, Model model){
       Project project = projectDao.findDistinctById(id);
        model.addAttribute("project", project);
        return "projects/complete";
    }
    @PostMapping("projects/{id}/complete")
    public String completeProject(@PathVariable Long id, @RequestParam(name= "git")String link){
        //TODO: Add github link to SQL database
        Project project = projectDao.findDistinctById(id);
        System.out.println(link);
        project.setGithub(link);
        projectDao.save(project);
        return "redirect:/profile";

    }
}

