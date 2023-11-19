import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_ResponseBodyTestEtme {
    @Test
    public void test01(){
        /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
        donen Response’in
        status code'unun 200,
        ve content type'inin Aplication.JSON,
        ve response body'sinde bulunan userId'nin 5,
        ve response body'sinde bulunan title'in "optio dolor molestias sit"
        oldugunu test edin.
         */

        //1- request hazirla: request body ve endpoint olustur
        String endpoint="https://jsonplaceholder.typicode.com/posts/44";
        //2- gorevde isteniyorsa expected body olustur
        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().when().get(endpoint);
        //4- istenen assertionlari yap
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")  //.contentType(ContentType.JSON)     da kullanilabilir

                .body("userId", Matchers.equalTo(5))
                .body("title",Matchers.equalTo("optio dolor molestias sit"));


    }
}
