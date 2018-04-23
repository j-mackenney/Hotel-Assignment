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
public class UserApplication implements Serializable {
    private String filePath;
    private Users users;
    
    public UserApplication() {}
    
    //Sets the file path to the xml "database" file, Takes a string as an argument    
    public void setFilePath(String filePath) throws JAXBException, FileNotFoundException, IOException {
        // Create the unmarshaller
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Unmarshaller u = jc.createUnmarshaller();

        //Unmarshals the object from the file
        FileInputStream fin = new FileInputStream(filePath);
        users = (Users)u.unmarshal(fin);
        fin.close();
        this.filePath = filePath;
    }
    
    public User login(String email, String password) {
        return users.login(email, password);
    }
    //Returns users as objects
    public Users getUsers() {
        return users;
    }
    //gets a specif user when their email is specified
    public User getUser(String email) {
        return users.getUser(email);
    }
    
    //Adds a user and then marshalls into the xml file
    public void addUser(User user) throws JAXBException, FileNotFoundException {
        users.addUser(user);
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(users, new FileOutputStream(filePath));
    }
}
