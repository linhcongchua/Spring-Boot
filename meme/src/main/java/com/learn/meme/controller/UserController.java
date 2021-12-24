package com.learn.meme.controller;

import com.learn.meme.model.Customer;
import com.learn.meme.model.User;
import com.learn.meme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    @PreAuthorize("@UserServiceRegisterAspect.isOwnerProfile(authentication, #username)")
    public Customer getCustomerByUsername(@PathVariable(value = "username") String username){
        return userService.getCustomerByUsername(username);
    }

    @GetMapping("/new-user-in-month")
    public List<Customer> getNewUsersInMonth(@RequestParam int month, @RequestParam int year){
        return userService.getNewUsersInMonth(month, year);
    }

    @PostMapping("/registration/{typeUser}")
    public User registation(@PathVariable(value = "typeUser") String typeUser, @RequestBody User user){
        return userService.registerOne(user, typeUser);
    }

    @PostMapping("/add")
    public void addUser(){

    }

    @PutMapping("/update")
    public void updateUser(){

    }

    @DeleteMapping("/delete")
    public void deleteUser(){

    }
}
