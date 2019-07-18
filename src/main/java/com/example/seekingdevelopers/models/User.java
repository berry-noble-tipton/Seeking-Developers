package com.example.seekingdevelopers.models;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @NotBlank(message = "Must have a username.")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Must have a password.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Must have an email.")
    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "Boolean default false")
    private boolean isAdmin;

    @Column
    private String photo;

    @Column
    private String github;

    @Column
    private String linkedin;

    @OneToMany(mappedBy = "creator")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name="dev_type_id")
    private Dev_type dev_type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_users",
            joinColumns={@JoinColumn(name = "picked_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "picking_user_id")}
    )
    private List<User> favorite_users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_projects",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="project_id")}
    )
    private List<Project> userProjects;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_languages",
            joinColumns={@JoinColumn(name="languages_id")},
            inverseJoinColumns = {@JoinColumn(name="users_id")}
    )
    private List<language> language;

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        language = copy.language;
    }


    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(){};

    public long getId(){return this.id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Dev_type getDev_type() {
        return dev_type;
    }

    public void setDev_type(Dev_type dev_type) {
        this.dev_type = dev_type;
    }

    public List<language> getLanguage() {
        return language;
    }

    public void setLanguage(List<language> language) {
        this.language = language;
    }
}
