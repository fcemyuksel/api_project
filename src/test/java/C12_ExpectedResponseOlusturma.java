import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C12_ExpectedResponseOlusturma {

    @Test
    public void test01(){
         /*
        https://restful-booker.herokuapp.com/booking url’ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
        donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body
        {
        "firstname" : "Hasan",
        "lastname" : "Yagmur",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
            "checkin" : "2024-06-01",
            "checkout" : "2024-06-10"
            },
        "additionalneeds" : "wi-fi"
        }

        Expected Response Body
        {
        "bookingid":24,
        "booking":{
            "firstname":"Hasan",
            "lastname":"Yagmur",
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
        String endPoint= "https://restful-booker.herokuapp.com/booking";

         //POST request gonderecegimiz icin request body'e ihtiyacimiz var.

        JSONObject bookingdatesJson=new JSONObject();
        bookingdatesJson.put("checkin", "2024-06-01");
        bookingdatesJson.put("checkout", "2024-06-10");

        JSONObject requestBody=new JSONObject();
        requestBody.put("firstname", "Hasan");
        requestBody.put("lastname" , "Yagmur");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates",bookingdatesJson);
        requestBody.put("additionalneeds","wi-fi");

        //2- gorevde isteniyorsa expected body olustur

        JSONObject expectedResponse=new JSONObject();
        expectedResponse.put("bookingid",24);
        expectedResponse.put( "booking",requestBody);
        /*
        System.out.println("=======Request Body============");
        System.out.println(requestBody);

        System.out.println("=====Expected Response========");
        System.out.println(expectedResponse); */

        //3- requesti gonder ve donen actual response'i kaydet

        Response response=given().contentType(ContentType.JSON)
                             .when().body(requestBody.toString())
                             .post(endPoint);


        //4- istenen assertionlari yap

        //System.out.println(expectedResponse.getJSONObject("booking")
        //                                    .getJSONObject("bookingdates")
         //                                   .get("checkin"));

       //Response body turune sahip bir objede
        //alt attribute'lerin degerlerine ulasamayiz
        //bunun icin JSPath class'indan yardim isteriz

        JsonPath actualResponseJP=response.jsonPath();

        //System.out.println(actualResponseJP.getString("booking.firstname"));
        //System.out.println(actualResponseJP.getString("booking.bookingdates.checkin"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getString("firstname"),
              actualResponseJP.getString("booking.firstname"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getString("lastname"),
              actualResponseJP.getString("booking.lastname"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getInt("totalprice"),
              actualResponseJP.getInt("booking.totalprice"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getBoolean("depositpaid"),
                actualResponseJP.getBoolean("booking.depositpaid"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getJSONObject("bookingdates")
                        .getString("checkin"),
                actualResponseJP.getString("booking.bookingdates.checkin"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getJSONObject("bookingdates")
                        .getString("checkout"),
                actualResponseJP.getString("booking.bookingdates.checkout"));

      Assert.assertEquals(expectedResponse.getJSONObject("booking").getString("additionalneeds"),
                actualResponseJP.getString("booking.additionalneeds"));

    }
}
