package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;

public class UserService {

    //aceasta clasa reprezinta metodele de la serviciul "user" de pe swagger

    public ResponseUserModel createUser(RequestUserModel requestBody){
        System.out.println("STEP 1: CREATE USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);

        Response response = performRequest("POST",request,"/users/register");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201);
        return response.getBody().as(ResponseUserModel.class);

    }
    public ResponseUserLoginModel loginUser(RequestUserModel requestBody){
        System.out.println("STEP 2: LOGIN USER REQUEST ");
        RequestUserLoginModel requestLoginBody = new RequestUserLoginModel(requestBody.getEmail(),requestBody.getPassword());
        RequestSpecification request = RestAssured.given();
        request.body(requestLoginBody);

        Response response = performRequest("POST", request,"users/login");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        return response.getBody().as(ResponseUserLoginModel.class);

    }

    public void checkUser(String token, String userID, int statusCode){
        System.out.println("STEP 3:CHECK USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer" + token);

        Response response3 = performRequest("GET", request, "users/"+ userID);
        System.out.println(response3.getStatusLine());
        response3.body().prettyPrint();
        Assert.assertEquals(response3.getStatusCode(),statusCode);

    }

    public void logoutUser(String token){
        System.out.println("STEP 4:LOGOUT USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer" + token);

        Response response4 = performRequest("GET",request, "users/logout");
        System.out.println(response4.getStatusLine());
        response4.body().prettyPrint();
        Assert.assertEquals(response4.getStatusCode(),200);
    }

    public ResponseUserLoginModel loginUser(RequestUserLoginModel requestBody){
        System.out.println("STEP 5: LOGIN USER REQUEST ");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);

        Response response = performRequest("POST", request,"users/login");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        return response.getBody().as(ResponseUserLoginModel.class);

    }
    public void deleteUser(String token, String userID){
        System.out.println("STEP 6:DELETE USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer" + token);

        Response response6 = performRequest("DELETE", request, "users/"+userID);
        System.out.println(response6.getStatusLine());
        response6.body().prettyPrint();
        Assert.assertEquals(response6.getStatusCode(),204);


    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint){
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}
