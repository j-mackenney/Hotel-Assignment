<%-- 
    Document   : index
    Created on : 23/09/2016, 6:54:10 PM
    Author     : minniemanZ
--%>
<%@page import="Model.Listing"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="Model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<% String filePath = application.getRealPath("WEB-INF/listings.xml");%>
<jsp:useBean id="listingApp" class="Model.ListingApplication" scope="application">
    <jsp:setProperty name="listingApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>
<% if (request.getParameter("id") == null || session.getAttribute("user") == null) { %>
<c:redirect url="/index.jsp?notFound"/>
<% }
    int listingId = 0;
    try {
        listingId = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException e) {
%>
<c:redirect url="/index.jsp?notFound"/>
<% }
    Listing listing = listingApp.findListing(listingId);
    User user = (User) session.getAttribute("user");
    if (!user.ownsListing(listing)) {
%>
<c:redirect url="/index.jsp?notFound"/>
<% }
    pageContext.setAttribute("id", listingId);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enquiries - Hotels.com</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="body">
            <c:import var="xml" url="http://localhost:8080/hotel/rest/enquiries/${id}" />
            <c:import url="http://localhost:8080/hotel/rest/enquiries/enquiries.xsl" var="xslt"/>
            <x:transform xml="${xml}" xslt="${xslt}" />
        </div>
    </body>
</html>
