package edu.usc.model;
// Generated Oct 27, 2011 2:23:49 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Userstatistic generated by hbm2java
 */
public class Userstatistic  implements java.io.Serializable {


     private BigDecimal id;
     private BigDecimal sessionid;
     private BigDecimal userid;
     private Serializable time;
     private Serializable location;

    public Userstatistic() {
    }

	
    public Userstatistic(BigDecimal id) {
        this.id = id;
    }
    public Userstatistic(BigDecimal id, BigDecimal sessionid, BigDecimal userid, Serializable time, Serializable location) {
       this.id = id;
       this.sessionid = sessionid;
       this.userid = userid;
       this.time = time;
       this.location = location;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public BigDecimal getSessionid() {
        return this.sessionid;
    }
    
    public void setSessionid(BigDecimal sessionid) {
        this.sessionid = sessionid;
    }
    public BigDecimal getUserid() {
        return this.userid;
    }
    
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
    public Serializable getTime() {
        return this.time;
    }
    
    public void setTime(Serializable time) {
        this.time = time;
    }
    public Serializable getLocation() {
        return this.location;
    }
    
    public void setLocation(Serializable location) {
        this.location = location;
    }




}


