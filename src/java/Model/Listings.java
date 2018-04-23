package Model;
 
import java.util.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "listings", namespace="http://www.uts.edu.au/31284/wsd-diary/listings")
public class Listings implements Serializable {
    @XmlElement(name = "listing")
    private ArrayList<Listing> list = new ArrayList<Listing>();
 
    public ArrayList<Listing> getList() {
        return list;
    }
    //adds a listing
    public void addListing(Listing listing) {
        list.add(listing);
    }
    //removes a listing
    public void removeListing(Listing listing) {
        list.remove(listing);
    }
    //returns the listing when an id is specified, if no listing exists for the id that was passed, returns null
    public Listing getListing(int id) {
        for (Listing listing : list) {
            if (listing.getId() == id)
                return listing;
        }
        return null;
    }
    
    public Listings filter(String username, String status, int guests) {
        Listings filteredList = new Listings();
        for (Listing listing : list) {
            int checks = 0;
            if (username.equals(listing.getListerUsername()) || username.isEmpty()) {
                checks++;
            }
            if (status.equals(listing.getStatus()) || "".equals(status)) {
                checks++;
            }
            if (listing.getGuests() >= guests) {
                checks++;
            }
            if (checks == 3) {
                filteredList.addListing(listing);
            }
        }
        return filteredList;
    }
    //Sets the status of a listing to closed/unavailable
    public void closeListing(Listing listing) {
        listing.setStatus("unavailable");
    }
    //Counts the amount of listings returns an integer
    public int countListing() {
        int count = getList().size();
        return count;
    }
}