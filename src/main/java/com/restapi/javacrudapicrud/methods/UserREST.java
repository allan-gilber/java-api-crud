package com.restapi.javacrudapicrud.methods;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.javacrudapicrud.database.RepositoryUserData;
import com.restapi.javacrudapicrud.entitys.UserData;
import com.restapi.javacrudapicrud.exceptions.ApiRequestException;
import com.restapi.javacrudapicrud.viewers.ApiBodyResponseViewer;
import com.restapi.javacrudapicrud.viewers.successMessageViewer;

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
    public ResponseEntity<?> save(@Valid @RequestBody UserData userData) {
        try {
            base.save(userData);
            String message = new successMessageViewer().userPostsuccess(userData.getName());
            ;
            System.out.println("message: " + message);

            Object teste = new ApiBodyResponseViewer(message);
            System.out.println("333 " + teste);
            return new ResponseEntity<>(new ApiBodyResponseViewer(message),
                    HttpStatus.OK);
        } catch (ApiRequestException e) {
            System.out.println("Error: " + e.getMessage());
            throw new ApiRequestException(e.getMessage(), e.getHttpStatus());
        }
    }

    // Maybe in the future?
    // @PutMapping
    // public void modifyUser(@RequestBody UserData userData) {
    // if (userData.getClient_id() > 0)
    // base.save(userData);
    // }

    // For future updates.
    // @DeleteMapping
    // public void deleteUser(@RequestBody UserData userData) {
    // base.delete(userData);
    // }
}
