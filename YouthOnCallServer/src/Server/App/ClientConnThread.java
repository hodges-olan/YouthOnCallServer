/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.App;

import com.google.gson.Gson;
import Server.Control.JobsControl;
import Server.Control.MembersControl;
import Server.Model.Jobs;
import Server.Model.Members;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
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
        Gson membersJSON = new Gson();
        Members member = membersJSON.fromJson(data, Members.class);
        outputStream.println(membersControl.createMember(member));
    }

    // Update member method
    private void updateMember(String data) {
        Gson membersJSON = new Gson();
        Members member = membersJSON.fromJson(data, Members.class);
        membersControl.updateMember(member);
    }

    // Retrieve member method
    private String retrieveMember(Integer data) {
        Members member;
        Gson membersJSON = new Gson();
        member = membersControl.retrieveMember(data);
        String returnData = membersJSON.toJson(member);
        return returnData;
    }

    // Retrieve all members method
    private String retrieveAllMembers() {
        List<Members> members;
        Gson membersJSON = new Gson();
        members = membersControl.retrieveAllMembers();
        String returnData = membersJSON.toJson(members);
        return returnData;
    }

    // Retrieve all youth method
    private String retrieveAllYouth() {
        List<Members> youth;
        Gson membersJSON = new Gson();
        youth = membersControl.retrieveAllYouth();
        String returnData = membersJSON.toJson(youth);
        return returnData;
    }

    // Authenticate member method
    private boolean authMember(String email, String password) {
        boolean returnData = membersControl.authMember(email, password);
        return returnData;
    }

    private void createJob(String data) {
        Gson jobsJSON = new Gson();
        Jobs job = jobsJSON.fromJson(data, Jobs.class);
        outputStream.println(jobsControl.createJob(job));
    }

    private void updateJob(String data) {
        Gson jobsJSON = new Gson();
        Jobs job = jobsJSON.fromJson(data, Jobs.class);
        jobsControl.updateJob(job);
    }

    private String retrieveJob(Integer data) {
        Jobs job;
        Gson jobsJSON = new Gson();
        job = jobsControl.retrieveJob(data);
        String returnData = jobsJSON.toJson(job);
        return returnData;
    }

    private String retrieveAllJobs() {
        List<Jobs> jobs;
        Gson jobsJSON = new Gson();
        jobs = jobsControl.retrieveAllJobs();
        String returnData = jobsJSON.toJson(jobs);
        return returnData;
    }
    
}
