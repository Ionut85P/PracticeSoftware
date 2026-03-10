package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import types.EndpointType;
import types.RequestMethodType;
import types.ResponseStatusType;

public class ReportService {
    public void generateAverageSalesPerMonthReport(String token) {
        System.out.println("STEP2: GENERATE REPORT");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer" + token);
        Response response2 = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.REPORT_AVERAGE_SALES_ENDPOINT);
        System.out.println(response2.getStatusLine());
        response2.body().prettyPrint();
        Assert.assertEquals(response2.getStatusCode(), ResponseStatusType.RESPONSE_OK);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}