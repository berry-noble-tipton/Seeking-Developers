package com.example.seekingdevelopers.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @NotBlank(message = "Must have title")
    @Column(nullable = false)
    private String title;

    @NotBlank (message = "Project must have description")
    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern ="MM-dd-yyyy")
    private Date creatingDate;


    @Column(columnDefinition = "Boolean default false")
    private boolean isComplete;

    @Column
    private String github;

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;

    @ManyToMany(mappedBy = "userProjects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> contributors;

    @ManyToOne
    @JoinColumn(name="dev_type_id")
    private Dev_type dev_type;

    public Project(){};

    public Project(String title, String description, Dev_type dev_type, User creator){
        this.title = title;
        this.description = description;
        this.dev_type = dev_type;
        this.creator = creator;
    }

    public Project(String title, String description, Dev_type dev_type, User creator, List<User> contributors){
        this.title = title;
        this.description = description;
        this.dev_type = dev_type;
        this.creator = creator;
        this.contributors= contributors;
    }
    public Project(String title, String description, Dev_type dev_type, User creator, List<User> contributors, boolean isComplete, String github){
        this.title = title;
        this.description = description;
        this.dev_type = dev_type;
        this.creator = creator;
        this.contributors= contributors;
        this.isComplete = isComplete;
        this.github = github;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Dev_type getDev_type() {
        return dev_type;
    }

    public void setDev_type(Dev_type dev_type) {
        this.dev_type = dev_type;
    }

    public List<User> getContributors() {
        return contributors;
    }

    public void setContributors(List<User> contributors) {
        this.contributors = contributors;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
