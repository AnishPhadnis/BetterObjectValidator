import org.hamcrest.Matcher;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class TestClass{

    public static void main(String[] args) {
        ObjectToValidate objectToValidate = ObjectToValidate.builder()
                .num(1)
                .listOfNums(List.of(1))
                .build();


        ObjectToValidateValidator objectToValidateValidator = ObjectToValidateValidator.builder()
                .num(equalTo(1))
                .listOfNums(contains(1))
                .build();

        objectToValidateValidator.validate(objectToValidate);



    }

    public void n(){
        //nothing(contains(4))
        Object object = new Object();


    }

    public <E extends Matcher<Object>> List<Matcher<E>> nothing(String key){
        Map<String, List<Matcher<E>>> m = new HashMap<>();
        m.put("num", List.of(equalTo(1)));

        List<Matcher<E>> n = new ArrayList<>();
        n.add(contains(4));

        return n;
    }
}