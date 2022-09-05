package com.restapi.firstjavacrud.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.firstjavacrud.database.RepositoryUserData;
import com.restapi.firstjavacrud.entitys.UserData;
import com.restapi.firstjavacrud.exceptions.ApiRequestException;

@RestController
@RequestMapping("/user")
public class UserREST {
    @Autowired
    private RepositoryUserData base;

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        throw new ApiRequestException("Opps, a error ocurred!");
        // return new ResponseEntity<>(base.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public void save(@RequestBody UserData userData) {
        base.save(userData);
    }

    @PutMapping
    public void modifyUser(@RequestBody UserData userData) {
        if (userData.getClient_id() > 0)
            base.save(userData);

    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserData userData) {
        base.delete(userData);
    }
}
