import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_ResponseBodysiniTEstEtme {
    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki Json formatindaki body ile bir
PUT request gonderdigimizde
{
"title": "Ahmet",
"body": "Merhaba",
"userId": 10,
"id": 70
}
donen Response’un,
status code’unun 200,
ve content type’inin application/json; charset=utf-8,
ve Server isimli Header’in degerinin cloudflare,
ve status Line’in HTTP/1.1 200 OK
         */

        //1- request hazirla: request body ve endpoint olustur
        String endpoint="https://jsonplaceholder.typicode.com/posts/70";
        JSONObject requestBody=new JSONObject();
        requestBody.put("title", "Ahmet");
        requestBody.put("body", "Merhaba");
        requestBody.put("userId", 10);
        requestBody.put("id", 70);

        //System.out.println(requestBody);

        //2- gorevde isteniyorsa expected body olustur
        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given().contentType(ContentType.JSON)
                //given'da yapacagimiz sorgu icin on bilgiler
                        .when().body(requestBody.toString())
                //when ile birlikte yapacagimiz sorgu icin body gerekiyorsa String olarak yazilir
                        .put(endpoint);
                //kullanacagimiz http methodu
                //GET ve DELETE icin body gerekmez
                //PUT, POST, PATCH icin body GEREKLİDİR

       // response.prettyPrint();  YAZDIRMAK TERCİH EDİLMEZ

        //4- istenen assertionlari yap

        response.then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");

    }
}
