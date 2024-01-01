package co.simplon.plantproject.dtos;

public class RefreshTokenResponse {

    private String token;
    private String refreshToken;

    public RefreshTokenResponse() {
	// TODO Auto-generated constructor stub
	super();
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getRefreshToken() {
	return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", refreshToken="
		+ refreshToken + "}";
    }

}
