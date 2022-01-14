package com.example.cleancodefileupload.controller;


import com.example.cleancodefileupload.entity.User;
import com.example.cleancodefileupload.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        if (!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Parol uzunligi 4 dan kam ", HttpStatus.BAD_REQUEST);
        }

        if (userService.checkUserName(user.getUserName())){
            return new ResponseEntity("Bu user oldin ruyxatdan o'tgan ", HttpStatus.BAD_REQUEST);

        }

        return ResponseEntity.ok(userService.create(user));
    }

    private Boolean checkPasswordLength(String password){
        return password.length() >= 4;
    }
}
