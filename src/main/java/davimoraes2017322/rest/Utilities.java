package davimoraes2017322.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Utilities {

    static boolean bodyChecker(Map<String, String> body){

        return (body.containsKey("name")
                &&body.containsKey("surname")
                &&body.containsKey("passport")
                &&body.containsKey("priority")
                &&body.containsKey("status"));

    }

    static int integerChecker(String value){
        try {
            int i = Integer.parseInt(value);
            return i;
        }catch (NumberFormatException e) {
            return -1;
        }
    }

    static String getJSON(Object object) {
        String json = "";
        ObjectMapper obj = new ObjectMapper();

        try {

            json = obj.writeValueAsString(object);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
