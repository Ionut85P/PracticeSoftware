package tests;

import io.restassured.response.Response;
import models.RequestBrandModel;
import models.RequestUserLoginModel;
import models.ResponseBrandModel;
import models.ResponseUserLoginModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.BrandService;

public class BrandBETest {

    @Test
    public void brandTest() {


        //Pasul 1: creem un brand
        System.out.println("STEP 1:CREATE NEW BRAND");
        RequestBrandModel requestBody = new RequestBrandModel("Brand", "Testing");
        BrandService brandService = new BrandService();
        ResponseBrandModel responseBody = brandService.createBrand(requestBody);


        //Pasul 2 verificam ca s-a creat brandul
        brandService.checkSpecificBrand(responseBody.getId());

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
