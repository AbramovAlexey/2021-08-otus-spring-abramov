package ru.otus.spring.hw16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class AppUserDto {

	@NotBlank
	private String name;

	@NotBlank
	private String password;


}
