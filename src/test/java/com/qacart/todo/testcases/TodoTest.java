package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TodoTest extends BaseTest {


    @Test
    public void shouldBeAbleToAddATodo() {

        User user = new User();
        RegisterPage.getInstance().load(webDriver.get());
        RegisterPage.getInstance().registerViaAPI(webDriver.get(), user);
        TodoPage.getInstance().clickOnPlusButton(webDriver.get());
        NewTodoPage.getInstance().addNewTodoTask(webDriver.get(), "Learn Selenium");
        RegisterPage.getInstance().load(webDriver.get());
        String text = TodoPage.getInstance().getCreateText(webDriver.get());
        Assert.assertEquals(text, "Learn Selenium");
    }


    @Test
    public void shouldBeAbleToDeleteTest() {
        User user = new User();
        RegisterPage.getInstance().load(webDriver.get());
        RegisterPage.getInstance().registerViaAPI(webDriver.get(), user);
        NewTodoPage.getInstance().addNewTodoTaskViaAPI(user,"Learn Selenium");
        TodoPage.getInstance().load(webDriver.get());
        TodoPage.getInstance().deleteToDo(webDriver.get());
        boolean isMessageDisplayed = TodoPage.getInstance().isThereNoTodoMessageDisplayed(webDriver.get());
        Assert.assertTrue(isMessageDisplayed);
    }
}
