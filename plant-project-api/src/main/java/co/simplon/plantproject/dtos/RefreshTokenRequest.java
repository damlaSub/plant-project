package co.simplon.plantproject.dtos;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequest {

    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
	return refreshToken;
    }

    private void setRefreshToken(String refreshToken) {
    	// Not updatable
	this.refreshToken = refreshToken;
    }
}
