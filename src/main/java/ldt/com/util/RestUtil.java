package ldt.com.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ldt.com.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtil {
    public static Logger log = LoggerFactory.getLogger(RestUtil.class);
    private static final RestUtil instance = new RestUtil();
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static RestUtil getInstance() {
        return instance;
    }

    private RestUtil() {
    }

    /**
     *
     * @param ep String
     * @return Response
     */
    public Response getToBackend(String ep) {
        Response response = given()
                .header("Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get(ep);
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            throw new RestException("get", ep, "no payload", response.asString());
        }
    }

    /**
     *
     * @param ep String
     * @return Response
     */
    public Response getToBackendXml(String ep) {
        Response response = given()
                .when()
                .get(ep);
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            throw new RestException("get", ep, "no payload", response.asString());
        }
    }

    /**
     *
     * @param ep String
     * @param payload Map<String,Object>
     * @return Response
     */
    public Response putToBackend(String ep, Map<String,Object> payload) {
        Response response = given()
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .put(ep);
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            throw new RestException("put", ep, payload, response.asString());
        }
    }

    public Response postToBackend(String ep, Map<String,Object> payload) {
        Response response = given()
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .body(payload)
                .post(ep);
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            throw new RestException("post", ep, payload, response.asString());
        }
    }

    public void setSession(Response response) {
        RestAssured.sessionId = response.getCookie("JSESSIONID");
    }

}
