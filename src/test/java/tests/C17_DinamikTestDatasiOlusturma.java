package tests;

import BaseUrl.BaseUrlJasonplaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonplaceholder;

import static io.restassured.RestAssured.given;

public class C17_DinamikTestDatasiOlusturma extends BaseUrlJasonplaceHolder {
    @Test
    public void test01(){
 /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
        asagidaki body’e sahip bir PUT request yolladigimizda
        donen response’in
            status kodunun 200,
            content type’inin “application/json; charset=utf-8”,
            Connection header degerinin “keep-alive”
            ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
         Request Body
            {
            "title":"Cem",
            "body":"Merhaba",
            "userId":14,
            "id":70
            }
        Response body (Expected Data) :
            {
            "title":"Cem",
            "body":"Merhaba",
            "userId":14,
            "id":70
            }
         */

        //1- request hazirla: request body ve endpoint olustur
        JSONObject requestBody= TestDataJsonplaceholder.parametreIleBodyOlustur(14,70,"Cem","Merhaba");
        specJsonplaceholder.pathParams("pp1","posts","pp2","70");
        //2- gorevde isteniyorsa expected body olustur
        JSONObject expectedResponse=TestDataJsonplaceholder.parametreIleBodyOlustur(14,70,"Cem","Merhaba");
        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().spec(specJsonplaceholder).contentType(ContentType.JSON)
                                .when().body(requestBody.toString())
                                .put("/{pp1}/{pp2}");
        //response.prettyPrint();
        //4- istenen assertionlari yap

        Assert.assertEquals(TestDataJsonplaceholder.basariliResponseStatusCode,
                            response.statusCode());

        Assert.assertEquals(TestDataJsonplaceholder.contentType,
                            response.contentType());

        Assert.assertEquals(TestDataJsonplaceholder.connectionHeaderDegeri,
                            response.header("Connection"));

        //response bod'sindeki attribute degerlerini test edebilmek icin
        //responsi JsonPath objesine cevirmeliyiz

        JsonPath actualresponseBody=response.jsonPath();

        Assert.assertEquals(expectedResponse.getString("title"),
                            actualresponseBody.getString("title"));

        Assert.assertEquals(expectedResponse.getString("body"),
                            actualresponseBody.getString("body"));

        Assert.assertEquals(expectedResponse.getInt("userId"),
                            actualresponseBody.getInt("userId"));

        Assert.assertEquals(expectedResponse.getInt("id"),
                            actualresponseBody.getInt("id") );




    }
}
