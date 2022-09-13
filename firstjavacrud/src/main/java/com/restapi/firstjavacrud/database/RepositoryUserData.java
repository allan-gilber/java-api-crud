package com.restapi.firstjavacrud.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.firstjavacrud.entitys.UserData;

// Creating a repository for the UserData class.
public interface RepositoryUserData extends JpaRepository<UserData, Long> {

}
