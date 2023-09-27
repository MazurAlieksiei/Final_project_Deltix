package org.deltix.utility;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.deltix.models.PostTradeResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DeltixApi {

    private static Logger log = Logger.getLogger(DeltixApi.class);
    private Properties properties;
    public DeltixApi(Properties properties) {
        this.properties = properties;
    }

    public String getToken() {
        log.info("Get token from Network.");
        Map<String, String> params = new HashMap<>();
        params.put("password", properties.getProperty("password"));
        params.put("username", properties.getProperty("login"));
        params.put("grant_type", "password");
        params.put("scope", properties.getProperty("scope"));
        String token =
                given().relaxedHTTPSValidation().header("Authorization", "Basic d2ViYXBwOg==")
                .contentType(ContentType.URLENC).formParams(params).post(properties.getProperty("siteUrl") +"/oauth/token")
                .then().assertThat().statusCode(HttpStatus.SC_OK).extract().path("access_token");
        return token;
    }

    public PostTradeResponse getPostTradeOrders() {
        log.info("Get trade orders from Network.");
        return given().relaxedHTTPSValidation().header("Authorization", "bearer" + getToken())
                .contentType(ContentType.JSON)
                .body(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\postTradeOrdersPayload.json"))
                .queryParam("benchmarkType", "Mid")
                .post(properties.getProperty("siteUrl") + "/api/v1/post-trade/orders/query")
                .then().assertThat().statusCode(HttpStatus.SC_OK).extract().as(PostTradeResponse.class);
    }
 }
