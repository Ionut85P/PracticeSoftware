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
   public void checkSpecificBrand(String brandID, int statusCode){
       System.out.println("STEP 2:CHECK BRAND REQUEST");
       RequestSpecification request  = RestAssured.given();
       Response response2 = performRequest("GET", request,"/brands/"+ brandID);
       System.out.println(response2.getStatusLine());
       response2.body().prettyPrint();
       Assert.assertEquals(response2.getStatusCode(),statusCode);
   }

   public void modifySpecificBrand(RequestBrandModel requestBody, String brandID){
       System.out.println("STEP 3: UPDATE BRAND");
       RequestSpecification request  = RestAssured.given();
       request.body(requestBody);
       Response response3 = performRequest("PUT", request,"/brands/"+brandID);
       System.out.println(response3.getStatusLine());
       response3.body().prettyPrint();
       Assert.assertEquals(response3.getStatusCode(), 200);
   }

   public void deleteSpecificBrand(String token, String brandID){
       System.out.println("STEP 6:DELETE BRAND REQUEST");
       RequestSpecification request  = RestAssured.given();
       request.header("Authorization","Bearer" + token);
       Response response6 = performRequest("DELETE",request,"/brands/"+brandID);
       System.out.println(response6.getStatusLine());
       response6.body().prettyPrint();
       Assert.assertEquals(response6.getStatusCode(),204);

   }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint){
        return new RestClient().performRequest(requestType, request, endpoint);

    }
}
