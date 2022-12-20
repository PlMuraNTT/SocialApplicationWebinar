package com.example.social.service;


import com.example.social.collection.User;
import com.example.social.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public Mono<User> save(User user) {
      return userRepository.save(user);
    }

    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }
}
