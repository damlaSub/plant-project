package co.simplon.plantproject.dtos;

public class TokenInfo {

    private String token;

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

    @Override
    public String toString() {
	return "{token=" + token + "}";
    }

}
