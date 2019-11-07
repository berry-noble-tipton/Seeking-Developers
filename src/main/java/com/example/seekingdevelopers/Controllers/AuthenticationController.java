package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.Dev_typeRepository;
import com.example.seekingdevelopers.Repositories.LanguageRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Dev_type;
import com.example.seekingdevelopers.models.Language;
import com.example.seekingdevelopers.models.User;
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
public class AuthenticationController {
    private UserRepository userDao;
    private LanguageRepository languageDao;
    private PasswordEncoder passwordEncoder;
    final private Dev_typeRepository dev_typeDao;


    public AuthenticationController(LanguageRepository languageDao,UserRepository userDao, PasswordEncoder passwordEncoder, Dev_typeRepository dev_typeDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.dev_typeDao = dev_typeDao;
        this.languageDao = languageDao;
    }


    @GetMapping("/login")
    public String showLoginForm(){
        return "users/login";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/signup-foundation";
    }
    @PostMapping("/signup")
    public String saveUser(@Valid User user, Errors validation, Model model, @RequestParam(name = "dev_type") Long id, @RequestParam(name = "language")long[] languages){
        if(userDao.findByUsername(user.getUsername()) != null ){
            validation.rejectValue("username",null, "Username is already in use");
        }
        if(validation.hasErrors()){
            model.addAttribute("errors",validation);
            return "users/signup-foundation";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        Dev_type dev_type = dev_typeDao.findOne(id);
        user.setDev_type(dev_type);
        user.setPassword(hash);
        user.setPhoto("https://cdn.filestackcontent.com/UrT1CLBVSYGCReZQ1nnA");
        user = userDao.save(user);
        List<Language> languageList = new ArrayList<>();
        for (long langId: languages) {
            System.out.println(user.getId());
            System.out.println(langId);
            Language language = languageDao.findOne(langId);
            languageList.add(language);
        }
        for (Language lang: languageList) {
            System.out.println(lang.getLanguage());
        }
        user.setLanguage(languageList);
        userDao.save(user);
        return "redirect:login";

    }

}
