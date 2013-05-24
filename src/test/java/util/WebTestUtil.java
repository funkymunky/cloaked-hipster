package util;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 10/04/13
 * Time: 8:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class WebTestUtil {
    private static final String DEFAULT_SERVER = "http://localhost:8080/";
    private static final String DEFAULT_CONTEXT_NAME = "HelloWorld";
    public static final String CONTEXT = DEFAULT_SERVER + DEFAULT_CONTEXT_NAME + "/";
    public static final String LOGIN_PAGE_ID = "login";

    private WebTestUtil() {
    }
}
