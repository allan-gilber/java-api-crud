package com.restapi.firstjavacrud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.firstjavacrud.database.DatabaseConnection;

import com.restapi.firstjavacrud.entitys.UserData;

@RestController
@RequestMapping("/user")
public class UserREST {
    @Autowired
    private DatabaseConnection base;

    @GetMapping
    public List<UserData> listAll() {
        return base.findAll();
    }

    @PostMapping
    public void save(@RequestBody UserData userData) {
        base.save(userData);
    }

    @PutMapping
    public void modifyUser(@RequestBody UserData userData) {
        if (userData.getId() > 0)
            base.save(userData);

    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserData userData) {
        base.delete(userData);
    }
}
