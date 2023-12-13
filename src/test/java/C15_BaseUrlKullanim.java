import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_BaseUrlKullanim extends BaseUrlHerokuapp {

    // 1- https://restful-booker.herokuapp.com/booking endpointine
    //    bir GET request gonderdigimizde
    //    donen response’un status code’unun 200 oldugunu
    //    ve Response’ta 10'dan fazla booking oldugunu test edin

    @Test
    public void test01() {
        specHerokuapp.pathParam("pp1", "booking");

        Response response = given()
                .when().spec(specHerokuapp)
                .get("/{pp1}");

        JsonPath actualresponseJP = response.jsonPath();
        // System.out.println(actualresponseJP.getList("bookingid").size());

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(actualresponseJP.getList("bookingid").size() > 10);
    }

    // 2- https://restful-booker.herokuapp.com/booking endpointine
    //    yandaki body’ye sahip bir POST request gonderdigimizde
    //    donen response’un status code’unun 200 oldugunu
    //    ve “firstname” degerinin “Cem” oldugunu test edin

    /*
         {
            "firstname" : "Cem",
            "lastname" : “Yuksel",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                   "checkin" : "2024-06-01",
                   "checkout" : "2024-06-10"
                      },
            "additionalneeds" : "wi-fi" }
     */
    @Test
    public void test02() {

        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody=new JSONObject();
        JSONObject bookingJsonObj=new JSONObject();

        //once icerideki JSon Objeyi tamamlayalim
        bookingJsonObj.put("checkin", "2024-01-10");
        bookingJsonObj.put("checkout", "2024-01-20");

        requestBody.put("firstname","Cem");
        requestBody.put("lastname","Yuksel");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingJsonObj);
        requestBody.put("additionalneeds","wi-fi");

        Response response=given().contentType(ContentType.JSON)
                            .when().spec(specHerokuapp).body(requestBody.toString())
                            .post("/{pp1}");

        JsonPath actualresponseJP=response.jsonPath();

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("Cem",actualresponseJP
                .getString("booking.firstname"));


    }
}
