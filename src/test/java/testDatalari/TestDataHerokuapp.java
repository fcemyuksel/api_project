package testDatalari;

import org.json.JSONObject;

public class TestDataHerokuapp {

    public static JSONObject parametreIleRequestBodyOlustur(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds ){

        JSONObject bookingdatesJson=new JSONObject();
        bookingdatesJson.put("checkin",checkin);
        bookingdatesJson.put("checkout",checkout);

        JSONObject requestBody=new JSONObject();
        requestBody.put("firstname",firstname);
        requestBody.put("lastname",lastname);
        requestBody.put("totalprice",totalprice);
        requestBody.put("depositpaid",depositpaid);
        requestBody.put("bookingdates",bookingdatesJson);
        requestBody.put("additionalneeds",additionalneeds);
    return requestBody;


    }

        public static JSONObject parametreIleResponseBodyOlustur(int bookingId,String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds ){

            JSONObject bookingdatesJson=new JSONObject();
            bookingdatesJson.put("checkin",checkin);
            bookingdatesJson.put("checkout",checkout);

            JSONObject bookingJson=new JSONObject();
            bookingJson.put("firstname",firstname);
            bookingJson.put("lastname",lastname);
            bookingJson.put("totalprice",totalprice);
            bookingJson.put("depositpaid",depositpaid);
            bookingJson.put("bookingdates",bookingdatesJson);
            bookingJson.put("additionalneeds",additionalneeds);

        JSONObject responseBody=new JSONObject();
        responseBody.put("bookingId",24);
        responseBody.put("booking",bookingJson);

        return responseBody;

        }

}
