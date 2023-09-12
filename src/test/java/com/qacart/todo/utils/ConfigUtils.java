package com.qacart.todo.utils;

import java.io.*;
import java.util.Properties;

public class ConfigUtils {
    //since we want to read the properties only once we can use singleton pattern

    private static ConfigUtils configUtils;

    private Properties properties;
    private ConfigUtils() {
        properties=readPropertyFromFile();
    }

    public static ConfigUtils getInstance(){
        if(configUtils==null){
            configUtils=new ConfigUtils();
        }
        return configUtils;
    }

    private Properties readPropertyFromFile() {
        InputStream inputStream = null;
        try {
            //mvn clean test -Dbrowser=CHROME -Denv=production
            String env=System.getProperty("env","production");
            inputStream = new FileInputStream("src/test/resources/env/"+env+".properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public String getBaseUrl(){
        return properties.get("url").toString();
    }
}
