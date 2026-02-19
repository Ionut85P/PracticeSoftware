package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.RequestUserLoginModel;
import models.ResponseUserLoginModel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportBETest {
    @Test
    public void reportTest(){
        RestAssured.baseURI="https://api.practicesoftwaretesting.com/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-type", "application/json");
        request.header("Accept", "application/json");

        //pasul 1:ne logam cu ADMIN creat
        System.out.println("STEP 1: LOGIN USER ADMIN REQUEST ");

        RequestUserLoginModel requestBody = new RequestUserLoginModel("admin@practicesoftwaretesting.com","welcome01");
        request.body(requestBody);
        Response response = request.post("users/login");
        response.body().prettyPrint();
        ResponseUserLoginModel responseBody = response.getBody().as(ResponseUserLoginModel.class);
        Assert.assertEquals(response.getStatusCode(), 200);

        //Pasul 2 Geneream raportul de vanzari /luna

        System.out.println("STEP2: GENERATE REPORT");

        request.header("Authorization","Bearer" + responseBody.getAccess_token());

        Response response2 = request.get("/reports/average-sales-per-month");
        System.out.println(response2.getStatusLine());
        response2.body().prettyPrint();
        Assert.assertEquals(response2.getStatusCode(),200);
    }
}
