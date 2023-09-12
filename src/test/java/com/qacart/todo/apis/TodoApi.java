package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TodoApi {
    private static TodoApi todoApi;

    private TodoApi() {
    }

    public static TodoApi getInstance(){
        if(todoApi==null){
            todoApi=new TodoApi();
        }
        return todoApi;
    }

    //call the api
    public Response addToDo(User user, String item){
        return RestAssured.given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType(ContentType.JSON)
                .body("{\"item\": \""+item+"\",\"isCompleted\": false}")
                .log().all()
                .auth().oauth2(user.getAccessToken())
                .when()
                .post("/api/v1/tasks")
                .then().log().all().extract().response();
    }
}
