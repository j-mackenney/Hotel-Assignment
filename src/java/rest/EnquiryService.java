package rest;
 
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import Model.*;
import com.sun.jersey.api.provider.jaxb.XmlHeader;
 
@Path("/enquiries")
public class EnquiryService {
    @Context
    private ServletContext application;
 
    private EnquiryApplication getEnquiryApp() throws JAXBException, IOException {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        synchronized (application) {
            EnquiryApplication enquiryApp = (EnquiryApplication)application.getAttribute("enquiryApp");
            if (enquiryApp == null) {
                enquiryApp = new EnquiryApplication();
                enquiryApp.setFilePath(application.getRealPath("WEB-INF/enquiries.xml"));
                application.setAttribute("enquiryApp", enquiryApp);
            }
            return enquiryApp;
        }
    }
    
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Enquiries getEnquiries() throws JAXBException, IOException {
        return getEnquiryApp().getEnquiries();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Enquiries getEnquiries(@PathParam("id") String id) throws JAXBException, IOException {
        return getEnquiryApp().getEnquiries(id);
    }
    
    
    @Path("/enquiries.xsl")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public File getListingFile() {
        return new File(application.getRealPath("WEB-INF/enquiries.xsl"));
    }
 /** YOUR METHODS WILL BE INSERTED HERE **/
}
