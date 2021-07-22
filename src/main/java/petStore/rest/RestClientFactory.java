package petStore.rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClientFactory {
    static {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(new AllureRestAssured());
    }

    public static RequestSpecification getClient(String baseUrl){
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}
