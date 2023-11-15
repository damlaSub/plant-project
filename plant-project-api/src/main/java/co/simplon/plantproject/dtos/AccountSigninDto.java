package co.simplon.plantproject.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountSigninDto {

    @NotBlank
    @Size(min = 1, max = 255)
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    public AccountSigninDto() {

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
	return "{email=" + email + ", password=" + password
		+ "}";
    }

}
