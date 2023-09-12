package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage {

    private static TodoPage todoPage;
    private final By welcomeMessage = By.cssSelector("[data-testid=\"welcome\"]");
    private final By plusButton = By.cssSelector("[data-testid=\"add\"]");
    private final By toDoItem = By.cssSelector("[data-testid=\"todo-item\"]");
    private final By deleteIcon = By.cssSelector("[data-testid=\"delete\"]");

    private final By noToDoLeft = By.cssSelector("[data-testid=\"no-todos\"]");


    private TodoPage() {
    }

    public static TodoPage getInstance(){
        if(todoPage==null){
            todoPage=new TodoPage();
        }
        return todoPage;
    }


    @Step("is welcome message displayed")
    public boolean isWelcomeMessageDisplayed(WebDriver webDriver){
        return webDriver.findElement(welcomeMessage).isDisplayed();
    }

    @Step("click on plus button via UI")
    public void clickOnPlusButton(WebDriver webDriver){
        webDriver.findElement(plusButton).click();
    }

    @Step("get Created element text value")
    public String getCreateText(WebDriver webDriver){
        return webDriver.findElement(toDoItem).getText();
    }

    @Step("delete the todo item")
    public void deleteToDo(WebDriver webDriver){
        webDriver.findElement(deleteIcon).click();
    }

    public boolean isThereNoTodoMessageDisplayed(WebDriver webDriver){
        return webDriver.findElement(noToDoLeft).isDisplayed();
    }
    public void load(WebDriver webDriver){
        webDriver.get(ConfigUtils.getInstance().getBaseUrl() +"/signup");
    }
}
