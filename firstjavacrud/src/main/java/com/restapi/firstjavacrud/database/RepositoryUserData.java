package com.restapi.firstjavacrud.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.firstjavacrud.entitys.UserData;

public interface RepositoryUserData extends JpaRepository<UserData, Long> {

}
