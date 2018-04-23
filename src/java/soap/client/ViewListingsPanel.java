/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author James
 */
public class ViewListingsPanel extends JPanel {
    
    private HotelSOAP hotelApp;
    private ListingTable model;
    private JTable table;
    
    private JTextField username = new JTextField();
    private JComboBox status = new JComboBox(new String[] {"", "available", "unavailable"});
    private JTextField guests = new JTextField();
    
    private JLabel helperLabel = new JLabel();
    private JLabel wrongUser = new JLabel();
    
    private JTextField email = new JTextField();
    private JPasswordField password = new JPasswordField();
    
    public ViewListingsPanel(HotelSOAP hotelApp) throws IOException_Exception, JAXBException_Exception {
        this.hotelApp = hotelApp;
        setup();
        build();
    }
    
    private void setup() throws IOException_Exception, JAXBException_Exception {
        model = new ListingTable(hotelApp);
        table = new JTable(model);
        // set table col widths here
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(75);
        table.getColumnModel().getColumn(2).setPreferredWidth(75);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);
        table.setPreferredScrollableViewportSize(new Dimension((int)table.getPreferredSize().getWidth(), 200));
        table.setFillsViewportHeight(true);
    }
    
    private void build() {
        ButtonListener listener = new ButtonListener();
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));
        box.add(searchBox());
        setSize(helperLabel, 200);
        helperLabel.setForeground(Color.RED);
        box.add(helperLabel);
        box.add(Box.createVerticalStrut(10));
        box.add(buttonBox(listener));
        box.add(Box.createVerticalStrut(30));
        box.add(new JScrollPane(table));
        box.add(Box.createVerticalStrut(10));
        box.add(closeListingBox(listener));
        add(box);
    }
    
    private Box searchBox() {
        Box box = Box.createHorizontalBox();
        
        Box leftBox = Box.createVerticalBox();
        leftBox.add(label("Username: "));
        leftBox.add(label("Status: "));
        leftBox.add(label("Guests: "));
        Box rightBox = Box.createVerticalBox();
        rightBox.add(comp(username));;
        rightBox.add(comp(status));
        rightBox.add(comp(guests));
        
        box.add(leftBox);
        box.add(rightBox);
        
        return box;
    }
    
    private Box buttonBox(ButtonListener listener) {
        Box box = Box.createHorizontalBox();
        JButton clear = new JButton("Clear");
        clear.addActionListener(listener);
        box.add(clear);
        box.add(Box.createHorizontalStrut(10));
        JButton search = new JButton("Search");
        search.addActionListener(listener);
        box.add(search);
        return box;
    }
    
    private Box closeListingBox(ButtonListener listener) {
        Box box = Box.createVerticalBox();
        
        Box loginBox = Box.createHorizontalBox();
        Box leftBox = Box.createVerticalBox();
        leftBox.add(label("Email: "));
        leftBox.add(label("Password: "));
        Box rightBox = Box.createVerticalBox();
        rightBox.add(comp(email));
        rightBox.add(comp(password));
        
        loginBox.add(leftBox);
        loginBox.add(rightBox);
        
        box.add(loginBox);
        
        JButton close = new JButton("Close Listing");
        close.addActionListener(listener);
        box.add(close);
        setSize(wrongUser, 200);
        wrongUser.setForeground(Color.RED);
        box.add(wrongUser);
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
    
    public void update(String user, String status, int number) {
        try {
            model.update(hotelApp.fetchListings(user, status, number));
            model.fireTableDataChanged();
        } catch (IOException_Exception | JAXBException_Exception ex) {
            Logger.getLogger(ViewListingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean validateFields() {
        if (!guests.getText().equals("")) {
            try {
                Integer.parseInt(guests.getText());
            } catch (Exception e) {
                helperLabel.setText("Guests must be number");
                return false;
            }
        }
        return true;
    }
    
    private boolean validateCloseListing() {
        try {
            User user = hotelApp.fetchUser(email.getText());
            if(user == null || !password.getText().equals(user.getPassword())) {
                wrongUser.setText("Incorrect Username or Password");
                return false;
            }
        } catch (IOException_Exception | JAXBException_Exception ex) {
            return false;
        }
        wrongUser.setText("");
        return true;
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = username.getText();
            String stat = (String)status.getSelectedItem();
            String guestsNumber = guests.getText();
            int n;
            switch (e.getActionCommand()) {
                case "Clear":
                    helperLabel.setText("");
                    username.setText("");
                    status.setSelectedItem("");
                    guests.setText("");
                    update("", "", 0);
                    break;
                case "Search":
                    if (!validateFields()) {
                        return;
                    }
                    if (guestsNumber.equals("")) {
                        n = 0;
                    } else {
                        n = Integer.parseInt(guests.getText());
                    }
                    helperLabel.setText("");
                    update(user, stat, n);
                    break;
                case "Close Listing":
                    if (!validateCloseListing()) {
                        return;
                    } else {
                        helperLabel.setText("");
                    }
                    if (!validateFields()) {
                        return;
                    }
                    if (guestsNumber.equals("")) {
                        n = 0;
                    } else {
                        n = Integer.parseInt(guests.getText());
                    }
                    int id = (int)model.getValueAt(table.getSelectedRow(), 0);
                    try {
                        hotelApp.closeListing(id);
                    } catch (IOException_Exception | JAXBException_Exception ex) {
                        Logger.getLogger(ViewListingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    update(user, stat, n);
                    break;
                default:
                    break;
            }
        }
    }
}
