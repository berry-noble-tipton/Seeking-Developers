package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Language;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private final ProjectRepository projectDao;
    private final UserRepository userDao;

    public SearchController(ProjectRepository projectDao, UserRepository userDao){
        this.projectDao = projectDao;
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public String searchUsers(Model model){
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        List<Project> projects = projectDao.findAll();
        model.addAttribute("projects", projects);
        return "users/users-search-list";
    }

    @PostMapping("/users")
    public String searchUsersAndPRojects(Model model, @RequestParam(name = "search") String search){
        List<User> users = userDao.findAllByUsernameContaining(search);
        model.addAttribute("users", users);
        List<Project> projects = projectDao.findAllByTitleContaining(search);
        model.addAttribute("projects", projects);
        return "users/users-search-list";
    }



    @GetMapping("/users/{id}/profile")
    public String singleUser(@PathVariable long id, Model model){
        User user = userDao.findOne(id);
        model.addAttribute("user", user);
        List<Language> langs = new ArrayList<>();
        List<User> favorite_users = user.getFavorite_users();
        model.addAttribute("favorite_users", favorite_users);
        langs = user.getLanguage();
        model.addAttribute("langs",langs);
        Project lastProject = projectDao.findDistinctTopByCreatorOrderByCreatingDateDesc(user);
        model.addAttribute("lastProject",lastProject);
        ArrayList<Project> projects = projectDao.findAllByCreatorOrderByCreatingDate(user);
        model.addAttribute("projects", projects);
        List<Project> completedProjectList = projectDao.findAllByCreatorAndIsCompleteTrue(user);
        model.addAttribute("completedProjects",completedProjectList);
        return "users/single-user";
    }
    @PostMapping("/users/{id}/profile")
    public String singleUser(@PathVariable long id){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        List<User> userList = currentUser.getFavorite_users();
        User user = userDao.findOne(id);
        for (User userCheck: userList) {
            if(user.getId() == currentUser.getId() || userCheck.getId() == user.getId()){
                return "redirect:/profile";
            }
        }
        userList.add(user);
        currentUser.setFavorite_users(userList);
        userDao.save(currentUser);
        return "redirect:/users";
    }


}
