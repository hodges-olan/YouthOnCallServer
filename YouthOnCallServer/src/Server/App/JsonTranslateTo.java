/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.App;

import com.google.gson.Gson;

/**
 *
 * @author co075oh
 */
public class JsonTranslateTo implements JsonTranslate {
    
    Gson translate = new Gson();

    @Override
    public Object translate(Object value, String type) {
        String json;
        if (type.equals("Members")) {
            json = translate.toJson(value);
        } else {
            json = translate.toJson(value);
        }
        return json;
    }
    
}
