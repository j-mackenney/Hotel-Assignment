<%-- 
    Document   : createListing
    Created on : 12/10/2016, 4:41:30 PM
    Author     : Andrew
--%>

<%@page import="Model.FormValidator"%>
<%@page import="Model.Listing"%>
<%@page import="Model.Location"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.ListingApplication"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String filePath = application.getRealPath("WEB-INF/listings.xml");%>
<jsp:useBean id="listingApp" class="Model.ListingApplication" scope="application">
    <jsp:setProperty name="listingApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>
<%
    int id = (listingApp.getAllListings().countListing() + 1);
    pageContext.setAttribute("id", id);
    String status = "available";
    String username = request.getParameter("username");
    String type = request.getParameter("type");
    String description = request.getParameter("description");
    String guests = request.getParameter("guests");
    
    int iguests;
    try {
        iguests = Integer.parseInt(guests);
    } catch (NumberFormatException e) {
        iguests = -1;
    }
    
    int istreetNumber;
    String streetNumber = request.getParameter("streetNumber");
    try {
        istreetNumber = Integer.parseInt(streetNumber);
    } catch (NumberFormatException e) {
        istreetNumber = -1;
    }
    
    String street = request.getParameter("street");
    String suburb = request.getParameter("suburb");
    String state = request.getParameter("state");
    
    String postcode = request.getParameter("postcode");
    int ipostcode;
    try {
        ipostcode = Integer.parseInt(postcode);
    } catch (NumberFormatException e) {
        ipostcode = -1;
    }
    
    Location location = new Location(istreetNumber, street, suburb, state, ipostcode);
    Listing listing = new Listing(id, status, type, iguests, username, description, location);

    session.setAttribute("listing", listing);
    String errors = FormValidator.getListingErrors(listing);
    if (!errors.equals("")) {
        pageContext.setAttribute("errors", errors);
        session.setAttribute("autocomplete", request.getParameter("autocomplete"));
%>
<c:redirect url="/account.jsp?${errors}#"/>
<%
} else {
    listingApp.addListing(listing);
    session.removeAttribute("listing");
    session.removeAttribute("autocomplete");
%>
<c:redirect url="/account.jsp?listingCreated=${id}"/>
<%    }%>
