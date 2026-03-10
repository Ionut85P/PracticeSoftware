package tests;

import models.RequestBrandModel;
import models.RequestUserLoginModel;
import models.ResponseBrandModel;
import models.ResponseUserLoginModel;
import org.testng.annotations.Test;
import services.BrandService;
import services.UserService;
import types.ResponseStatusType;

public class BrandBETest {

    @Test
    public void brandTest() {


        //Pasul 1: creem un brand
        System.out.println("STEP 1:CREATE NEW BRAND");
        RequestBrandModel requestBody = new RequestBrandModel("Brand", "Testing");
        BrandService brandService = new BrandService();
        ResponseBrandModel responseBody = brandService.createBrand(requestBody);


        //Pasul 2 verificam ca s-a creat brandul
        brandService.checkSpecificBrand(responseBody.getId(), ResponseStatusType.RESPONSE_OK);

        //Pasul 3 modificam un brand

        RequestBrandModel requestBody3 = new RequestBrandModel("Ioan", "Test");
        brandService.modifySpecificBrand(requestBody3,responseBody.getId());

        //Pasul 4 verificam ca s-a modificat brandul
        brandService.checkSpecificBrand(responseBody.getId(), ResponseStatusType.RESPONSE_OK);


        //pasul 5:ne logam cu ADMIN creat
        UserService userService=new UserService();
        RequestUserLoginModel requestAdminBody = new RequestUserLoginModel("admin@practicesoftwaretesting.com","welcome01");

        ResponseUserLoginModel responseAdminBody= userService.loginUser(requestAdminBody);


        //Pasul 6: stergem brandul
        brandService.deleteSpecificBrand(responseAdminBody.getAccess_token(), responseBody.getId());


        //Pasul 7: verificam ca brandul s-a sters
        brandService.checkSpecificBrand(responseBody.getId(), ResponseStatusType.RESPONSE_NOT_FOUND);


    }
}
