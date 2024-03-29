package co.simplon.plantproject.errors;

@SuppressWarnings("serial")
public class AccountNotFoundException
	extends RuntimeException {

    public AccountNotFoundException(String msg) {
	super(msg);
    }

}
