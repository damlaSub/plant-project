package co.simplon.plantproject.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountItem;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.services.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;

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

    @GetMapping("/accounts")
    public Collection<AccountItem> getAll() {
	return service.getAll();
    }

    @GetMapping("/accounts/{id}")
    public AccountItem getAccount(
	    @PathVariable("id") Long id,
	    AccountItem input) {
	return service.getAccount(id);
    }

}
