<%--
    Document   : index
    Created on : 23/09/2016, 6:54:10 PM
    Author     : minniemanZ
--%>
<%@page import="soap.client.HotelSOAP"%>
<%@page import="soap.client.HotelApp"%>
<%@page import="Model.User"%>
<%@page import="Model.Listing"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String filePath = application.getRealPath("WEB-INF/listings.xml");%>
<jsp:useBean id="listingApp" class="Model.ListingApplication" scope="application">
    <jsp:setProperty name="listingApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>
<% if (session.getAttribute("user") == null) { %>
<c:redirect url="/index.jsp?notAllowed"/>
<% }
    int listingId = 0;
    try {
        listingId = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException e) {
%>
<c:redirect url="/index.jsp?notAllowed"/>
<%
    }
    User user = (User) session.getAttribute("user");
    Listing listing = listingApp.findListing(listingId);
    if (user.ownsListing(listing)) {
        HotelApp locator = new HotelApp();
        HotelSOAP hotelApp = locator.getHotelSOAPPort();
        hotelApp.closeListing(listingId);
%>
    <c:redirect url="/account.jsp?closed"/>
<%    
    }
%>
<c:redirect url="/account.jsp?notAllowed"/>
