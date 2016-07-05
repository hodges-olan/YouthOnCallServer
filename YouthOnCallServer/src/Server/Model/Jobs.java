/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author co075oh
 */
@Entity
public class Jobs implements Serializable {
    
    // Class Attributes
    @Id
    private int id;
    @Column (name="name")
    private String name;
    @Column (name="description")
    private String description;
    @Column (name="pay")
    private double pay;
    @Column (name="memberID")
    private int memberID;
    @Column (name="youthID")
    private int youthID;
    @Column (name="estHours")
    private int estHours;
    @Column (name="status")
    private String status;

    // Constructor Methods
    public Jobs() {
    }

    public Jobs(int id, String name, String description, float pay, int memberID, int estHours, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pay = pay;
        this.memberID = memberID;
        this.estHours = estHours;
        this.status = status;
    }
    
    public Jobs(int id, String name, String description, float pay, int memberID, int youthID, int estHours, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pay = pay;
        this.memberID = memberID;
        this.youthID = youthID;
        this.estHours = estHours;
        this.status = status;
    }
    
    // Getter and Setter Methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getYouthID() {
        return youthID;
    }

    public void setYouthID(int youthID) {
        this.youthID = youthID;
    }

    public int getEstHours() {
        return estHours;
    }

    public void setEstHours(int estHours) {
        this.estHours = estHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
