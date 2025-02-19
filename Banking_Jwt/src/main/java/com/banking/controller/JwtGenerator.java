package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.JwtRequestBody;
import com.banking.model.Jwttoken;
import com.banking.service.JwtService;

@RestController
public class JwtGenerator {
	
	@Autowired
	private JwtService service;

	@PostMapping("/jwttoken")
	public Jwttoken generateToken(@RequestBody JwtRequestBody body) {
		String token=service.generateToken(body.getUsername(), body.getRoles());
		Jwttoken resultToken=new Jwttoken();
		resultToken.setToken(token);
		return resultToken;
	}
	@PostMapping("/jwtvalidate")
	public boolean validateToken(@RequestBody Jwttoken token) {
		boolean validity=service.isTokenValid(token.getToken());
		return validity;
	}
}
