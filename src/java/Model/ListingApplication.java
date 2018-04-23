/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author minniemanZ
 */
public class ListingApplication implements Serializable {
    private String filePath;
    private Listings listings;
    
    public ListingApplication() {}
    
    //Sets the file path to the xml "database" file, Takes a string as an argument
    public void setFilePath(String filePath) throws JAXBException, FileNotFoundException, IOException {
        // Creates the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Listings.class);
        Unmarshaller u = jc.createUnmarshaller();

        // Unmarshals the object from the file
        FileInputStream fin = new FileInputStream(filePath);
        listings = (Listings)u.unmarshal(fin);
        fin.close();
        this.filePath = filePath;
    }
    
    public Listing findListing(int id) {
        return listings.getListing(id);
    }
    
    public Listings getListings(String username, String status, int guests) {
        return listings.filter(username, status, guests);
    }
    //Adds a listing, then calls the marshallObjects method to push them to the sml file.
    public void addListing(Listing listing) throws JAXBException, FileNotFoundException {
        listings.addListing(listing);
        marshallObjects();
    }
    //Called when someone wants to close a listing, updates the xml file with the listing status
    public void closeListing(int id) throws JAXBException, FileNotFoundException {
        listings.closeListing(findListing(id));
        marshallObjects();
    }
    //Takes the java objects and updates the xml file
    private void marshallObjects() throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance(Listings.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(listings, new FileOutputStream(filePath));
    }
    //returns all listings as objects
    public Listings getAllListings() {
        return listings;
    }
}
