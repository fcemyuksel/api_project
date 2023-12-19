package tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C21_De_Serialization extends BaseUrlHerokuapp {

    @Test
    public void test01(){

         /*
        https://restful-booker.herokuapp.com/booking url’ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
        donen response’un id haric asagidaki gibi oldugunu test edin.
                            Request body
                       {
                            "firstname" : "Cem",
                            "lastname" : “Yuksel",
                            "totalprice" : 500,
                            "depositpaid" : false,
                            "bookingdates" : {
                                     "checkin" : "2024-06-01",
                                     "checkout" : "2024-06-10"
                                              },
                            "additionalneeds" : "wi-fi"
                        }
                            Response Body // expected data
                        {
                        "bookingid":24,
                        "booking":{
                            "firstname" : "Cem",
                            "lastname" : “Yuksel",
                            "totalprice" : 500,
                            "depositpaid" : false,
                            "bookingdates" : {
                                     "checkin" : "2024-06-01",
                                     "checkout" : "2024-06-10"
                            ,
                            "additionalneeds":"wi-fi"
                        }}
         */

        //1- request hazirla: request body ve endpoint olustur
        specHerokuapp.pathParam("pp1","booking");
        Map<String ,Object> requestBodyMap= TestDataHerokuapp
                    .requestBodMapOlustur("Cem","Yuksel",500,false,"2024-06-01","2024-06-10","wi-fi");

        //2- gorevde isteniyorsa expected body olustur
        Map<String,Object> expectedResponseMap=
                TestDataHerokuapp.responseBodyMap(24.0,"Cem","Yuksel",500.0,false,"2024-06-01","2024-06-10","wi-fi");
        System.out.println("expected response"+ expectedResponseMap);
        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBodyMap)
                            .post("/{pp1}");
        //4- istenen assertionlari yap
        //expectedResponseMap <===>  response

        Map<String,Object> actualResponseMap=response.as(HashMap.class);
        //System.out.println(actualResponseMap);

        String expectedFirstName=(String) ((Map<String,Object>)expectedResponseMap.get("booking")).get("firstname");
        String actualFirstName= (String) ((Map<String,Object>)actualResponseMap.get("booking")).get("firstname");

        Assert.assertEquals(expectedFirstName,actualFirstName);

        String expectedCheckin=((Map<String ,String >)(((Map<String, Object>) expectedResponseMap.get("booking")).get("bookingdates"))).get("checkin");
        String actualCheckin=((Map<String ,String >)(((Map<String, Object>) actualResponseMap.get("booking")).get("bookingdates"))).get("checkin");

        Assert.assertEquals(expectedCheckin,actualCheckin);


    }
}
