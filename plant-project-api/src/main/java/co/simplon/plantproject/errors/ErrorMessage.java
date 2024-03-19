package co.simplon.plantproject.errors;

import java.util.Date;

public class ErrorMessage {

    private Date date;
    private int status;
    private String message;
    private String description;

    public ErrorMessage(Date date, int status,
	    String message, String description) {
	this.date = date;
	this.status = status;
	this.message = message;
	this.description = description;
    }

    public Date getDate() {
	return date;
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
