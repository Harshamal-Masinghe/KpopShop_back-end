package com.kpopshop.user.controller;

import com.kpopshop.user.model.User;
import com.kpopshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping( "/users")

public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){

        return service.addUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return service.findAllUsers();
    }

    @GetMapping("/{userID}")
    public User getUser(@PathVariable String userID){
        return service.getUserByUserID(userID);
    }

    @GetMapping("/userName/{userName}")
    public List<User> findUserUsingUserName(@PathVariable String userName){
        return service.getUserByUserName(userName);
    }

    @GetMapping("/email/{email}")
    public List<User> getUserBYEmail(@PathVariable String email){
        return service.getUserBYEmail(email);
    }

    @GetMapping("/count")
    public long countUsers() {
        return service.countUsers();
    }

    //UPDATE
    @PutMapping("/{userID}")
    public User modifyUser(@RequestBody User user){
        return service.updateUser(user);
    }
    //DELETE
    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable String userID){
        return service.deleteUser(userID);
    }

    // New endpoint to generate user report
    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getUserReport() {
        Map<String, Object> report = new HashMap<>();
        List<User> users = service.findAllUsers();
        long userCount = service.countUsers();
        report.put("users", users);
        report.put("userCount", userCount);
        return ResponseEntity.ok(report);
    }

    //@DeleteMapping("/address/{addressID}")
    //public String deleteAddress(@PathVariable String addressID){
    //return service.deleteAddress(addressID);
    //}

}