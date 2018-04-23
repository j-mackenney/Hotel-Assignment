/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author James
 */
public class MainPanel extends JPanel {
    
    private HotelSOAP hotelApp;
    
    private JTextField email = new JTextField();
    private JTextField password = new JTextField();
    
    private User user;
    
    public MainPanel(HotelSOAP hotelApp) throws IOException_Exception, JAXBException_Exception {
        this.hotelApp = hotelApp;
        setup();
        build();
    }
    
    private void setup() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    private void build() throws IOException_Exception, JAXBException_Exception {
        add(tabbedPane());
    }
    
    private JComponent tabbedPane() throws IOException_Exception, JAXBException_Exception {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JComponent viewListings = new ViewListingsPanel(hotelApp);
        JComponent newListing = new NewListingPanel(hotelApp, viewListings);
        
        tabbedPane.addTab("New Listing", null, newListing, "Create a new listing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        
        tabbedPane.addTab("View Listing", null, viewListings, "View all listings");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        return tabbedPane;
    }
}
