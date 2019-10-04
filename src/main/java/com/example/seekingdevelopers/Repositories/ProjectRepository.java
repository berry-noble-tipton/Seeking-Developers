package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findDistinctTopByCreatorOrderByCreatingDateDesc(User user);

     ArrayList<Project> findAllByCreatorOrderByCreatingDate(User user);

     ArrayList<Project> findAllByisCompleteFalseOrderByCreatingDateDesc();

     ArrayList<Project> findAllByTitleContaining(String search);

     ArrayList<Project> findAllByCreatorAndIsCompleteTrue(User user);

     Project findDistinctById(long id);

     ArrayList<Project> findAll();





}