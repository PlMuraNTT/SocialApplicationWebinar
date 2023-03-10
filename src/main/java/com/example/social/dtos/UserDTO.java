package com.example.social.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String id;
	private String name;
	private String username;
	private String email;
    private CompanyDTO company;
}