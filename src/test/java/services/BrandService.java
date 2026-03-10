package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.RequestBrandModel;
import models.ResponseBrandModel;
import org.testng.Assert;
import types.EndpointType;
import types.RequestMethodType;
import types.ResponseStatusType;

public class BrandService {
   public ResponseBrandModel createBrand(RequestBrandModel requestBody){
       System.out.println("STEP 1:CREATE NEW BRAND");
       RequestSpecification request  = RestAssured.given();
       request.body(requestBody);
       Response response = performRequest(RequestMethodType.REQUEST_POST,request,EndpointType.BRAND_CREATE_ENDPOINT);
       System.out.println(response.getStatusLine());
       response.body().prettyPrint();
              Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_CREATED);
       return response.getBody().as(ResponseBrandModel.class);


   }
   public void checkSpecificBrand(String brandID, int statusCode){
       System.out.println("STEP 2:CHECK BRAND REQUEST");
       RequestSpecification request  = RestAssured.given();
       Response response2 = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.BRAND_REQUEST_ENDPOINT + brandID);
       System.out.println(response2.getStatusLine());
       response2.body().prettyPrint();
       Assert.assertEquals(response2.getStatusCode(),statusCode);
   }

   public void modifySpecificBrand(RequestBrandModel requestBody, String brandID){
       System.out.println("STEP 3: UPDATE BRAND");
       RequestSpecification request  = RestAssured.given();
       request.body(requestBody);
       Response response3 = performRequest(RequestMethodType.REQUEST_PUT, request,EndpointType.BRAND_REQUEST_ENDPOINT+brandID);
       System.out.println(response3.getStatusLine());
       response3.body().prettyPrint();
       Assert.assertEquals(response3.getStatusCode(), ResponseStatusType.RESPONSE_OK);
   }

   public void deleteSpecificBrand(String token, String brandID){
       System.out.println("STEP 6:DELETE BRAND REQUEST");
       RequestSpecification request  = RestAssured.given();
       request.header("Authorization","Bearer" + token);
       Response response6 = performRequest(RequestMethodType.REQUEST_DELETE,request,EndpointType.BRAND_REQUEST_ENDPOINT+brandID);
       System.out.println(response6.getStatusLine());
       response6.body().prettyPrint();
       Assert.assertEquals(response6.getStatusCode(),ResponseStatusType.RESPONSE_NO_CONTENT);

   }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint){
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}
