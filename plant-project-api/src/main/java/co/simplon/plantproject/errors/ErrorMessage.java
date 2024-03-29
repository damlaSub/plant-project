package co.simplon.plantproject.errors;

public class ErrorMessage {

    private int status;
    private String message;
    private String description;

    public ErrorMessage(int status, String message,
	    String description) {

	this.status = status;
	this.message = message;
	this.description = description;
    }

    public int getStatus() {
	return status;
    }

    public String getMessage() {
	return message;
    }

    public String getDescription() {
	return description;
    }
}
