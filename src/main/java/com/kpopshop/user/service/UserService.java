package com.kpopshop.user.service;

import com.kpopshop.user.model.User;
import com.kpopshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    //CRUD CREATE, READ, UPDATE, DELETE

    //CREATE
    public User addUser(User user){
        user.setUserID(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(user);
    }

    //read
    public List<User> findAllUsers(){
        return repository.findAll();
    }


    public User getUserByUserID(String UserID){
        Optional<User> userOptional = repository.findById(UserID);
        if(userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with id: " + UserID);
        }
    }
    public List<User> getUserByUserName(String userName){
        return repository.findByUserName(userName);
    }

    public List<User> getUserBYEmail(String email){
        return repository.getUserByEmail(email);
    }
    public long countUsers() {
        return repository.count();
    }


    //UPDATE

    public User updateUser(User userRequest){
        //get the existing document from DB
        //populate new value from request to existing object/entity/document
        User existingUser = repository.findById(userRequest.getUserID()).get();
        existingUser.setUserName(userRequest.getUserName());
        existingUser.setAddressID(userRequest.getAddressID());
        existingUser.setAddress(userRequest.getAddress());
        //existingUser.setPassword(userRequest.getPassword());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setStreet(userRequest.getStreet());
        existingUser.setCity(userRequest.getCity());
        existingUser.setZipCode(userRequest.getZipCode());
        return repository.save(existingUser);
    }

    //DELETE

    public String deleteUser(String userID){

        repository.deleteById(userID);
        return userID+"task deleted from dashboard";

    }

    // public String deleteAddress(String addressID) {
    // repository.deleteById(addressID);
    //return addressID+"task deleted from dashboard";
    //}
}