package co.simplon.plantproject.errors;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

@SuppressWarnings("serial")
public class Conflict extends HttpClientErrorException {

    public Conflict(HttpStatusCode statusCode,
	    String statusText) {
	super(statusCode, statusText);
	// TODO Auto-generated constructor stub
    }

}
