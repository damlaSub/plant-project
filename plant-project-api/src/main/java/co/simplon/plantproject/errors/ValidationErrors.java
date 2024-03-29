package co.simplon.plantproject.errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ValidationErrors {

    private final Map<String, Set<String>> fieldErrors = new HashMap<String, Set<String>>();

    private final List<String> globalErrors = new ArrayList<String>();

    ValidationErrors() {
	//
    }

    void addFieldError(String fieldName, String errorCode) {
	handleFieldError(fieldName, errorCode);
    }

    void addGlobalErrors(String errorCode) {
	globalErrors.add(errorCode);
    }

    private void handleFieldError(String fieldName,
	    String errorCode) {
	Set<String> codes = fieldErrors.get(fieldName);
	if (codes == null) {
	    codes = new HashSet<String>();
	    fieldErrors.put(fieldName, codes);
	}
	codes.add(errorCode);
    }

    public Map<String, Set<String>> getFieldErrors() {
	return Collections.unmodifiableMap(fieldErrors);
    }

    public List<String> getGlobalErrors() {
	return Collections.unmodifiableList(globalErrors);
    }

}
