import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C07_JSonObjeIleJsonPathKullanimi {
    @Test
    public void test01(){

        JSONObject kisiBilgileriJsonObj=new JSONObject();
        //firstname, lastname, age ===> direkt bilgi
        //address ===> bir JSonObject
        //phoneNumbers ===> JSonArray  == icinde 2 tane JSonObject var

        // olusturmaya en icerdeki JSon objelerinden basliyoruz
        JSONObject cepTelJsonObj=new JSONObject();
        JSONObject evTelJsonObj=new JSONObject();
        cepTelJsonObj.put("type","iPhone");
        cepTelJsonObj.put("number","0123456789");
        evTelJsonObj.put("type","home");
        evTelJsonObj.put("number","112233445566");

        JSONArray telefonBilgileriArray=new JSONArray();
        telefonBilgileriArray.put(cepTelJsonObj);
        telefonBilgileriArray.put(evTelJsonObj);

        JSONObject adresJsonObj=new JSONObject();
        adresJsonObj.put("streetAddress","2487/4 sokak");
        adresJsonObj.put("city","izmir");
        adresJsonObj.put("postalCode",35310);

        //icerideki tum Json objeleri hazir olunca
        // asil Json objesine tum elementleri ekleyebiliriz

        kisiBilgileriJsonObj.put("firstname","Cem");
        kisiBilgileriJsonObj.put("lastname","Yuksel");
        kisiBilgileriJsonObj.put("age",50);
        kisiBilgileriJsonObj.put("address",adresJsonObj);
        kisiBilgileriJsonObj.put("phoneNumbers",telefonBilgileriArray);

        System.out.println(kisiBilgileriJsonObj);

        /*
        {"firstname":"Cem","address":{"streetAddress":"2487/4 sokak","city":"izmir","postalCode":35310},"age":50,"phoneNumbers":[{"number":"0123456789","type":"iPhone"},{"number":"112233445566","type":"home"}],"lastname":"Yuksel"}

         */

        //soyismini yazdirin
        System.out.println(kisiBilgileriJsonObj.get("lastname").toString()); //Yuksel

        System.out.println(kisiBilgileriJsonObj.getString("lastname").toUpperCase()); //YUKSEL

        //kisinin yasadigi sehri yazdirin
        System.out.println(kisiBilgileriJsonObj.getJSONObject("address").getString("city"));//izmir

        System.out.println(kisiBilgileriJsonObj.getJSONObject("address")
                .getString("city").toUpperCase()); //İZMİR

        //cep telefonunun numarasini yazdirin

        System.out.println(kisiBilgileriJsonObj.getJSONArray("phoneNumbers")
                .getJSONObject(0).getString("number")); //0123456789
    }
}
