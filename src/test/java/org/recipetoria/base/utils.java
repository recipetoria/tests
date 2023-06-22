package org.recipetoria.base;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class utils extends TestBase {
    Faker faker = new Faker();
    public String generatePassword(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial) {
        if (includeSpecial) {
            char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase).toCharArray();
            char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
            for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
                password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
            }
            return new String(password);
        } else {
            return faker.lorem().characters(minimumLength, maximumLength, includeUppercase);
        }
    }

    public void writeLocalStorageData(){
        JavascriptExecutor jsExecuter = (JavascriptExecutor) getDriver();
        String localStoragedata = (String) jsExecuter.executeScript("return JSON.stringify(window.localStorage);");

        try {
            FileWriter writer = new FileWriter("src/test/resources/localStorageData.txt");
            writer.write(localStoragedata);
            writer.close();
            System.out.println("LocalStorage data saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLocalStorageDataAsJson(String localStorageData, String filePath) {
        try {
            // Convert the LocalStorage data to a JSON object
            Gson gson = new Gson();
            JsonObject localStorageObject = gson.fromJson(localStorageData, JsonObject.class);

            // Extract the value of the "authRegister" property
            String authRegisterData = localStorageObject.get("authRegister").getAsString();

            // Save the "authRegister" value to a file in JSON format
            FileWriter writer = new FileWriter(filePath);
            writer.write(authRegisterData);
            writer.close();

            System.out.println("LocalStorage data saved to " + filePath + " in JSON format.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLocalStorageFile(String filePath){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
