package co.simplon.plantproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.Credentials;
import co.simplon.plantproject.dtos.SingInCredentials;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.services.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(@RequestBody Credentials inputs) {
	service.signUp(inputs);
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @RequestBody SingInCredentials inputs) {
	return service.signIn(inputs);
    }

}
