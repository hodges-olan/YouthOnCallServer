/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.App;

import Server.Control.JobsControl;
import Server.Control.MembersControl;
import Server.Model.Jobs;
import Server.Model.Members;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author co075oh
 */
public class ClientConnThread implements Runnable {

    // Declare attributes
    Socket socket;
    Scanner inputStream;
    PrintStream outputStream;
    
    // Declare Controller Classes
    private final MembersControl membersControl = new MembersControl();
    private final JobsControl jobsControl = new JobsControl();
    
    // Declare App Controller
    private static final HashMap<String, JsonTranslate> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("toJSON", new JsonTranslateTo());
        COMMANDS.put("fromJSON", new JsonTranslateFrom());
    }
    
    // Default Constructor
    public ClientConnThread (Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new Scanner(socket.getInputStream());
        this.outputStream = new PrintStream(socket.getOutputStream());
    }
    
    // Run Method
    @Override
    public void run() {
        boolean done = false;
        String command, data, returnData;
        Integer intData;
        
        while (!done) {
            // Gather information from client
            command = inputStream.nextLine();
            switch(command) {
                case "createMember":
                    data = inputStream.nextLine();
                    this.createMember(data);
                    yocLogger.log(command, data, "INFO");
                    break;
                case "updateMember":
                    data = inputStream.nextLine();
                    this.updateMember(data);
                    yocLogger.log(command, data, "INFO");
                    break;
                case "retrieveMember":
                    intData = inputStream.nextInt();
                    returnData = this.retrieveMember(intData);
                    outputStream.println(returnData);
                    yocLogger.log(command, Integer.toString(intData), "INFO");
                    break;
                case "retrieveAllMembers":
                    returnData = this.retrieveAllMembers();
                    outputStream.println(returnData);
                    yocLogger.log(command, returnData, "INFO");
                    break;
                case "retrieveAllYouth":
                    returnData = this.retrieveAllYouth();
                    outputStream.println(returnData);
                    yocLogger.log(command, returnData, "INFO");
                    break;
                case "authMember":
                    String email = inputStream.nextLine();
                    String password = inputStream.nextLine();
                    boolean authenticated = this.authMember(email, password);
                    outputStream.println(authenticated);
                    yocLogger.log(command, email, "INFO");
                    break;
                case "createJob":
                    data = inputStream.nextLine();
                    this.createJob(data);
                    yocLogger.log(command, data, "INFO");
                    break;
                case "updateJob":
                    data = inputStream.nextLine();
                    this.updateJob(data);
                    yocLogger.log(command, data, "INFO");
                    break;
                case "retrieveJob":
                    intData = inputStream.nextInt();
                    returnData = this.retrieveJob(intData);
                    yocLogger.log(command, Integer.toString(intData), "INFO");
                    outputStream.println(returnData);
                    break;
                case "retrieveAllJobs":
                    returnData = this.retrieveAllJobs();
                    outputStream.println(returnData);
                    yocLogger.log(command, returnData, "INFO");
                    break;
                case "bye":
                    done = true;
                    this.inputStream.close();
                    this.outputStream.close();
                    yocLogger.log(command, "Session closed", "INFO");
                    try {
                        this.socket.close();
                    } catch (IOException ex) {
                        yocLogger.log(command, ex.getMessage(), "ERR");
                    }
                    break;
                default:
                    outputStream.println("Invalid Command");
                    yocLogger.log(command, "Invalid Command", "INFO");
                    break;
            }
        }
    }

    // Create member method
    private void createMember(String data) {
        Members member = (Members) COMMANDS.get("fromJSON").translate((Object) data, "Members");
        outputStream.println(membersControl.createMember(member));
    }

    // Update member method
    private void updateMember(String data) {
        Members member = (Members) COMMANDS.get("fromJSON").translate((Object) data, "Members");
        membersControl.updateMember(member);
    }

    // Retrieve member method
    private String retrieveMember(Integer data) {
        Members member;
        member = membersControl.retrieveMember(data);
        String returnData = (String) COMMANDS.get("toJSON").translate((Object) member, "Members");
        return returnData;
    }

    // Retrieve all members method
    private String retrieveAllMembers() {
        List<Members> members;
        members = membersControl.retrieveAllMembers();
        String returnData = (String) COMMANDS.get("toJSON").translate((Object) members, "Members");
        return returnData;
    }

    // Retrieve all youth method
    private String retrieveAllYouth() {
        List<Members> youth;
        youth = membersControl.retrieveAllYouth();
        String returnData = (String) COMMANDS.get("toJSON").translate((Object) youth, "Members");
        return returnData;
    }

    // Authenticate member method
    private boolean authMember(String email, String password) {
        boolean returnData = membersControl.authMember(email, password);
        return returnData;
    }

    private void createJob(String data) {
        Jobs job = (Jobs) COMMANDS.get("fromJSON").translate((Object) data, "Jobs");
        outputStream.println(jobsControl.createJob(job));
    }

    private void updateJob(String data) {
        Jobs job = (Jobs) COMMANDS.get("fromJSON").translate((Object) data, "Jobs");
        jobsControl.updateJob(job);
    }

    private String retrieveJob(Integer data) {
        Jobs job;
        job = jobsControl.retrieveJob(data);
        String returnData = (String) COMMANDS.get("toJSON").translate((Object) job, "Jobs");
        return returnData;
    }

    private String retrieveAllJobs() {
        List<Jobs> jobs;
        jobs = jobsControl.retrieveAllJobs();
        String returnData = (String) COMMANDS.get("toJSON").translate((Object) jobs, "Jobs");
        return returnData;
    }
    
}
