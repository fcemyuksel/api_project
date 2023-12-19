package tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class C19_FarkliDataTurleriIleListOlusturma {
    @Test
    public void test01(){

        List<String > isimler=new ArrayList<>();
        isimler.add("Cem");
        //isimler.add(2);
        //isimler.add(true);
        //isimler.add('a');

        // Eger farkli data turlerinden element koymak isterseniz
        // data turu olarak herseyi kabul eden Object secebilirsiniz

        List<Object> herseyList=new ArrayList<>();

        herseyList.add(10);
        herseyList.add("Defne");
        herseyList.add(true);
        herseyList.add('f');
        herseyList.add(new int[3]);

        System.out.println(herseyList); //[10, Defne, true, f, [I@129a8472]

        // 2.element olan 10'un 3 katini yazdirin

        System.out.println((Integer) herseyList.get(0)*3); // 0. index rakam oldugu icin carpar //30
        //System.out.println((Integer) herseyList.get(1)*3);  //1. index rakam degil kabul etmez

        System.out.println(    ((String)  herseyList.get(1)).toUpperCase()); //DEFNE



    }
}
