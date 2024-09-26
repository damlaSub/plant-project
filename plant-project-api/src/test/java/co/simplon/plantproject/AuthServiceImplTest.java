package co.simplon.plantproject;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;


import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.entities.Account;
import co.simplon.plantproject.repositories.AccountRepository;
import co.simplon.plantproject.services.AuthServiceImpl;
import co.simplon.plantproject.utils.AuthHelper;

class AuthServiceImplTest {

	@Mock
	AuthHelper authHelper;
	@Mock
    AccountRepository accountRepository;
	@InjectMocks
	AuthServiceImpl service;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Test for AuthServiceImpl sign up")
	void shouldCreateAccount() {
		//Given
		AccountCreateDto inputs = new AccountCreateDto();
		inputs.setFirstName("John");
        inputs.setLastName("Doe");
        inputs.setEmail("john.doe@example.com");
        inputs.setPassword("Test1234@");
        inputs.setConfirm("Test1234@");
        
        //When
        when(authHelper.encode(inputs.getPassword()))
        .thenReturn("$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC");
        
        when(accountRepository.save(any(Account.class))).thenReturn(null);
        
        service.signUp(inputs);

        verify(authHelper).encode("Test1234@");
        verify(accountRepository, times(1)).save(any(Account.class));
        
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        Account savedAccount = accountCaptor.getValue();
        
        //Then
        assertEquals("John", savedAccount.getFirstName());
        assertEquals("Doe", savedAccount.getLastName());
        assertEquals("john.doe@example.com", savedAccount.getEmail());
        assertEquals("$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC", savedAccount.getPassword());
		
	}
}

