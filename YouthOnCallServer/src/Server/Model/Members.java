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
public class Members implements Serializable {
    
    // Class Attributes
    @Id
    private int id;
    @Column (name="name")
    private String name;
    @Column (name="email")
    private String email;
    @Column (name="address")
    private String address;
    @Column (name="city")
    private String city;
    @Column (name="st")
    private String st;
    @Column (name="zip")
    private Integer zip;
    @Column (name="phone")
    private String phone;
    @Column (name="youth")
    private boolean youth;
    @Column (name="password")
    private String password;

    // Constructor Methods
    public Members() {
    }

    public Members(int id, String name, String email, String address, String city, String st, Integer zip, String phone, boolean youth, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.st = st;
        this.zip = zip;
        this.phone = phone;
        this.youth = youth;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isYouth() {
        return youth;
    }

    public void setYouth(boolean youth) {
        this.youth = youth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
