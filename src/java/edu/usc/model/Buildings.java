package edu.usc.model;
// Generated 19:47:48 05-10-2011 by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Buildings generated by hbm2java
 */
public class Buildings  implements java.io.Serializable {

     private BigDecimal id;
     private String name;
     private String shortname;
     private String address;
     private Serializable location;

    public Buildings() {
    }

	
    public Buildings(BigDecimal id) {
        this.id = id;
    }
    public Buildings(BigDecimal id, String name, String shortname, String address, Serializable location) {
       this.id = id;
       this.name = name;
       this.shortname = shortname;
       this.address = address;
       this.location = location;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getShortname() {
        return this.shortname;
    }
    
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Serializable getLocation() {
        return this.location;
    }
    
    public void setLocation(Serializable location) {
        this.location = location;
    }
}