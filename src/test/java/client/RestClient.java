package client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    //trebuie sa fac doua actiuni pe aceasta clasa
    //trebuie sa configurez clientul
    //pe baza configurarilor sa pot executa orice actiune(get post put delete)

    public Response performRequest(String requestType, RequestSpecification request, String endpoint){
        switch (requestType){
            case "POST":
               return prepareClient(request).post(endpoint);
            case "PUT":
               return prepareClient(request).put(endpoint);
            case "GET":
               return prepareClient(request).get(endpoint);
            case "DELETE":
               return prepareClient(request).delete(endpoint);
        }


        return null;
    }

    public RequestSpecification prepareClient(RequestSpecification request){
        request.baseUri("https://api.practicesoftwaretesting.com");
        request.header("Content-type", "application/json");
        request.header("Accept", "application/json");
        return  request;
    }
}
