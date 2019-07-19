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
        return "users/users-search-list";
    }

    @GetMapping("/users/{id}/profile")
    public String singleProject(@PathVariable long id, Model model){
        User user = userDao.findOne(id);
        model.addAttribute("user", user);
        List<Language> langs = new ArrayList<>();
        langs = user.getLanguage();
        model.addAttribute("langs",langs);
        Project lastProject = projectDao.findDistinctTopByCreatorOrderByCreatingDateDesc(user);
        model.addAttribute("lastProject",lastProject);
        ArrayList<Project> projects = projectDao.findAllByCreatorOrderByCreatingDate(user);
        model.addAttribute("projects", projects);
        return "users/single-user";
    }
    @PostMapping("/users/{id}/profile")
    public String singleProject(@PathVariable long id){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        List<User> userList = new ArrayList<>();
        User user = userDao.findOne(id);
        userList.add(user);
        currentUser.setFavorite_users(userList);

        return "users/single-user";
    }


}
