package co.simplon.plantproject.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.RefreshTokenRequest;
import co.simplon.plantproject.dtos.RefreshTokenResponse;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService service;

    public AuthController(AuthService service) {
	this.service = service;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(
	    @Valid @RequestBody AccountCreateDto inputs) {
	service.signUp(inputs);
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @Valid @RequestBody AccountSigninDto inputs) {
	return service.signIn(inputs);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshtoken(
	    @Valid @RequestBody RefreshTokenRequest request) {
	return service.refreshToken(request);
    }

//    @PostMapping("/refresh-token")
//    public RefreshTokenResponse refreshtoken(
//	    @Valid @RequestBody RefreshTokenRequest request) {
//	return service.refreshToken(request);
//    }

}