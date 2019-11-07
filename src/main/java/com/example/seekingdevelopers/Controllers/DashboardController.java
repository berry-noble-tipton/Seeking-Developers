package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.Dev_typeRepository;
import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.Services.EmailService;
import com.example.seekingdevelopers.models.Dev_type;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DashboardController {

    private final ProjectRepository projectDao;
    private final UserRepository userDao;
    private final EmailService emailService;
    private final Dev_typeRepository devDao;


    public DashboardController(ProjectRepository projectDao, UserRepository userDao, EmailService emailService, Dev_typeRepository devDao){
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.emailService = emailService;
        this.devDao = devDao;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        ArrayList<Project> listOfProjects = projectDao.findAllByisCompleteFalseOrderByCreatingDateDesc();
        Dev_type dev_typeFrontEnd = devDao.findOne((long) 1);
        ArrayList<Project> frontEndProjects = new ArrayList<>();
        ArrayList<Project> backEndProjects = new ArrayList<>();
        for (Project project: listOfProjects) {
            if(project.getDev_type() == dev_typeFrontEnd && frontEndProjects.size() <= 2){
                frontEndProjects.add(project);
            } if(backEndProjects.size()<= 2 && project.getDev_type() != dev_typeFrontEnd) {
                backEndProjects.add(project);
            }
        }

        model.addAttribute("frontEndProjects", frontEndProjects);
        model.addAttribute("backEndProjects", backEndProjects);

        return "projects/dashboard-foundation";
    }

    @PostMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(name = "joinProject") Long joinId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findOne(user.getId());
        Project project = projectDao.findDistinctById(joinId);
        ArrayList<Project> listOfProjects = projectDao.findAllByisCompleteFalseOrderByCreatingDateDesc();
        model.addAttribute("listOfProjects", listOfProjects);
        emailService.prepareAndSend(project, loggedInUser.getUsername() + " is applying.", "They want to help you on " + project.getTitle() + "they are a " + loggedInUser.getDev_type().getTitle() + " developer. You can email them at " + loggedInUser.getEmail());
        return "projects/dashboard";
    }


    @GetMapping("/dashboard/viewall")
    public String viewAll(Model model, @RequestParam(name = "viewAll") Long viewAll){

        ArrayList<Project> listOfProjects = projectDao.findAllByisCompleteFalseOrderByCreatingDateDesc();
        Dev_type dev_typeFrontEnd = devDao.findOne((long) 1);
        ArrayList<Project> frontEndProjects = new ArrayList<>();
        ArrayList<Project> backEndProjects = new ArrayList<>();
        for (Project project: listOfProjects) {
            if(project.getDev_type() == dev_typeFrontEnd && viewAll == 1){
                frontEndProjects.add(project);
            } if(viewAll == 2 && project.getDev_type() != dev_typeFrontEnd) {
                backEndProjects.add(project);
            }
        }

        model.addAttribute("frontEndProjects", frontEndProjects);
        model.addAttribute("backEndProjects", backEndProjects);




        return "projects/viewAll";
    }



}
