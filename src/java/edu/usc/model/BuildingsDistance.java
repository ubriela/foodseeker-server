/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.model;

/**
 *
 * @author ubriela
 */
public class BuildingsDistance {
    private Buildings building;
    private double distance;

    public BuildingsDistance(Buildings building, double distance) {
        this.building = building;
        this.distance = distance;
    }

    public Buildings getBuilding() {
        return building;
    }

    public void setBuilding(Buildings building) {
        this.building = building;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
