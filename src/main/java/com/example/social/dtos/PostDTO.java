package com.example.social.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

	private String id;
	private ObjectId userId;
	private String title;
	private String body;
}