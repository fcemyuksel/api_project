import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C09_ResponseBodyTestiListKullanimi {
   @Test
    public void test01(){
        //    http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
        //    donen Response'in
        //    status code'unun 200,
        //    ve content type'inin Aplication.JSON,
        //    ve response body'sindeki
        //    employees sayisinin 24
        //    ve employee'lerden birinin "Ashton Cox"
        //    ve girilen yaslar icinde 61,66 ve 22 degerinin oldugunu
        //    test edin.

        //1- request hazirla: request body ve endpoint olustur
        String endPoint="http://dummy.restapiexample.com/api/v1/employees";

        //2- gorevde isteniyorsa expected body olustur
        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given()
                .when()
                .get(endPoint);

        response.prettyPrint();
        //4- istenen assertionlari yap
       response.then().assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .body("data.id", Matchers.hasSize(24))
               .body("data.employee_name",Matchers.hasItem("Ashton Cox"))
               .body("data.employee_age",Matchers.hasItems(61,66,22));

    }
}
