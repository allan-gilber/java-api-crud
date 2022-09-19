package com.restapi.firstjavacrud.methods;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.firstjavacrud.database.RepositoryUserData;
import com.restapi.firstjavacrud.entitys.UserData;
import com.restapi.firstjavacrud.exceptions.ApiRequestException;

@/**
  * This class is a REST controller that handles the requests for the user data
  */
RestController
@RequestMapping("/user")
public class UserREST {
    @Autowired
    private RepositoryUserData base;

    /**
     * It's a function that returns a list of users from the database, and it's
     * paginated
     * 
     * @param pageNumber The page number to retrieve.
     * @return A list of users.
     */
    @GetMapping
    public ResponseEntity<?> listAll(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber) {
        try {
            Page<UserData> request = base.findAll(PageRequest.of(pageNumber, 10, Sort.by(Sort.Direction.ASC, "name")));

            if (request.getNumberOfElements() == 0)
                throw new ApiRequestException("Opps, nothing was found. Empty Database", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(request.getContent(), HttpStatus.OK);
        } catch (ApiRequestException e) {
            System.out.println("Error: " + e.getMessage());
            throw new ApiRequestException(e.getMessage(), e.getHttpStatus());
        }
    }

    @PostMapping
    public void save(@Valid @RequestBody UserData userData) {
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
