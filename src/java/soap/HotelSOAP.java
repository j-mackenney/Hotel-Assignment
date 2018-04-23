/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import Model.Listing;
import Model.ListingApplication;
import Model.Listings;
import Model.User;
import Model.UserApplication;
import Model.Users;
import java.io.IOException;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;


/**
 *
 * @author James
 */
@WebService(serviceName = "hotelApp")
public class HotelSOAP {

    @Resource
    private WebServiceContext context;

    private UserApplication getUserApp() throws JAXBException, IOException {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            UserApplication userApp = (UserApplication) application.getAttribute("userApp");
            if (userApp == null) {
                userApp = new UserApplication();
                userApp.setFilePath(application.getRealPath("WEB-INF/users.xml"));
                application.setAttribute("userApp", userApp);
            }
            return userApp;
        }
    }
    
    private ListingApplication getListingApp() throws JAXBException, IOException {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            ListingApplication listingApp = (ListingApplication) application.getAttribute("listingApp");
            if (listingApp == null) {
                listingApp = new ListingApplication();
                listingApp.setFilePath(application.getRealPath("WEB-INF/listings.xml"));
                application.setAttribute("listingApp", listingApp);
            }
            return listingApp;
        }
    }
    
    @WebMethod
    public void addListing(Listing listing) throws JAXBException, IOException {
        getListingApp().addListing(listing);
    }
    
    @WebMethod
    public void closeListing(int id) throws JAXBException, IOException {
        getListingApp().closeListing(id);
    }
    
    @WebMethod
    public Listings fetchListings(String username, String status, int guests) throws JAXBException, IOException {
        return getListingApp().getListings(username, status, guests);
    }
    
    @WebMethod
    public Users fetchUsers() throws JAXBException, IOException {
        return getUserApp().getUsers();
    }
    
    @WebMethod
    public User fetchUser(String email) throws JAXBException, IOException {
        return getUserApp().getUsers().getUser(email);
    }
    
    @WebMethod
    public User login(String email, String password) throws JAXBException, IOException {
        return getUserApp().login(email, password);
    }    
}