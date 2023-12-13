import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C13_ExpectedResponseIleTest {
    @Test
    public void test01(){

         /*
            http://dummy.restapiexample.com/api/v1/update/21 url’ine
            asagidaki body’ye sahip bir PUT request gonderdigimizde
            donen response’un asagidaki gibi oldugunu test edin.
            Request Body
                    {
                    "status": "success",
                    "data": {
                        "name": "Ahmet",
                        "salary": "1230",
                        "age": "44",
                        "id": 40
                            }
                   }
            Response Body
                    {
                    "status": "success",
                    "data": {
                        "status": "success",
                        "data": {
                            "name": "Ahmet",
                            "salary": "1230",
                            "age": "44",
                            "id": 40
                        }
                    },
                    "message": "Successfully! Record has been updated."
                }

     */

        //1- request hazirla: request body ve endpoint olustur
        String endPoint=" http://dummy.restapiexample.com/api/v1/update/21";

        JSONObject dataJsonObject=new JSONObject();

        dataJsonObject.put("name","Ahmet");
        dataJsonObject.put("salary", "1230");
        dataJsonObject.put("age","44");
        dataJsonObject.put("id",40);

        JSONObject requestBody=new JSONObject();
        requestBody.put("status","success");
        requestBody.put("data",dataJsonObject);

        //2- gorevde isteniyorsa expected response olustur


        JSONObject expectedResponseBody=new JSONObject();
        expectedResponseBody.put("status","success");
        expectedResponseBody.put("data",requestBody);
        expectedResponseBody.put("message","Successfully! Record has been updated.");

        //3- requesti gonder ve donen actual response'i kaydet

        Response response=given().contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .put(endPoint);
        //response.prettyPrint();

        //4- istenen assertionlari yap
        //assertion yapabilmek icin actual response icindeki bilgilere ulasmamiz gerekir
        // bunun icin de actualresponse'i dstslsrs kolsycs ulsdsabilecegimiz
        //JSonPath objesine Cast yapiyoruz

        JsonPath actualResponseJP=response.jsonPath();

        Assert.assertEquals(expectedResponseBody.getString("status"),
                            actualResponseJP.getString("status"));

        Assert.assertEquals(expectedResponseBody.getJSONObject("data")
                                                .getJSONObject("data")
                                                .getString("name"),
                            actualResponseJP.getString("data.data.name"));

        Assert.assertEquals(expectedResponseBody.getJSONObject("data")
                        .getJSONObject("data")
                        .getInt("id"),
                actualResponseJP.getInt("data.data.id"));

        Assert.assertEquals(expectedResponseBody.getJSONObject("data")
                        .getJSONObject("data")
                        .getString("salary"),
                actualResponseJP.getString("data.data.salary"));

        Assert.assertEquals(expectedResponseBody.getJSONObject("data")
                        .getJSONObject("data")
                        .getString("age"),
                actualResponseJP.getString("data.data.age"));

        Assert.assertEquals(expectedResponseBody.getJSONObject("data")
                                                .getString("status"),
                            actualResponseJP.getString("data.status"));

        Assert.assertEquals(expectedResponseBody.getString("message"),
                            actualResponseJP.getString("message"));

    }
}









