package com.example.social;


import com.example.social.model.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebClientTest {


    @Test
    public void testWebClient(){
        Mono<CommentDTO> comment = WebClient.create("https://jsonplaceholder.typicode.com/")
                .method(HttpMethod.GET)
                .uri("/comments/{id}", Map.of("id", 1))
                .retrieve()
                .bodyToMono(CommentDTO.class);


        StepVerifier
                .create(comment)
                .consumeNextWith(commentDTO -> assertEquals(commentDTO.getId(),"1"))
                .verifyComplete();
    }

}
