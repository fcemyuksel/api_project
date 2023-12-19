package tests;

import BaseUrl.BaseUrlJasonplaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonplaceholder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C20_APITestindeMapKullanimi extends BaseUrlJasonplaceHolder {

    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
    asagidaki body’e sahip bir PUT request yolladigimizda
    donen response’in response body’sinin asagida verilen ile ayni oldugunu test ediniz

        Request Body

        {
            "title":"Cem",
            "body":"Yuksel",
            "userId":10,
            "id":70
        }

        Expected Response Body:

        {
            "title":"Cem",
            "body":"Yuksel",
            "userId":10,
            "id":70
        }
         */

        //1- request hazirla: request body ve endpoint olustur
        specJsonplaceholder.pathParams("pp1","posts","pp2","70");
        Map<String ,Object> requestBodyMap= TestDataJsonplaceholder
                                    .requestBodyMapOlustur(10,70,"Cem","Yuksel");

        //2- gorevde isteniyorsa expected body olustur
        Map<String ,Object> expectedResponseMap=TestDataJsonplaceholder
                            .reeponseBodyMapOlustur(10.0,70.0,"Cem","Yuksel");

        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().spec(specJsonplaceholder).contentType(ContentType.JSON)
                                .when().body(requestBodyMap)
                                .put("/{pp1}/{pp2}");
        //response.prettyPrint();
        //4- istenen assertionlari yap

        //expected data <===> actual response objesi
        //   Map                    Response
        //actual response objesini de Map'e cevirmeliyiz

        Map<String,Object > actualResponseMap=response.as(HashMap.class);
        //System.out.println(actualResponseMap);

        //expected data <===> actual response objesi
        //   Map                    Map

        Assert.assertEquals(expectedResponseMap.get("id"),
                (Double)actualResponseMap.get("id"));

       Assert.assertEquals(expectedResponseMap.get("title"),
                            actualResponseMap.get("title"));

       Assert.assertEquals(expectedResponseMap.get("body"),
               actualResponseMap.get("body"));

        Assert.assertEquals(expectedResponseMap.get("userId"),
                actualResponseMap.get("userId"));
    }
}
