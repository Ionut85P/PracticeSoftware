package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ImageService;

public class ImageBETest {
    @Test
    public void testMethod(){
        RestAssured.baseURI="https://api.practicesoftwaretesting.com/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-type", "application/json");
        request.header("Accept", "application/json");

        //Pasul 1 Accesam toate imaginile
        ImageService imageService = new ImageService();
        imageService.obtainAllImages();
    }
}
