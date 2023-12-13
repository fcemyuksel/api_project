import BaseUrl.BaseUrlJasonplaceHolder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C14_BaseUrlKullanimi extends BaseUrlJasonplaceHolder {
    //Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin
    //1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET request gonderdigimizde donen response’un status code’unun 200 oldugunu ve Response’ta 100 kayit oldugunu test edin
    //2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET request gonderdigimizde donen response’un status code’unun 200 oldugunu ve “title” degerinin “optio dolor molestias sit” oldugunu test edin
    //3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE request gonderdigimizde donen response’un status code’unun 200 oldugunu ve response body’sinin null oldugunu test edin

    @Test
    public void test01() {
        //1-  https://jsonplaceholder.typicode.com/posts endpointine
        //    bir GET request gonderdigimizde donen response’un
        //    status code’unun 200 oldugunu
        //    ve Response’ta 100 kayit oldugunu test edin

        specJsonplaceholder.pathParam("pp1","posts");
        Response response=given()
                            .when().spec(specJsonplaceholder) //bu bize base url'i getirir
                            .get("/{pp1}");

        //response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.hasSize(100));

    }
    @Test
    public void test02(){
        //2- https://jsonplaceholder.typicode.com/posts/44 endpointine
        // bir GET request gonderdigimizde donen response’un status code’unun
        // 200 oldugunu ve “title” degerinin “optio dolor molestias sit” oldugunu test edin

        specJsonplaceholder.pathParams("pp1","posts","pp2","44");
        Response response=given()
                .when().spec(specJsonplaceholder)
                .get("/{pp1}/{pp2}");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("optio dolor molestias sit"));

    }

    @Test
    public void test03(){
        //3- https://jsonplaceholder.typicode.com/posts/50 endpointine
        // bir DELETE request gonderdigimizde
        // donen response’un status code’unun 200 oldugunu ve
        // response body’sinin null oldugunu test edin
        specJsonplaceholder.pathParams("pp1","posts","pp2","50");
        Response response=given()
                            .when().spec(specJsonplaceholder)
                            .delete("/{pp1}/{pp2}");

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",Matchers.nullValue());


    }

}
