package Model;
 
import java.util.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "enquiries", namespace="http://www.uts.edu.au/31284/wsd-diary/enquiries")
public class Enquiries implements Serializable {
    @XmlElement(name = "enquiry")
    private ArrayList<Enquiry> list = new ArrayList<Enquiry>();
 
    public ArrayList<Enquiry> getList() {
        return list;
    }
    //adds an enquiry to the xml "database"
    //Takes an Enquiry object as an argument
    public void addEnquiry(Enquiry enquiry) {
        list.add(enquiry);
    }

    public Enquiries filter(String id) {
        Enquiries filteredList = new Enquiries();
        for (Enquiry enquiry: list) {
            if (enquiry.getListingId().equals(id)) {
                filteredList.addEnquiry(enquiry);
            }
        }
        return filteredList;
    }
}