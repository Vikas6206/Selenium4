package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class UserApi {

    private static UserApi userApi;


    private UserApi(){
    }

    public static UserApi getInstance(){
        if(userApi==null){
            userApi= new UserApi();
        }
        return userApi;
    }


    public Response register(User user) {

        RequestSpecification baseRequestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigUtils.getInstance().getBaseUrl())
                .setContentType(ContentType.JSON).build();


        RequestSpecification requestSpec = RestAssured.given()
                .log().all()
                .spec(baseRequestSpec)
                .body(user);

        Response response = requestSpec.when()
                .post("/api/v1/users/register")
                .then()
                .log().all().extract().response();
        return response;

    }
}
