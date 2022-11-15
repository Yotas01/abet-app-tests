package edu.javeriana.abetapptests.common;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    static Gson gson = new Gson();

    public static Object parseToObject(String responseBody, Class c){
        return gson.fromJson(responseBody, c);
    }
    public static String parseToJson(Object c){
        return gson.toJson(c);
    }
}
