package tests;

import models.RequestUserLoginModel;
import models.ResponseUserLoginModel;
import org.testng.annotations.Test;
import services.ReportServices;
import services.UserService;

public class ReportBETest {
    @Test
    public void reportTest(){

        //pasul 1:ne logam cu ADMIN creat
        System.out.println("STEP 1: LOGIN USER ADMIN REQUEST ");

        RequestUserLoginModel requestBody = new RequestUserLoginModel("admin@practicesoftwaretesting.com","welcome01");
        UserService userService = new UserService();
        ResponseUserLoginModel responseBody = userService.loginUser(requestBody);
        //Pasul 2 Geneream raportul de vanzari /luna
        ReportServices reportService = new ReportServices();
        reportService.generateAverageSalesPerMonthReport(responseBody.getAccess_token());

    }

}
