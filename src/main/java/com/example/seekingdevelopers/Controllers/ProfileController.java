package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.Dev_typeRepository;
import com.example.seekingdevelopers.Repositories.LanguageRepository;
import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Dev_type;
import com.example.seekingdevelopers.models.Language;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private final ProjectRepository projectDao;
    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private LanguageRepository languageDao;
    final private Dev_typeRepository dev_typeDao;




    public ProfileController(ProjectRepository projectDao, UserRepository userDao, PasswordEncoder passwordEncoder, LanguageRepository languageDao, Dev_typeRepository dev_typeDao){
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.dev_typeDao = dev_typeDao;
        this.languageDao = languageDao;
    }


    @GetMapping("/profile")
    public String loggedInProfile(Model model){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        List<User> favorite_users = currentUser.getFavorite_users();
        model.addAttribute("user",currentUser);
        model.addAttribute("favorite_users", favorite_users);
        List<Language> langs = new ArrayList<>();
        langs = currentUser.getLanguage();
        model.addAttribute("langs",langs);
        Project lastProject = projectDao.findDistinctTopByCreatorOrderByCreatingDateDesc(loggedinUser);
        model.addAttribute("lastProject",lastProject);
        List<Project> completedProjectList = projectDao.findAllByCreatorAndIsCompleteTrue(loggedinUser);
       model.addAttribute("completedProjects",completedProjectList);
        ArrayList<Project> projects = projectDao.findAllByCreatorOrderByCreatingDate(loggedinUser);
        model.addAttribute("projects", projects);

        return "users/profile-foundation";
    }


    @PostMapping("/profile")
    public String uploadImage(@RequestParam(name="imageURL") String imageURL){
        System.out.println(imageURL);
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        currentUser.setPhoto(imageURL);
        userDao.save(currentUser);

        return "redirect:/profile";
    }

    @GetMapping("profile/edit")
    public String editProfile(Model model){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        model.addAttribute("user", currentUser);

        return "users/editProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@Valid User user, Errors validation, Model model, @RequestParam(name = "dev_type") Long id, @RequestParam(name = "languages",required = false)long[] languages){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        user.setId(currentUser.getId());
        user.setPhoto(currentUser.getPhoto());

        if(userDao.findByUsername(user.getUsername()) != null && !user.getUsername().equalsIgnoreCase(currentUser.getUsername())){
            validation.rejectValue("username",null, "Username is already in use");
        }
        if(validation.hasErrors()){
            model.addAttribute("errors",validation);
            return "users/signup";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        Dev_type dev_type = dev_typeDao.findOne(id);
        user.setDev_type(dev_type);
        user.setPassword(hash);
        user = userDao.save(user);
        if(languages != null) {
            List<Language> languageList = new ArrayList<>();
            for (long langId : languages) {
                Language language = languageDao.findOne(langId);
                languageList.add(language);
            }
            for (Language lang : languageList) {
                System.out.println(lang.getLanguage());
            }
            user.setLanguage(languageList);
        }

        userDao.save(user);
        return "redirect:/profile";

    }


}
