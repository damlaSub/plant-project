package co.simplon.plantproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = {"classpath:db/schema-test.ddl.sql", "classpath:db/data-test.dml.sql"}, 
     executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
class BaseMvcTests {
	/*
     * Constants used to define limits or separators in
     * tests. They are not directly used in the class, but can
     * be used by classes or methods called.
     */
    protected static final char DELIMITER = '§';
    protected static final int MAX_CHARS_PER_COLUMN = 8192;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Tokens tokens;

    // This method executes an HTTP request with the method (GET, POST, etc.), 
    //the specified path and an authentication token.
    protected final ResultActions perform(String method, String path, String tokenName) throws Exception {
        return perform(method, path, tokenName, null);
    }
    
    // Overloading the previous method, which also accepts JSON content
    //for POST or PUT requests.
    protected final ResultActions perform(String method, String path, String tokenName, String content) throws Exception {
        var builder = requestBuilder(method, path, tokenName, content, null, null);
        return mvc.perform(builder);
    }
    
    protected final ResultActions perform(String method, String path, String tokenName, String json, String field, String error) throws Exception {
        var builder = requestBuilder(method, path, tokenName, json, field, error);
        return mvc.perform(builder);
    }     

    // This method constructs an HTTP request
    private MockHttpServletRequestBuilder requestBuilder(String method, String path, String tokenName, String content, String field, String error) {
        var builder = MockMvcRequestBuilders.request(HttpMethod.valueOf(method), path);
        if (!"anonymous".equals(tokenName)) { // Add an authorization header if the tokenName is not "anonymous"
            builder.header("Authorization", tokens.get(tokenName));
        }
        if (content != null) { // If the request contains a body then it is added
            builder.contentType(MediaType.APPLICATION_JSON).content(content);
        }
        return builder;
    }

}
