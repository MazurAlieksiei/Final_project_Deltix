package org.example.utility;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.example.models.PostTradeResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DeltixApi {

    Properties properties;
    public DeltixApi(Properties properties) {
        this.properties = properties;
    }

    public String getToken() {
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
        return given().relaxedHTTPSValidation().header("Authorization", "bearer" + getToken())
                .contentType(ContentType.JSON)
                .body(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\postTradeOrdersPayload.json"))
                .queryParam("benchmarkType", "Mid")
                .post(properties.getProperty("siteUrl") + "/api/v1/post-trade/orders/query")
                .then().assertThat().statusCode(HttpStatus.SC_OK).extract().as(PostTradeResponse.class);
    }
 }
