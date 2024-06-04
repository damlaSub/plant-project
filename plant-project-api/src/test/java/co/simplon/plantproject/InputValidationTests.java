package co.simplon.plantproject;

import static org.hamcrest.CoreMatchers.not;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@SpringBootTest
@DisplayName("Tests inputs against validations")
public class InputValidationTests extends BaseMvcTests {

	@DisplayName("Should input be not valid")
	 @ParameterizedTest
	 @CsvFileSource(resources = {
			 "/csv/validation/account-create-not-valid.csv",
			 "/csv/validation/account-sign-in-not-valid.csv"
	 }, numLinesToSkip = 1 , delimiter = DELIMITER,
	 maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	 void shouldBeNotValid(String method, String path, String tokenName, String json, String field, String error) throws Exception {
							
			String jsonPath  = null;

			if(field !=null) {//Field error
				jsonPath  = String.format("$.fieldErrors.%s", field);
			} else {//Global error
				jsonPath = String.format("$.globalErrors");
			}
			System.out.println(jsonPath);
		perform(method, path, tokenName, json, field, error)
		.andExpect(status().isBadRequest())
		.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath(jsonPath).value(Matchers.containsInAnyOrder(error.split(","))));	
		
	 }
	
	@DisplayName("Should input be valid")
	 @ParameterizedTest
	 @CsvFileSource(resources = {
			 "/csv/validation/account-create-valid.csv",
			 "/csv/validation/account-sign-in-valid.csv"
	 }, numLinesToSkip = 1 , delimiter = DELIMITER,
	 maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	 void shouldBeValid(String method, String path, String tokenName, String json, int expectedStatus) throws Exception {
		 perform(method, path, tokenName, json).andExpectAll(status().is(expectedStatus));
	 }
}
