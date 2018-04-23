/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author James
 */
public class NewListingPanel extends JPanel {
    private HotelSOAP hotelApp;
    
    private JTextField email = new JTextField();
    private JTextField password = new JPasswordField();
    
    private JComboBox type = new JComboBox(new String[] {"", "Whole House", "Whole Unit", "Whole Unit"});
    private JTextField guests = new JTextField();
    private JTextField description = new JTextField();
    
    private JTextField number = new JTextField();
    private JTextField street = new JTextField();
    private JTextField suburb = new JTextField();
    private JComboBox state = new JComboBox(new String[] {"", "ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA"});
    private JTextField postcode = new JTextField();
    
    private JLabel helperLabel = new JLabel();
    private ViewListingsPanel viewListings;
    
    public NewListingPanel(HotelSOAP hotelApp, JComponent viewListings) {
        this.hotelApp = hotelApp;
        this.viewListings = (ViewListingsPanel)viewListings;
        setup();
        build();
    }
    
    private void setup() {
        
    }
    
    private void build() {
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(40));
        box.add(labelBox());
        box.add(Box.createVerticalStrut(20));
        box.add(locationBox());
        box.add(Box.createVerticalStrut(20));
        box.add(buttonBox());
        box.add(Box.createVerticalStrut(10));
        helperLabel.setForeground(Color.RED);
        setSize(helperLabel, 200);
        box.add(helperLabel);
        add(box);
    }
    
    private Box buttonBox() {
        ButtonListener listener = new ButtonListener();
        Box box = Box.createHorizontalBox();
        JButton clear = new JButton("Clear");
        clear.addActionListener(listener);
        box.add(clear);
        box.add(Box.createHorizontalStrut(10));
        JButton submit = new JButton("Submit");
        submit.addActionListener(listener);
        box.add(submit);
        return box;
    }
    
    private Box labelBox() {
        Box box = Box.createHorizontalBox();
        
        Box leftBox = Box.createVerticalBox();
        leftBox.add(label("email: "));
        leftBox.add(label("Password: "));
        leftBox.add(Box.createVerticalStrut(10));
        leftBox.add(label("Type: "));
        leftBox.add(label("Guests: "));
        leftBox.add(label("Description: "));
        Box rightBox = Box.createVerticalBox();
        rightBox.add(comp(email));
        rightBox.add(comp(password));
        rightBox.add(Box.createVerticalStrut(10));
        rightBox.add(comp(type));
        rightBox.add(comp(guests));
        rightBox.add(comp(description));
        
        box.add(leftBox);
        box.add(rightBox);
        
        return box;
    }
    
    private Box locationBox() {
        Box box = Box.createHorizontalBox();
        
        Box leftBox = Box.createVerticalBox();
        leftBox.add(label("Number: "));
        leftBox.add(label("Street: "));
        leftBox.add(label("Suburb: "));
        leftBox.add(label("State: "));
        leftBox.add(label("Postcode: "));
        Box rightBox = Box.createVerticalBox();
        rightBox.add(comp(number));
        rightBox.add(comp(street));
        rightBox.add(comp(suburb));
        rightBox.add(comp(state));
        rightBox.add(comp(postcode));
        
        box.add(leftBox);
        box.add(rightBox);
        return box;
    }
    
    private JComponent comp(JComponent field) {
        setSize(field, 200);
        return field;
    }
    
    private JLabel label(String name) {
        JLabel label = new JLabel(name);
        setSize(label, 75);
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }
    
    private void setSize(JComponent c, int x) {   
        c.setPreferredSize(new Dimension(x, 20));
        c.setMinimumSize(new Dimension(x, 20));
        c.setMaximumSize(new Dimension(x, 20)); 
    }
    
    private int getNextListingID() {
        try {
            Listings listings = hotelApp.fetchListings("", "", 0);
            return listings.getListing().size() + 1;
        } catch (IOException_Exception | JAXBException_Exception ex) {
            Logger.getLogger(NewListingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    private void addListing() throws IOException_Exception, JAXBException_Exception {
        int id = getNextListingID();
        User user = hotelApp.fetchUser(email.getText());
        if (user == null || !user.getPassword().equals(password.getText())) {
            helperLabel.setText("Incorrect Username or Password");
            return;
        }
        Location location = new Location();
        location.setNumber(Integer.parseInt(number.getText()));
        location.setStreet(street.getText());
        location.setSuburb(suburb.getText());
        location.setState((String)state.getSelectedItem());
        location.setPostcode(Integer.parseInt(postcode.getText()));
            // number, street, suburb, state, postcode
        
        Listing listing = new Listing();
        listing.setId(id);
        listing.setStatus("available");
        listing.setType((String)type.getSelectedItem());
        listing.setGuests(Integer.parseInt(guests.getText()));
        listing.setListerUsername(user.getUsername());
        listing.setDescription(description.getText());
        listing.setLocation(location);
            // id, status, type, guests, listerUsername, description, location
        try {
            hotelApp.addListing(listing);
        } catch (IOException_Exception | JAXBException_Exception ex) {
            Logger.getLogger(NewListingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewListings.update("", "", 0);
    }
    
    private boolean validateFields() {
        if (email.getText().equals("") || password.getText().equals("")) {
            helperLabel.setText("You must enter a username and password");
            return false;
        }
        User user;
        try {
            user = hotelApp.fetchUser(email.getText());
            if (user == null || !user.getPassword().equals(password.getText())) {
            helperLabel.setText("Incorrect Username or Password");
            return false;
        }
        } catch (IOException_Exception | JAXBException_Exception ex) {
            return false;
        }
        if (
                type.getSelectedItem().equals("") || 
                guests.getText().equals("") ||
                description.getText().equals("") ||
                number.getText().equals("") ||
                street.getText().equals("") ||
                suburb.getText().equals("") ||
                state.getSelectedItem().equals("") || 
                postcode.getText().equals("")
           ) {
            helperLabel.setText("You must complete all fields");
            return false;
        }
        try {
            Integer.parseInt(guests.getText());
            Integer.parseInt(number.getText());
            Integer.parseInt(postcode.getText());
        } catch (Exception e) {
            helperLabel.setText("guest, number and postcode must be numbers");
            return false;
        }
        return true;
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Clear":
                    helperLabel.setText("");
                    email.setText("");
                    password.setText("");
                    type.setSelectedItem("");
                    guests.setText("");
                    description.setText("");
                    number.setText("");
                    street.setText("");
                    suburb.setText("");
                    state.setSelectedItem("");
                    postcode.setText("");
                    break;
                case "Submit":
                    if (!validateFields()) {
                        break;
                    }
                    try {
                        addListing();
                    } catch (IOException_Exception | JAXBException_Exception ex) {
                        Logger.getLogger(NewListingPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    helperLabel.setText("");
                    email.setText("");
                    password.setText("");
                    type.setSelectedItem("");
                    guests.setText("");
                    description.setText("");
                    number.setText("");
                    street.setText("");
                    suburb.setText("");
                    state.setSelectedItem("");
                    postcode.setText("");
                    break;
                default:
                    break;
            }
        }
    }
}
