package rest;
 
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import Model.*;
import com.sun.jersey.api.provider.jaxb.XmlHeader;
 
@Path("/users")
public class UserService {
    @Context
    private ServletContext application;
 
    private UserApplication getUserApp() throws JAXBException, IOException {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        synchronized (application) {
            UserApplication userApp = (UserApplication)application.getAttribute("userApp");
            if (userApp == null) {
                userApp = new UserApplication();
                userApp.setFilePath(application.getRealPath("WEB-INF/users.xml"));
                application.setAttribute("userApp", userApp);
            }
            return userApp;
        }
    }
    
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @XmlHeader("<?xml-stylesheet type='text/xsl' href='http://localhost:8080/hotel/rest/users/users.xsl'?>")
    
    public Users getUsers() throws JAXBException, IOException {
        return getUserApp().getUsers();
    }
    
    @Path("/users.xsl")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public File getFile() {
        return new File(application.getRealPath("WEB-INF/users.xsl"));
    }
    
    @Path("/{email}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public User getUser(@PathParam("email") String email) throws JAXBException, IOException {
        return getUserApp().getUser(email);
    }
    
    @Path("/addUser")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void addUser(User user) throws JAXBException, IOException {
        getUserApp().addUser(user);
    }
 
 /** YOUR METHODS WILL BE INSERTED HERE **/
}