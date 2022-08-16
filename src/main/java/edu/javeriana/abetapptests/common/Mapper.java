package edu.javeriana.abetapptests.common;

import com.google.gson.Gson;

public class Mapper {

    static Gson gson = new Gson();

    public static  <T> Object parseToObject(String responseBody, Class c){
        return gson.fromJson(responseBody, c);
    }
    public static String parseToJson(Object c){
        return gson.toJson(c);
    }
}
