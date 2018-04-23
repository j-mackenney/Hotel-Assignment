/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.*;
import javax.xml.rpc.ServiceException;


/**
 *
 * @author James
 */

public class HotelSOAPClient extends JFrame {
    public static void main(String[] args) throws IOException_Exception, JAXBException_Exception, ServiceException, RemoteException {
        HotelApp locator = new HotelApp();
        HotelSOAP hotelApp = locator.getHotelSOAPPort();
        
        new HotelSOAPClient(hotelApp);
    }
    
    public HotelSOAPClient(HotelSOAP hotelApp) throws IOException_Exception, JAXBException_Exception {
        setup();
        build(hotelApp);
        pack();
        setVisible(true);
    }
    
    private void setup() {
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void build(HotelSOAP hotelApp) throws IOException_Exception, JAXBException_Exception {
        add(new MainPanel(hotelApp));
    }
}
