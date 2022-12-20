package com.example.social.controller;

import com.example.social.collection.User;
import com.example.social.dtos.UserDTO;
import com.example.social.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping(path = "/users/{id}")
    public Mono<ResponseEntity<String>> deleteUser(@PathVariable String id){
        return userService.delete(id).log().thenReturn(ResponseEntity.ok("ok"));
    }

}
