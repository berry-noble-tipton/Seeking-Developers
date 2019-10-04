package com.example.seekingdevelopers.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dev_type")
public class Dev_type {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "dev_type")
    private List<Project> projectsList;

    @OneToMany(mappedBy = "dev_type")
    private List<User> userListList;

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

    public List<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    public List<User> getUserListList() {
        return userListList;
    }

    public void setUserListList(List<User> userListList) {
        this.userListList = userListList;
    }
}
