package com.example.seekingdevelopers.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String language;

    @ManyToMany(mappedBy = "languages")
    private List<User> users;

    public Language(long id, String language, List<User> users) {
        this.id = id;
        this.language = language;
        this.users = users;
    }

    public Language(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
