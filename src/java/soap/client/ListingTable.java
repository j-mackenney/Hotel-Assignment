/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author James
 */
public class ListingTable extends AbstractTableModel {

    HotelSOAP hotelApp;
    Listings listings;
    
    private final int cols = 7;
    private final String[] columnNames = {
        "ID", "Status", "Type", "Guests",
        "Username", "description", "location"
    };
    
    public ListingTable(HotelSOAP hotelApp) throws IOException_Exception, JAXBException_Exception {
        this.hotelApp = hotelApp;
        this.listings = hotelApp.fetchListings("", "", 0);
    }
    
    @Override
    public String getColumnName(int col) {   
        return columnNames[col];    
    }

    public void update(Listings listings) {
        this.listings = listings;
    }

    @Override
    public int getRowCount() {
        return listings.getListing().size();
    }

    @Override
    public int getColumnCount() {
        return cols;
    }

    @Override
    public Object getValueAt(int row, int col) {
        int listing = row;
        switch (col) {
            case 0:
                return listings.getListing().get(listing).getId();
            case 1:
                return listings.getListing().get(listing).getStatus();
            case 2:
                return listings.getListing().get(listing).getType();
            case 3:
                return listings.getListing().get(listing).getGuests();
            case 4:
                return listings.getListing().get(listing).getListerUsername();
            case 5:
                return listings.getListing().get(listing).getDescription();
            case 6:
                Location location = listings.getListing().get(listing).getLocation();
                String loc = "";
                loc += location.getNumber() + " " + location.getStreet()
                        + " " + location.getSuburb() + " " + location.getPostcode() + " "
                        + location.getState();
                return loc;
            default:
                return "";
        }
    }
}
