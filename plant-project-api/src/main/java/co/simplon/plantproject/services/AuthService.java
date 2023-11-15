package co.simplon.plantproject.services;

import java.util.Collection;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountItem;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.TokenInfo;

public interface AuthService {

    public void signUp(AccountCreateDto inputs);

    public Boolean existsByEmail(String email);

    public TokenInfo signIn(AccountSigninDto inputs);

    public AccountItem getAccount(Long id);

    public Collection<AccountItem> getAll();

}