package co.simplon.plantproject.dtos;

public class TokenInfo {

    private String token;

    private String role;

    private String firstName;

    private String refreshToken;

    public TokenInfo() {
	// TODO Auto-generated constructor stub
	super();
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
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

    public String getRefreshToken() {
	return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", role=" + role
		+ ", firstName=" + firstName
		+ ", refreshToken=" + refreshToken + "}";
    }

}
