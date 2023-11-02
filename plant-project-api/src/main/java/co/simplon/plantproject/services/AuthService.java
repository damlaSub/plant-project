package co.simplon.plantproject.services;

import co.simplon.plantproject.dtos.SignUpCredentials;
import co.simplon.plantproject.dtos.SingInCredentials;
import co.simplon.plantproject.dtos.TokenInfo;

public interface AuthService {

    public void signUp(SignUpCredentials inputs);

    public Boolean existsByEmail(String email);

    public TokenInfo signIn(SingInCredentials inputs);

}