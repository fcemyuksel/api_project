import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class C01_IlkRequest {
    @Test
    public void ilkRequest(){

        //1- request hazirla: request body ve endpoint olustur
        //2- gorevde isteniyorsa expected body olustur
        //3- requesti gonder ve donen actual response'i kaydet
        //4- istenen assertionlari yap

        /*
        API testtingde yapilacak islem yukaridaki 4 maddedir
        sadece sirket ve frameworklere bagli olarak
        o 4 islemin yapilisi degisir

        1- Matchers ile beklenen ve actual degerelri karsilastiracagiz
        2- JUnit assert classi ile testleri yapacagiz
        3- bize expected body verildiginde json ile expected responce olusturma
        4- Jsonpath kullanarak bilgilere daha pratik ulasma
        5- expected response olustururken Map kullanimi
        6- Pojo class'lar kullanarak karmasik expected body olusturma
        7- cucumber framwork ile API testleri
        8- Karate framework kullanilarak hazir methodlarla api testi
         */

        //https://restful-booker.herokuapp.com/booking/10 url’ine
        // bir GET request gonderdigimizde donen Response’u yazdirin.


        //1- request hazirla: request body ve endpoint olustur
        String endPoint="https://restful-booker.herokuapp.com/booking/10";
        //2- gorevde isteniyorsa expected body olustur
        //3- requesti gonder ve donen actual response'i kaydet

        Response response= given() //request yollanmadan yapilmasi gereken on hazirlikler icin kullanilir
                           .when() //request yollarken body kullanmamiz gerekirse body ile ilgili detaylari when()'den donra yapariz
                           .get(endPoint); //kullanacagimiz HTTP metodu get, post, put, patch, delete
                                            // icine de kullanacagimiz enfpoint yazilir

        response.prettyPrint();


        //4- istenen assertionlari yap

    }
}
