/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.App;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author co075oh
 */
public class YouthOnCallServerTest {
    
    public YouthOnCallServerTest() {
    }

    /**
     * Test of main method, of class YouthOnCallServer.
     */
    @Test
    public void testMain() {
        // Declare Executor Service
        ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    
        // Declare Session Factory
        SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        
        System.out.println("main - Socket Test");
        try {

            // Create Server Socket
            ServerSocket serverSocket = new ServerSocket(7890);
            assertTrue(serverSocket.isBound());
            
            // Open log file
            String filePath = "log.txt";
            PrintWriter logFile = new PrintWriter(new FileWriter(filePath, true));
            assertFalse(logFile.checkError());
            
        } catch (IOException ex) {
            Logger.getLogger(YouthOnCallServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
