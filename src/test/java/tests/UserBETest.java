package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.AddressModel;
import models.RequestUserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import sharedData.SharedData;

import static java.sql.DriverManager.getDriver;

public class UserBETest extends SharedData {

    @Test
    public void userTest(){
        RestAssured.baseURI="https://api.practicesoftwaretesting.com/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-type", "application/json");
        request.header("Accept", "application/json");

        AddressModel addressModel = new AddressModel("Street 1","City","State","Country","1234AA");
        RequestUserModel requestBody = new RequestUserModel("Ioan", "Popaluca",addressModel,"0987654321","1985-02-08","etwQu_@123","aapi@test.com");

        request.body(requestBody);
        Response response = request.post("/users/register");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201);


        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginProcess(requestBody.getEmail(),requestBody.getPassword());

    }



}
