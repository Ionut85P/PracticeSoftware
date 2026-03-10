package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import types.EndpointType;
import types.RequestMethodType;
import types.ResponseStatusType;

public class UserService {

    //aceasta clasa reprezinta metodele de la serviciul "user" de pe swagger

    public ResponseUserModel createUser(RequestUserModel requestBody){
        System.out.println("STEP 1: CREATE USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);

        Response response = performRequest(RequestMethodType.REQUEST_POST,request, EndpointType.USER_CREATE_ENDPOINT);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_CREATED);
        return response.getBody().as(ResponseUserModel.class);

    }
    public ResponseUserLoginModel loginUser(RequestUserModel requestBody){
        System.out.println("STEP 2: LOGIN USER REQUEST ");
        RequestUserLoginModel requestLoginBody = new RequestUserLoginModel(requestBody.getEmail(),requestBody.getPassword());
        RequestSpecification request = RestAssured.given();
        request.body(requestLoginBody);

        Response response = performRequest(RequestMethodType.REQUEST_POST, request,EndpointType.USER_LOGIN_ENDPOINT);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_OK);
        return response.getBody().as(ResponseUserLoginModel.class);

    }

    public void checkUser(String token, String userID, int statusCode){
        System.out.println("STEP 3:CHECK USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer" + token);

        Response response3 = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.USER_SPECIFIC_ENDPOINT+ userID);
        System.out.println(response3.getStatusLine());
        response3.body().prettyPrint();
        Assert.assertEquals(response3.getStatusCode(),statusCode);

    }

    public void logoutUser(String token){
        System.out.println("STEP 4:LOGOUT USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer" + token);

        Response response4 = performRequest(RequestMethodType.REQUEST_GET,request, EndpointType.USER_LOGOUT_ENDPOINT);
        System.out.println(response4.getStatusLine());
        response4.body().prettyPrint();
        Assert.assertEquals(response4.getStatusCode(),ResponseStatusType.RESPONSE_OK);
    }

    public ResponseUserLoginModel loginUser(RequestUserLoginModel requestBody){
        System.out.println("STEP 5: LOGIN USER REQUEST ");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);

        Response response = performRequest(RequestMethodType.REQUEST_POST, request,EndpointType.USER_LOGIN_ENDPOINT);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_OK);
        return response.getBody().as(ResponseUserLoginModel.class);

    }
    public void deleteUser(String token, String userID){
        System.out.println("STEP 6:DELETE USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer" + token);

        Response response6 = performRequest(RequestMethodType.REQUEST_DELETE, request, EndpointType.USER_SPECIFIC_ENDPOINT+userID);
        System.out.println(response6.getStatusLine());
        response6.body().prettyPrint();
        Assert.assertEquals(response6.getStatusCode(),ResponseStatusType.RESPONSE_NO_CONTENT);


    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint){
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}
