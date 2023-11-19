import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C06_ResponseBodyTestEtme {
    @Test
    public void test01(){
        /*
https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde
donen Response’un,
status code’unun 200,
ve content type’inin application-json,
ve response body’sindeki
"firstname“in, "Susan",
ve "lastname“in, "Jackson",
ve "totalprice“in, 612,
ve "depositpaid“in, false,
ve "additionalneeds“in, "Breakfast"
oldugunu test edin
         */

        //1- request hazirla: request body ve endpoint olustur
        String endpoint="https://restful-booker.herokuapp.com/booking/10";
        //2- gorevde isteniyorsa expected body olustur
        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().when().get(endpoint);
        //4- istenen assertionlari yap

        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Susan"))
                .body("lastname", equalTo("Ericsson"))
                .body("totalprice",lessThan(1000))
                .body("depositpaid",equalTo(false))
                .body("additionalneeds",Matchers.notNullValue());




    }
}
