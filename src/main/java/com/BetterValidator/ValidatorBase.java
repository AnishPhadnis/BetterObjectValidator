package com.BetterValidator;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.hamcrest.Matcher;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;

public abstract class ValidatorBase<T extends ValidatorBase<T>> {
    protected Multimap<String, Matcher<? super Object>> matchers;
    protected String asserts;

    public ValidatorBase(){
        matchers = MultimapBuilder.hashKeys().arrayListValues().build();
    }

    public T asserts(String message){
        this.asserts = message;
        return getThis();
    }

    public T build(){
        return getThis();
    }

    public abstract T getThis();



    public T validate(Object obj) {
        for (String key : matchers.keySet()) {
            try {
                for (Matcher<? super Object> m : matchers.get(key)){
                    Field f = obj.getClass().getDeclaredField(key);
                    f.setAccessible(true);

                    assertThat(String.format("Property '%s' is not valid", key),
                            //PropertyUtils.getProperty(obj, key), m);
                            f.get(obj), m);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return getThis();
    }


}
