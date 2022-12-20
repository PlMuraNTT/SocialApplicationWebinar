package com.example.social.controller;

import com.example.social.collection.User;
import com.example.social.model.UserDTO;
import com.example.social.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;


    @PostMapping(path = "/users")
    public Mono<ResponseEntity<UserDTO>> saveUser(@RequestBody UserDTO userDTO){
     return userService.save(mapper.map(userDTO, User.class)).map(user -> ResponseEntity.ok(mapper.map(user,UserDTO.class)));
    }

}
