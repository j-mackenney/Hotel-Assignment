package rest;
 
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import Model.*;
import com.sun.jersey.api.provider.jaxb.XmlHeader;
 
@Path("/listings")
public class ListingService {
    @Context
    private ServletContext application;
 
    private ListingApplication getListingApp() throws JAXBException, IOException {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        synchronized (application) {
            ListingApplication listingApp = (ListingApplication)application.getAttribute("listingApp");
            if (listingApp == null) {
                listingApp = new ListingApplication();
                listingApp.setFilePath(application.getRealPath("WEB-INF/listings.xml"));
                application.setAttribute("listingApp", listingApp);
            }
            return listingApp;
        }
    }
    
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Listings getListings(@DefaultValue("") @QueryParam("username") String username,
		@DefaultValue("available") @QueryParam("status") String status, @DefaultValue("1") @QueryParam("guests") String guests) throws JAXBException, IOException {
        int guestsValue;
        try {
            guestsValue = Integer.parseInt(guests);
        } catch(NumberFormatException e) {
            guestsValue = 1;
        }
        return getListingApp().getListings(username, status, guestsValue);
    }
    
    @Path("/listings.xsl")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public File getFile() {
        return new File(application.getRealPath("WEB-INF/listings.xsl"));
    }
    
    @Path("/listing.xsl")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public File getListingFile() {
        return new File(application.getRealPath("WEB-INF/listing.xsl"));
    }
    
    @Path("/userListings.xsl")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public File getUserListingFile() {
        return new File(application.getRealPath("WEB-INF/userListings.xsl"));
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Listing getListing(@PathParam("id") int id) throws JAXBException, IOException {
        return getListingApp().findListing(id);
    }
 /** YOUR METHODS WILL BE INSERTED HERE **/
}
