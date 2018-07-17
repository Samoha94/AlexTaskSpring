package com.gmail.samoha199412.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.samoha199412.entity.Project;
import com.gmail.samoha199412.entity.User;

import com.gmail.samoha199412.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demoUser")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping(path = "/users")
    public @ResponseBody String addUser(@RequestBody User user) {
        userService.save(user);
        return "add new user";
    }

    @GetMapping(path = "/users")
    @JsonView(User.ShowUser.class)
    public @ResponseBody List<User> showUser(){
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    @JsonView(User.ShowUser.class)
    public @ResponseBody
    Optional<User> showUser(@PathVariable("id") Long id){
        return userService.findUser(id);
    }

    @PutMapping(path = "/users/{id}")
    public @ResponseBody String refreshUser(@PathVariable("id") Long id, @RequestBody User user){
        userService.refreshUser(id, user);
        return "Well done";
    }

    @DeleteMapping(path = "/users/{id}")
    public @ResponseBody String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "Well Done";
    }

    @DeleteMapping(path = "/users")
    public @ResponseBody String deleteAllUsers(){
        userService.deleteAllUser();
        return "Well done";
    }

    @GetMapping(path = "/users/{id}/projects")
    @JsonView(Project.ShowProject.class)
    public @ResponseBody List<Project> showUserProject(@PathVariable("id") Long id){
        return userService.getUserProjects(id);
    }

    @DeleteMapping(path = "/users/{id}/projects")
    public ResponseEntity deleteAllProjectOfUser(@PathVariable("id") Long id){
        userService.deleteUserProjects(id);
        return ResponseEntity.status(200).build();
    }
}
