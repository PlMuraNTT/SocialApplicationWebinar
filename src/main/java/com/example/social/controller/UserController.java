package com.example.social.controller;

import com.example.social.collection.User;
import com.example.social.dtos.UserDTO;
import com.example.social.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;


    @PostMapping(path = "/users")
    public Mono<ResponseEntity<UserDTO>> saveUser(@RequestBody User userDTO){
     return userService.save(mapper.map(userDTO, User.class))
             .doOnNext(user -> log.info("Utente salvato {}",user))
             .flatMap(user -> Mono.just(user))
             .map(user -> user)
             .map(user -> ResponseEntity.ok(mapper.map(user,UserDTO.class)));
    }

    @DeleteMapping(path = "/users/{id}")
    public Mono<ResponseEntity<String>> deleteUser(@PathVariable String id){
        return userService.delete(id).log().thenReturn(ResponseEntity.ok("ok"));
    }

    @GetMapping(path = "/users")
    public Flux<UserDTO> getUsers(){
        return userService.findAll()
                .map(user -> mapper.map(user,UserDTO.class))
                .log()
                .switchIfEmpty(Flux.error(new Throwable("Utenti non trovati")));
    }

    @GetMapping(path = "/users/{id}")
    public Mono<ResponseEntity<UserDTO>> getUser(@PathVariable String id){
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(mapper.map(user,UserDTO.class)))
                .log()
                .switchIfEmpty(Mono.just(ResponseEntity.status(404).build()));
    }



}
