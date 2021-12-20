package ru.otus.spring.hw13.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class JwtResponse {

	public JwtResponse(String token, String username, List<String> roles) {
		this.token = token;
		this.username = username;
		this.roles = roles;
	}

	private String token;
	private String type = "Bearer";
	private String username;
	private List<String> roles;

}
