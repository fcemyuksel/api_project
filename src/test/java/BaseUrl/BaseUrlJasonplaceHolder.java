package BaseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlJasonplaceHolder {

    protected RequestSpecification specJsonplaceholder;

    @Before
    public void setup(){
        specJsonplaceholder=new RequestSpecBuilder()
                            .setBaseUri("https://jsonplaceholder.typicode.com/")
                            .build();
    }
}
