package co.simplon.plantproject.services;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.RefreshTokenRequest;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.entities.Account;

public interface AuthService {

    public void signUp(AccountCreateDto inputs);

    public Boolean existsByEmail(String email);

    public TokenInfo signIn(AccountSigninDto inputs);

    public TokenInfo refreshToken(
	    RefreshTokenRequest request);

    public TokenInfo createTokenFromAccount(
	    Account account);

}