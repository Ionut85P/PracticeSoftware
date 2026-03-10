package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import types.EndpointType;
import types.RequestMethodType;
import types.ResponseStatusType;

public class ImageService {

    public void obtainAllImages (){

        System.out.println("STEP 1 :GET ALL IMAGES");
        RequestSpecification request  = RestAssured.given();
        Response response = performRequest(RequestMethodType.REQUEST_GET,request, EndpointType.IMAGE_GET_ALL_ENDPOINT);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_OK);
    }


        private Response performRequest(String requestType, RequestSpecification request, String endpoint){
            return new RestClient().performRequest(requestType, request, endpoint);

    }
}
