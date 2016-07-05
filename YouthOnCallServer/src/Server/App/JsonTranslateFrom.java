/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.App;

import Server.Model.Jobs;
import Server.Model.Members;
import com.google.gson.Gson;

/**
 *
 * @author co075oh
 */
public class JsonTranslateFrom implements JsonTranslate {

    Gson translate = new Gson();
    
    @Override
    public Object translate(Object value, String type) {
        Object json;
        if (type.equals("Members")) {
            json = translate.fromJson((String) value, Members.class);
        } else {
            json = translate.fromJson((String) value, Jobs.class);
        }
        return json;
    }
    
}
