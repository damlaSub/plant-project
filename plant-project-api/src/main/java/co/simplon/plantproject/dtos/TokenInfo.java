package co.simplon.plantproject.dtos;

public class TokenInfo {

    private String token;

    private String role;

    private String firstName;

    public TokenInfo() {
	// TODO Auto-generated constructor stub
	super();
    }

    public String getToken() {
	return token;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public void setToken(String token) {
	this.token = token;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", role=" + role
		+ ", firstName=" + firstName + "}";
    }

}
