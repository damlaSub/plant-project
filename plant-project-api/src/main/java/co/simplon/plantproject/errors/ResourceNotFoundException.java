package co.simplon.plantproject.errors;

@SuppressWarnings("serial")
public class ResourceNotFoundException
	extends RuntimeException {

    public ResourceNotFoundException(String msg) {
	super(msg);
    }
}
