package com.qacart.todo.pages;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    //web elements
    private static RegisterPage registerPage;

    private final By firstNameInput = By.cssSelector("[data-testid=\"first-name\"]");
    private final By lastNameInput = By.cssSelector("[data-testid=\"last-name\"]");
    private final By email = By.cssSelector("[data-testid=\"email\"]");
    private final By pwd = By.cssSelector("[data-testid=\"password\"]");
    private final By confirmPwd = By.cssSelector("[data-testid=\"confirm-password\"]");
    private final By submit = By.cssSelector("[data-testid=\"submit\"]");
    private final By welcome = By.cssSelector("[data-testid=\"welcome\"]");

    //methods

    private RegisterPage() {
    }

   public static RegisterPage getInstance(){
        if(registerPage==null){
            registerPage = new RegisterPage();
        }
        return registerPage;
   }

   @Step("visit the signup page")
   public void load(WebDriver webDriver){
       webDriver.get(ConfigUtils.getInstance().getBaseUrl() +"/signup");
   }
    @Step("register using the UI")
   public void register(WebDriver webDriver, User user){
        webDriver.findElement(firstNameInput).sendKeys(user.getFirstName());
        webDriver.findElement(lastNameInput).sendKeys(user.getLastName());
        webDriver.findElement(email).sendKeys(user.getEmail());
        webDriver.findElement(pwd).sendKeys(user.getPassword());
        webDriver.findElement(confirmPwd).sendKeys(user.getPassword());
        webDriver.findElement(submit).click();
    }

    @Step("register using the API")
    public void registerViaAPI(WebDriver webDriver, User user){
        Response response =UserApi.getInstance().register(user);
        //Extract all the values to be used for setting the cookies
        String accessToken =response.path("access_token");
        user.setAccessToken(accessToken);
        String userID =response.path("userID");
        String firstName =response.path("firstName");

        //set the cookies (from selenium) for page navigation
        Cookie accessTokenCookie = new Cookie("access_token",accessToken);
        Cookie userIDCookie = new Cookie("userID",userID);
        Cookie firstNameCookie = new Cookie("firstName",firstName);

        //add the cookies to the browser
        webDriver.manage().addCookie(accessTokenCookie);
        webDriver.manage().addCookie(userIDCookie);
        webDriver.manage().addCookie(firstNameCookie);

        //reload the page and the page should navigate to the next page
        RegisterPage.getInstance().load(webDriver);
    }
}
