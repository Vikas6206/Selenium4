package com.qacart.todo.testcases;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

    @Test
    public void shouldBEaAbleToRegisterTheApplication(){
        User user = new User();
        RegisterPage.getInstance().load(webDriver.get());
        RegisterPage.getInstance().registerViaAPI(webDriver.get(), user);
        Assert.assertTrue(TodoPage.getInstance().isWelcomeMessageDisplayed(webDriver.get()));
    }
}
