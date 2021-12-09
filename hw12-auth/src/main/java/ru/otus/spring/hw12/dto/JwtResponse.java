package ru.otus.spring.hw12.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

	public JwtResponse(String token, String username) {
		this.token = token;
		this.username = username;
	}

	private String token;
	private String type = "Bearer";
	private String username;

}
