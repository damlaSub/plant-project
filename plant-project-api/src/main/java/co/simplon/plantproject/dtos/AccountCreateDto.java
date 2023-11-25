package co.simplon.plantproject.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import co.simplon.plantproject.customValidation.UniqueAccountEmail;

public class AccountCreateDto {

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;

    @NotBlank
    @Email
    @UniqueAccountEmail
    private String email;

    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    public AccountCreateDto() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "{firstName=" + firstName + ", lastName="
		+ lastName + ", email=" + email
		+ ", password=" + password + "}";
    }

}
