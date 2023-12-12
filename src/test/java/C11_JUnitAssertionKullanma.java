import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class C11_JUnitAssertionKullanma {
    @Test
    public void test01(){

    /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda
        donen response body’sinin asagida verilen ile ayni oldugunu test ediniz

        Response body :

        {
        "userId":3,
        "id":22,
        "title":"dolor sint quo a velit explicabo quia nam",
        "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
     */

        //1- request hazirla: request body ve endpoint olustur
        String endPoint="https://jsonplaceholder.typicode.com/posts/22";

        //2- gorevde isteniyorsa expected body olustur

        JSONObject expectedResponse=new JSONObject();
        expectedResponse.put("userId",3);
        expectedResponse.put("id",22);
        expectedResponse.put("title","dolor sint quo a velit explicabo quia nam");
        expectedResponse.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        //3- requesti gonder ve donen actual response'i kaydet
        Response response=given()
                            .when()
                            .get(endPoint);
        //response.prettyPrint();
        //4- istenen assertionlari yap
        response.then().assertThat()
                .body("userId",equalTo(3),
                        "id",equalTo(22),
                        "title",equalTo("dolor sint quo a velit explicabo quia nam"));

        response
                .then()
                .assertThat()
                .body("userId",equalTo(expectedResponse.get("userId")),
                "id",equalTo(expectedResponse.get("id")),
                        "title", equalTo(expectedResponse.get("title")));


        //Server'in bize dondurdugu actual response'i biz "response" isi ile kaydettik
        // "response" objesi uzerindeki attribute'lerin degerlerini alabilmek icin
        // JSonPath class'indan yardim almamiz gerekiyor

        JsonPath actualResponseJP=response.jsonPath();

        Assert.assertEquals(expectedResponse.get("userId"),actualResponseJP.getInt("userId"));
        Assert.assertEquals(expectedResponse.get("id"),actualResponseJP.getInt("id"));
        Assert.assertEquals(expectedResponse.get("title"),actualResponseJP.getString("title"));
        Assert.assertEquals(expectedResponse.get("body"),actualResponseJP.getString("body"));

    }
}
