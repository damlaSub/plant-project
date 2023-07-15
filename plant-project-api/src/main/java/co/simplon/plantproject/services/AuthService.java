package co.simplon.plantproject.services;

import co.simplon.plantproject.dtos.Credentials;
import co.simplon.plantproject.dtos.TokenInfo;

public interface AuthService {

    public void signUp(Credentials inputs);

    public TokenInfo signIn(Credentials inputs);

}