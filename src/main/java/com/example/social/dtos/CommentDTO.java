package com.example.social.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

	private String postId;
	private String userId;
	private String id;
	private String name;
	private String email;
	private String body;
}