package com.example.social.controller;

import com.example.social.dtos.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api/playground/v1")
public class PlayGroundController {


    Scheduler boundedElastic = Schedulers.boundedElastic();

    @GetMapping("/interval/{items}/{delay}")
    public Flux<Integer> fluxItems(@PathVariable Integer items, @PathVariable Integer delay){
        return Flux.fromIterable(IntStream.range(0,items).boxed().collect(Collectors.toList()))
                .log()
                .delayElements(Duration.of(delay, ChronoUnit.SECONDS));
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<String>> retriveUserNameByIdFromExternalService(@PathVariable(name = "id") Integer userId){
        //https://jsonplaceholder.typicode.com/users/{id}

        return WebClient.create("https://jsonplaceholder.typicode.com/")
                .method(HttpMethod.GET)
                .uri("/users/{id}", Map.of("id", userId))
                .retrieve()
                .bodyToMono(UserDTO.class)
                .publishOn(boundedElastic)
                .log()
                .flatMap(user -> Mono.just(ResponseEntity.ok(user.getName().toUpperCase())));
    }

}