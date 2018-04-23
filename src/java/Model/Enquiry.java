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
@XmlRootElement(name = "enquiry")
public class Enquiry implements Serializable {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "startDate")
    private String startDate;
    @XmlElement(name = "endDate")
    private String endDate;
    @XmlElement(name = "message")
    private String message;
    @XmlElement (name = "listingId")
    private String listingId;

    //zero argument constructor for javabean compliance
    public Enquiry(){}
    
    //constructor with all attributes for new enquiries
    public Enquiry(String name, String email, String startDate, String endDate, String message, String listingId) {
        this.name = name;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.message = message;
        this.listingId = listingId;
    } 
    
    //getters and setters for all attributes of the enquiries
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the listingId
     */
    public String getListingId() {
        return listingId;
    }

    /**
     * @param listingId the listingId to set
     */
    public void setListingId(String listingId) {
        this.listingId = listingId;
    }
}
