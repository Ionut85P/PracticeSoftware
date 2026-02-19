package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrandBETest {

    @Test
    public void brandTest() {
        RestAssured.baseURI = "https://api.practicesoftwaretesting.com/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-type", "application/json");
        request.header("Accept", "application/json");

        //Pasul 1: creem un brand
        System.out.println("STEP 1:CREATE NEW BRAND");

        RequestBrandModel requestBody = new RequestBrandModel("Brand", "Testing");

        request.body(requestBody);
        Response response = request.post("/brands");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        ResponseBrandModel responseBody = response.getBody().as(ResponseBrandModel.class);
        Assert.assertEquals(response.getStatusCode(), 201);


        //Pasul 2 verificam ca s-a creat brandul
        System.out.println("STEP 2:CHECK BRAND REQUEST");

        Response response2 = request.get("/brands/"+responseBody.getId());
        System.out.println(response2.getStatusLine());
        response2.body().prettyPrint();
        Assert.assertEquals(response2.getStatusCode(),200);

        //Pasul 3 modificam un brand

        System.out.println("STEP 3: UPDATE BRAND");

        RequestBrandModel requestBody3 = new RequestBrandModel("Ioan", "Test");

        request.body(requestBody3);
        Response response3 = request.put("/brands/"+responseBody.getId());
        System.out.println(response3.getStatusLine());
        response3.body().prettyPrint();
        Assert.assertEquals(response3.getStatusCode(), 200);

        //Pasul 4 verificam ca s-a modificat brandul
        System.out.println("STEP 4:CHECK BRAND REQUEST");

        Response response4 = request.get("/brands/"+responseBody.getId());
        System.out.println(response4.getStatusLine());
        response4.body().prettyPrint();
        Assert.assertEquals(response4.getStatusCode(),200);

        //pasul 5:ne logam cu ADMIN creat
        System.out.println("STEP 5: LOGIN USER ADMIN REQUEST ");

        RequestUserLoginModel requestBody5 = new RequestUserLoginModel("admin@practicesoftwaretesting.com","welcome01");
        request.body(requestBody5);
        Response response5 = request.post("users/login");
        response5.body().prettyPrint();
        ResponseUserLoginModel responseBody5 = response5.getBody().as(ResponseUserLoginModel.class);
        Assert.assertEquals(response5.getStatusCode(), 200);

        //Pasul 6: stergem brandul

        System.out.println("STEP 6:DELETE BRAND REQUEST");

        request.header("Authorization","Bearer" + responseBody5.getAccess_token());
        Response response6 = request.delete("/brands/"+responseBody.getId());
        System.out.println(response6.getStatusLine());
        response6.body().prettyPrint();
        Assert.assertEquals(response6.getStatusCode(),204);

        //Pasul 7: verificam ca brandul s-a sters
        System.out.println("STEP 7:CHECK BRAND REQUEST");

        Response response7 = request.get("/brands/"+responseBody.getId());
        System.out.println(response7.getStatusLine());
        response7.body().prettyPrint();
        Assert.assertEquals(response7.getStatusCode(),404);

    }
}
