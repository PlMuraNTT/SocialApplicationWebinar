package com.example.social.repository;

import com.example.social.collection.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends ReactiveMongoRepository<Post,String> {
}
