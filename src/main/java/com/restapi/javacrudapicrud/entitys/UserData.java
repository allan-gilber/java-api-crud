package com.restapi.javacrudapicrud.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * It's a class that represents a user's data.
 */

@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 6, max = 70)
    private Long client_id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @NotBlank
    private String name = "";

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "it cannot be empty")
    @NotBlank(message = "it cannot be blank")
    @Email(message = "please, provide a valid email")
    private String email = "";

    @Column(nullable = false)
    @NotEmpty(message = "it cannot be empty")
    @NotBlank(message = "it cannot be blank")
    @Size(min = 6, max = 50, message = "it must have atleast {min} characters length")
    private String password = "";

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserData [client_id= " + client_id + ", email= " + email + ", name= " + name + ", password= " + password
                + "]";
    }

}
