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
@XmlRootElement(name = "listing")
public class Listing implements Serializable {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "status")
    private String status = "available";
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "guests")
    private int guests;
    @XmlElement(name = "listerUsername")
    private String listerUsername;
    @XmlElement(name = "description")
    private String description;
    @XmlElement (name = "location")
    private Location location;
    
    //zero argument constructor for javabean compliance
    public Listing() {}
    
    //constructor with all attributes when creating new listings with full information
    public Listing(int id, String status, String type, int guests, String listerUsername, String description, Location location) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.guests = guests;
        this.listerUsername = listerUsername;
        this.description = description;
        this.location = location;
    }

    //getters and setters for all attributes
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the guests
     */
    public int getGuests() {
        return guests;
    }

    /**
     * @param guests the guests to set
     */
    public void setGuests(int guests) {
        this.guests = guests;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the listerUsername
     */
    public String getListerUsername() {
        return listerUsername;
    }

    /**
     * @param listerUsername the listerUsername to set
     */
    public void setListerUsername(String listerUsername) {
        this.listerUsername = listerUsername;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
