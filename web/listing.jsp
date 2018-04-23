<%-- 
    Document   : index
    Created on : 23/09/2016, 6:54:10 PM
    Author     : minniemanZ
--%>
<%@page import="Model.Listing"%>
<%@page import="Model.Enquiry"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<% String filePath = application.getRealPath("WEB-INF/listings.xml");%>
<jsp:useBean id="listingApp" class="Model.ListingApplication" scope="application">
    <jsp:setProperty name="listingApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>
<% if (request.getParameter("id") == null) { %>
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
    if (listing == null) {
%>
<c:redirect url="/index.jsp?notFound"/>
<% }
    pageContext.setAttribute("id", listingId);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listing - Hotels.com</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="body">
            <c:import var="xml" url="http://localhost:8080/hotel/rest/listings/${id}" />
            <c:import url="http://localhost:8080/hotel/rest/listings/listing.xsl" var="xslt"/>
            <x:transform xml="${xml}" xslt="${xslt}" />          
            <div id="EnquiryForm" class="tabcontent first">
                <h3>Enquire about listing</h3>
                <% if (request.getParameter("submitted") != null) { %>
                <p>Enquiry Submitted!</p>
                <% } else {
                    Enquiry inValid = null;
                    if (session.getAttribute("enquiry") != null) {
                        inValid = (Enquiry) session.getAttribute("enquiry");
                    }
                %>
                <form method="POST" action="enquiryAction.jsp">
                    <input type="hidden" value="${id}" name="id"></input>
                    <div class="formGroup">
                        <div class="formLabel">Name</div> 
                        <input type="text" name="name" placeholder="e.g. John Citizen" value="<%= inValid != null ? inValid.getName() : ""%>"></input>
                        <% if (request.getParameter("nameEmpty") != null) { %>
                        <div class="inputError">
                            The name field is required.
                        </div>
                        <% } %>
                        <% if (request.getParameter("nameInvalid") != null) { %>
                        <div class="inputError">
                            The name field must consist of a first and second name space separated.
                        </div>
                        <% }%>
                    </div>
                    <div class="formGroup">
                        <div class="formLabel">Email</div> 
                        <input type="text" name="email" placeholder="e.g. john@gmail.com" value="<%= inValid != null ? inValid.getEmail() : ""%>"></input>
                        <% if (request.getParameter("emailEmpty") != null) { %>
                        <div class="inputError">
                            The email field is required.
                        </div>
                        <% } %>
                        <% if (request.getParameter("emailInvalid") != null) { %>
                        <div class="inputError">
                            The email field is invalid.
                        </div>
                        <% }%>
                    </div>
                    <div class="formGroup">
                        <div class="formLabel">Start Date</div> 
                        <input type="text" name="startDate" placeholder="dd/mm/yyyy" value="<%= inValid != null ? inValid.getStartDate() : ""%>"></input>
                        <% if (request.getParameter("startDateEmpty") != null) { %>
                        <div class="inputError">
                            The start date field is required.
                        </div>
                        <% } %>
                        <% if (request.getParameter("startDateInvalid") != null) { %>
                        <div class="inputError">
                            The start date field must be in a dd/mm/yyyy format.
                        </div>
                        <% } %>
                        <% if (request.getParameter("dateRangeInvalid") != null) { %>
                        <div class="inputError">
                            The start date must be before the end date.
                        </div>
                        <% }%>
                    </div>
                    <div class="formGroup">
                        <div class="formLabel">End Date</div>
                        <input type="text" name="endDate" placeholder="dd/mm/yyyy" value="<%= inValid != null ? inValid.getEndDate() : ""%>"></input>
                        <% if (request.getParameter("endDateEmpty") != null) { %>
                        <div class="inputError">
                            The end date field is required.
                        </div>
                        <% } %>
                        <% if (request.getParameter("endDateInvalid") != null) { %>
                        <div class="inputError">
                            The end date field must be in a dd/mm/yyyy format.
                        </div>
                        <% }%>
                    </div>  
                    <div class="formGroup">
                        <div class="formLabel">Message</div>
                        <textarea rows="4" cols="50" name="message" placeholder="What do you want to enquire about?"><%= inValid != null ? inValid.getMessage() : ""%></textarea>
                        <% if (request.getParameter("messageEmpty") != null) { %>
                        <div class="inputError">
                            The message field is required.
                        </div>
                        <% } %>
                    </div>  
                    <div class="formGroup">
                        <input type="submit" value="Enquire"></input>
                    </div>
                </form>
                <% }%>    
            </div>
            <div id="MapHolder" class="tabcontent">
                <div id="map"></div>
            </div>
            <script src="maps.js" type="text/javascript"></script>
        </div>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtyJVyMZ_d22M68iTVHiJ-DrbBj-1Fw3s">
        </script>

    </body>
</html>
