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
public class EnquiryApplication implements Serializable {
    private String filePath;
    private Enquiries enquiries;
    
    public EnquiryApplication() {}
    
    //Sets the file path to the xml "database" file, Takes a string as an argument
    public void setFilePath(String filePath) throws JAXBException, FileNotFoundException, IOException {
        // Create the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Enquiries.class);
        Unmarshaller u = jc.createUnmarshaller();

        //Unmarshal the object from the file
        FileInputStream fin = new FileInputStream(filePath);
        enquiries = (Enquiries)u.unmarshal(fin);
        fin.close();
        this.filePath = filePath;
    }
    
    
    public Enquiries getEnquiries(String id) {
        return enquiries.filter(id);
    }
    
    public Enquiries getEnquiries() {
        return enquiries;
    }
    //Takes a java object and marshalls it into xml using jaxb
    public void addEnquiry(Enquiry enquiry) throws JAXBException, FileNotFoundException {
        enquiries.addEnquiry(enquiry);
        JAXBContext jc = JAXBContext.newInstance(Enquiries.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(enquiries, new FileOutputStream(filePath));
    }
}
