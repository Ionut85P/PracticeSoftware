package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import sharedData.SharedData;

public class UserLoginBETest extends SharedData {
    @Test
    public void userTest(){
        RestAssured.baseURI="https://api.practicesoftwaretesting.com/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-type", "application/json");
        request.header("Accept", "application/json");

        //pasul 1 : creem un nou user

        AddressModel addressModel = new AddressModel("Street 1","City","State","Country","1234AA");
        RequestUserModel requestBody = new RequestUserModel("Ioan", "Popaluca",addressModel,"0987654321","1985-02-08","etwQu_@123","aapi@test.com");

        request.body(requestBody);
        Response response = request.post("/users/register");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        ResponseUserModel responseBody = response.getBody().as(ResponseUserModel.class);
        Assert.assertEquals(response.getStatusCode(), 201);


        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginProcess(requestBody.getEmail(),requestBody.getPassword());

        //pasul 2:ne logam cu userul creat

        RequestUserLoginModel requestBody2 = new RequestUserLoginModel(requestBody.getEmail(),requestBody.getPassword());
        request.body(requestBody2);
        Response response2 = request.post("users/login");
        response2.body().prettyPrint();
        ResponseUserLoginModel responseBody2 = response2.getBody().as(ResponseUserLoginModel.class);
        Assert.assertEquals(response2.getStatusCode(), 200);

        // verificam ca s-a creat userul
        request.header("Authorization","Bearer" + responseBody2.getAccess_token());
        Response response3 = request.get("users/"+responseBody.getId());
        System.out.println(response3.getStatusLine());
        response3.body().prettyPrint();
        Assert.assertEquals(response3.getStatusCode(),200);

    }
}
