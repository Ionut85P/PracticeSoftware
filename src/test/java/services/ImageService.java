package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class ImageService {

    public void obtainAllImages (){

        System.out.println("STEP 1 :GET ALL IMAGES");
        RequestSpecification request  = RestAssured.given();
        Response response = request.get("/images");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
    }


        private Response performRequest(String requestType, RequestSpecification request, String endpoint){
            return new RestClient().performRequest(requestType, request, endpoint);

    }
}
