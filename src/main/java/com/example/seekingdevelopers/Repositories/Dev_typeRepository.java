package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.models.Dev_type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dev_typeRepository extends CrudRepository<Dev_type, Long> {

}
