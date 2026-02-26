package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class ReportServices {
    public void generateAverageSalesPerMonthReport(String token) {
        System.out.println("STEP2: GENERATE REPORT");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer" + token);
        Response response2 = performRequest("GET", request, "/reports/average-sales-per-month");
        System.out.println(response2.getStatusLine());
        response2.body().prettyPrint();
        Assert.assertEquals(response2.getStatusCode(), 200);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}