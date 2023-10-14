package co.simplon.plantproject.dtos;

public class SingInCredentials {

    private String email;
    private String password;

    public SingInCredentials() {

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
