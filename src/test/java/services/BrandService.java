package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.RequestBrandModel;
import models.ResponseBrandModel;
import org.testng.Assert;

public class BrandService {
   public ResponseBrandModel createBrand(RequestBrandModel requestBody){
       System.out.println("STEP 1:CREATE NEW BRAND");
       RequestSpecification request  = RestAssured.given();
       request.body(requestBody);
       Response response = performRequest("POST",request,"/brands");
       System.out.println(response.getStatusLine());
       response.body().prettyPrint();
              Assert.assertEquals(response.getStatusCode(), 201);
       return response.getBody().as(ResponseBrandModel.class);


   }
   public void checkSpecificBrand(String brandID){
       System.out.println("STEP 2:CHECK BRAND REQUEST");
       RequestSpecification request  = RestAssured.given();

       Response response2 = performRequest("GET", request,"/brands/"+ brandID);
       System.out.println(response2.getStatusLine());
       response2.body().prettyPrint();
       Assert.assertEquals(response2.getStatusCode(),200);
   }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint){
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}
