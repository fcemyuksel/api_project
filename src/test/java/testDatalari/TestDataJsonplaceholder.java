package testDatalari;

import org.json.JSONObject;

public class TestDataJsonplaceholder {
    public static int basariliResponseStatusCode=200;
    public static JSONObject ornekResponseBody(){

        /*
         {
            "userId":3,
            "id":22,
            "title":"dolor sint quo a velit explicabo quia nam",
            "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                         um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
         */

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",3);
        jsonObject.put("id",22);
        jsonObject.put("title","dolor sint quo a velit explicabo quia nam");
        jsonObject.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return jsonObject;
    }
}
