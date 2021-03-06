package com.example.cleancodefileupload.controller;


import com.example.cleancodefileupload.controller.vm.LoginVM;
import com.example.cleancodefileupload.entity.User;
import com.example.cleancodefileupload.repository.UserRepository;
import com.example.cleancodefileupload.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserJwtController {

 private final    AuthenticationManager authenticationManager ;
 private final JwtTokenProvider jwtTokenProvider;
 private final UserRepository userRepository;
    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginVM loginVM){


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword()));
        User user = userRepository.findByLogin(loginVM.getUsername());
        if (user ==null){
            throw new UsernameNotFoundException("Bu foydalanuvchi mavjud emas");
        }
        String token = jwtTokenProvider.createToken(user.getUserName(), user.getRoles());
        Map<Object, Object> map = new HashMap<>();
        map.put("username",user.getUserName());
        map.put("token", token);
        return ResponseEntity.ok(map);

    }

}
