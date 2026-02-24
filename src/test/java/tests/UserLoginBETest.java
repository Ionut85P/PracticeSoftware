package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import services.UserService;
import sharedData.SharedData;

public class UserLoginBETest extends SharedData {
    @Test
    public void userTest(){


        //pasul 1 : creem un nou user

        AddressModel addressModel = new AddressModel("Street 1","City","State","Country","1234AA");
        RequestUserModel requestBody = new RequestUserModel("Ioan", "Popaluca",addressModel,"0987654321","1985-02-08","etwQu_@123","aapi@test.com");

        UserService userService = new UserService();
       ResponseUserModel responseBody = userService.createUser(requestBody);



        //pasul 2:ne logam cu userul creat

        ResponseUserLoginModel responseLoginBody= userService.loginUser(requestBody);

        //pasu 3  verificam ca s-a creat userul


        userService.checkUser(responseLoginBody.getAccess_token(),responseBody.getId(),200);

     //pasu 4 delogam userul

        userService.logoutUser(responseLoginBody.getAccess_token());

        //pasul 5:ne logam cu ADMIN creat

        RequestUserLoginModel requestAdminBody = new RequestUserLoginModel("admin@practicesoftwaretesting.com","welcome01");

        ResponseUserLoginModel responseAdminBody= userService.loginUser(requestAdminBody);

        // Pasul 6 stergem userul


        userService.deleteUser(responseAdminBody.getAccess_token(), responseBody.getId());


        // verificam ca  userul s-a sters

        userService.checkUser(responseLoginBody.getAccess_token(),responseBody.getId(),404);

    }
}
