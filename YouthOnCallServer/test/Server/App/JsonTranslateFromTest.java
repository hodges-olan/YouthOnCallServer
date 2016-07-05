/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.App;

import Server.Model.Members;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author co075oh
 */
public class JsonTranslateFromTest {
    
    public JsonTranslateFromTest() {
    }

    /**
     * Test of translate method, of class JsonTranslateFrom.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        Object value = (Object) ("{\"id\":24,\"name\":\"Olan Hodges\",\"email\":\"olanhodges@gmail.com\",\"address\":\"3312 Westview Drive\",\"city\":\"McKinney\",\"st\":\"TX\",\"zip\":75070,\"phone\":\"214-585-2561\",\"youth\":false,\"password\":\"97c94ebe5d767a353b77f3c0ce2d429741f2e8c99473c3c150e2faa3d14c9da6\"}");
        String type = "Members";
        JsonTranslateFrom instance = new JsonTranslateFrom();
        Members expResult = new Members(24,"Olan Hodges", "olanhodges@gmail.com", "3312 Westview Drive", "McKinney", "TX", 75070, "214-585-2561", false, "97c94ebe5d767a353b77f3c0ce2d429741f2e8c99473c3c150e2faa3d14c9da6");
        Members result = (Members) instance.translate(value, type);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getEmail(), result.getEmail());
        assertEquals(expResult.getAddress(), result.getAddress());
        assertEquals(expResult.getCity(), result.getCity());
        assertEquals(expResult.getSt(), result.getSt());
        assertEquals(expResult.getZip(), result.getZip());
        assertEquals(expResult.getPhone(), result.getPhone());
        assertEquals(expResult.isYouth(), result.isYouth());
        assertEquals(expResult.getPassword(), result.getPassword());
    }
    
}
