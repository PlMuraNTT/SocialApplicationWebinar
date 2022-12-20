package com.example.social.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api/playground/v1")
public class PlayGroundController {


    @GetMapping("/interval/{items}/{delay}")
    public Flux<Integer> fluxItems(@PathVariable Integer items, @PathVariable Integer delay){
        return Flux.fromIterable(IntStream.range(0,items).boxed().collect(Collectors.toList()))
                .log()
                .delayElements(Duration.of(delay, ChronoUnit.SECONDS));
    }

}
