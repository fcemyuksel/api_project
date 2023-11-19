import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_ResponseBilgileriniTestEtme {
@Test
    public void test01(){

    //https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
    //gonderdigimizde donen Response’un,
    //status code’unun 200,
    //ve content type’inin application/json; charset=utf-8,
    //ve Server isimli Header’in degerinin Cowboy,
    //ve status Line’in HTTP/1.1 200 OK oldugunu test edin



    //1- request hazirla: request body ve endpoint olustur
    String endpoint="https://restful-booker.herokuapp.com/booking/10";
    //2- gorevde isteniyorsa expected body olustur
    //3- requesti gonder ve donen actual response'i kaydet
    Response response=given().when().get(endpoint);

    //4- istenen assertionlari yap

    // response git ve sunlari assert et

    response.then().assertThat()
            .statusCode(200)
            .contentType("application/json; charset=utf-8")
            .header("Server","Cowboy")
            .statusLine("HTTP/1.1 200 OK");

//    System.out.println("Status Code: "+response.statusCode());
//    System.out.println("Content Type: "+response.getContentType());
//    System.out.println("Server isimli header degeri: "+response.getHeader("Server"));
//    System.out.println("Status Line: "+response.statusLine());
//    System.out.println("Response suresi: "+response.getTime()+" ms");
// sout yapmak gereksiz

}


}
