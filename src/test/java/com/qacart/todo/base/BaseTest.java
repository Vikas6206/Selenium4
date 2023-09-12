package com.qacart.todo.base;


import com.qacart.todo.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseTest {


//    protected WebDriver webDriver;
    //3 threads accessing the same driver at same time where the driver object is overridden while
    //runing the test in parallel
    protected ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    @BeforeMethod
    protected void setup(){
       WebDriver webDriver = new DriverFactory().initializeDriver();
       this.webDriver.set(webDriver);
    }

    @AfterMethod
    protected void tearDown(ITestResult result){
        String testCaseName =result.getMethod().getMethodName();
        File destnFile = new File("target"+File.separator+"screenshots"+File.separator+testCaseName+".png");
        takeScreenshot(destnFile);
        webDriver.get().quit();
    }

    public void takeScreenshot(File destinationFile){
        try{
            File file = ((TakesScreenshot) webDriver.get()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,destinationFile);
            InputStream is =new FileInputStream(destinationFile);
            Allure.addAttachment("screenshot",is);
        }catch (IOException e){
            System.out.println();
        }
    }
}
