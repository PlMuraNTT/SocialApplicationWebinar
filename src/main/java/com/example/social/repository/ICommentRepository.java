package com.example.social.repository;

import com.example.social.collection.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends ReactiveMongoRepository<Comment,String> {
}
