package tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataHerokuapp;

import static io.restassured.RestAssured.given;

public class C18_TestDataKullanimi extends BaseUrlHerokuapp {
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
               Expected response body
                        {
                         "bookingid":24,
                         "booking":{
                            "firstname":"Cem",
                            "lastname":"Yuksel",
                            "totalprice":500,
                            "depositpaid":false,
                            "bookingdates":{
                                    "checkin":"2024-06-01",
                                    "checkout":"2024-06-10"
                                            },
                            "additionalneeds":"wi-fi"
                                    }
                          }
         */

        //1- request hazirla: request body ve endpoint olustur

        specHerokuapp.pathParam("pp1","booking");
        JSONObject requestBody= TestDataHerokuapp.parametreIleRequestBodyOlustur("Cem","Yuksel",500,
                false,"2024-06-01","2024-06-10","wi-fi");

        //2- gorevde isteniyorsa expected body olustur
        JSONObject expectedResponse=TestDataHerokuapp.parametreIleResponseBodyOlustur(24,"Cem","Yuksel",500,
                false,"2024-06-01","2024-06-10","wi-fi");

        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().spec(specHerokuapp).contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post("/{pp1}");

        //response.prettyPrint();
        //4- istenen assertionlari yap
        //body'deki attribute test edilecegi icin
        // response'ı JsonPath objesine cevirelim

        JsonPath actualResponseJP=response.jsonPath();

        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                                            .getString("firstname"),
                actualResponseJP.getString("booking.firstname"));

        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                                            .getString("lastname"),
                actualResponseJP.getString("booking.lastname"));

        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                                    .getInt("totalprice"),
                actualResponseJP.getInt("booking.totalprice"));


        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                                            .getBoolean("depositpaid"),
                actualResponseJP.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                                            .getJSONObject("bookingdates")
                                            .getString("checkin"),
                actualResponseJP.getString("booking.bookingdates.checkin"));

        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                        .getJSONObject("bookingdates")
                        .getString("checkout"),
                actualResponseJP.getString("booking.bookingdates.checkout"));

        Assert.assertEquals(expectedResponse.getJSONObject("booking")
                                            .getString("additionalneeds"),
                actualResponseJP.getString("booking.additionalneeds"));






    }
}













