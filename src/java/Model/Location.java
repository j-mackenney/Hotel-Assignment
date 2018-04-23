/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author minniemanZ
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "location")
public class Location implements Serializable {
    @XmlElement(name = "number")
    private int number;
    @XmlElement(name = "street")
    private String street;
    @XmlElement(name = "suburb")
    private String suburb;
    @XmlElement(name = "state")
    private String state;
    @XmlElement(name = "postcode")
    private int postcode;
    
    //zero argument constructor for javabean compliance
    public Location() {}
    
    //constructor with all fields
    public Location(int number, String street, String suburb, String state, int postcode) {
        this.number = number;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

    // Getters and Setters for each attribute below
    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @param suburb the suburb to set
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * @return the postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
    
    
}
