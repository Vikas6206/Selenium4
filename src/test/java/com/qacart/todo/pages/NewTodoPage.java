package com.qacart.todo.pages;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewTodoPage {

    private static NewTodoPage newTodoPage;
    private final By newTodo = By.cssSelector("[data-testid=\"new-todo\"]");
    private final By submitNewTask = By.cssSelector("[data-testid=\"submit-newTask\"]");

    private NewTodoPage() {
    }

    public static NewTodoPage getInstance(){
        if(newTodoPage==null){
            newTodoPage=new NewTodoPage();
        }
        return newTodoPage;
    }


    @Step("add new to do via UI")
    public void addNewTodoTask(WebDriver webDriver,String value){
        webDriver.findElement(newTodo).sendKeys(value);
        webDriver.findElement(submitNewTask).click();
    }



    @Step("add new to do via API")
    public void addNewTodoTaskViaAPI(User user, String value){
        Response response =TodoApi.getInstance().addToDo(user,value);
    }

}
