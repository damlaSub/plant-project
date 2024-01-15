package co.simplon.plantproject.dtos;

public class RefreshTokenRequest {

    @jakarta.validation.constraints.NotBlank
    private String refreshToken;

    public String getRefreshToken() {
	return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
    }
}
