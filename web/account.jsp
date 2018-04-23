<%-- 
    Document   : account
    Created on : 06/10/2016, 7:29:48 PM
    Author     : andrew
--%>
<%@page import="Model.Listing"%>
<%@page import="Model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("user") == null) { %>
<c:redirect url="/index.jsp"/>
<% } %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account - Hotels.com</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="body">
            <div id="userInfo">
                <% User user = (User) session.getAttribute("user");%>
                Welcome <%= user.getName()%>!
                <%
                    pageContext.setAttribute("username", user.getUsername());
                    Listing inValid = null;
                    if (session.getAttribute("listing") != null) {
                        inValid = (Listing) session.getAttribute("listing");
                    }
                %>
            </div>
            <ul class="tab">
                <li><a href="#" class="tablinks <%= inValid == null ? "active" : ""%>" onclick="openTab(event, 'YourListings')">Your Listings</a></li>
                <li><a href="#" class="tablinks  <%= inValid != null ? "active" : ""%>" onclick="openTab(event, 'CreateListingForm')">Create a Listing</a></li>
            </ul>
            <script src="tabs.js" type="text/javascript"></script>
            <div id="YourListings" class="tabcontent <%= inValid == null ? "first" : ""%>">
                <% if (request.getParameter("closed") != null) { %>
                <div class="success">
                    Listing has been closed.
                </div>
                <% } %>
                <% if (request.getParameter("listingCreated") != null) {%>
                <div class="success">
                    Listing no.<%= request.getParameter("listingCreated")%> has been created.
                </div>
                <% } %>
                <% if (request.getParameter("notAllowed") != null) { %>
                <div class="danger">
                    Access Denied.
                </div>
                <% }%>
                <c:import var="xml" url="http://localhost:8080/hotel/rest/listings?username=${username}&status="/>
                <c:import url="http://localhost:8080/hotel/rest/listings/userListings.xsl" var="xslt"/>
                <x:transform xml="${xml}" xslt="${xslt}" />
            </div>
            <div id="CreateListingForm" class="tabcontent <%= inValid != null ? "first" : ""%>">
                <h3>Create a new listing</h3>
                <% if (request.getParameter("submitted") != null) { %>
                <p>Listing added!</p>
                <% } else {
                %>
                <form method="POST" action="createListing.jsp">
                    <input type="hidden" value="${username}" name="username"></input>
                    <div class="formGroup">
                        <div class="formLabel">Type</div> 
                        <select name="type">
                            <option value="Whole house" <%= inValid != null && inValid.getType().equals("Whole house") ? "selected" : ""%>>Whole house</option>
                            <option value="Whole unit" <%= inValid != null && inValid.getType().equals("Whole unit") ? "selected" : ""%>>Whole unit</option>
                            <option value="Bedroom" <%= inValid != null && inValid.getType().equals("Bedroom") ? "selected" : ""%>>Bedroom</option> 
                        </select>
                        <% if (request.getParameter("typeEmpty") != null) { %>
                        <div class="inputError">
                            The property type is required.
                        </div>
                        <% }%>
                    </div>
                    <div class="formGroup">
                        <div class="formLabel">Guests</div>
                        <input type="text" name="guests" placeholder="e.g. 4" value="<%= inValid != null && inValid.getGuests() != -1 ? inValid.getGuests() : ""%>"></input>
                        <% if (request.getParameter("guestsZero") != null) { %>
                        <div class="inputError">
                            There must be at least one person able to stay in your property.
                        </div>
                        <% }%>
                    </div>
                    <div class="formGroup">
                        <div class="formLabel">Description</div> 
                        <textarea rows="4" cols="50" name="description" placeholder="Describe the accomodation"><%= inValid != null ? inValid.getDescription() : ""%></textarea>
                        <% if (request.getParameter("descriptionEmpty") != null) { %>
                        <div class ="inputError">
                            You must include a description of the property.
                        </div>
                        <% } %>
                    </div>
                    <div class="formGroup">
                        <div class="formLabel">Address</div>
                        <input id="autocomplete" name="autocomplete" placeholder="Enter your address" onFocus="geolocate()" type="text" value="<%= session.getAttribute("autocomplete") != null ? session.getAttribute("autocomplete") : ""%>"></input>
                        <input id="street_number" name="streetNumber" type="hidden" value="<%= inValid != null ? inValid.getLocation().getNumber() : ""%>"></input>
                        <input class="field" id="route" name="street" type="hidden" value="<%= inValid != null ? inValid.getLocation().getStreet() : ""%>"></input>
                        <input class="field" id="locality" name="suburb" type="hidden" value="<%= inValid != null ? inValid.getLocation().getSuburb() : ""%>"></input>
                        <input class="field" id="administrative_area_level_1" name="state" type="hidden" value="<%= inValid != null ? inValid.getLocation().getState() : ""%>"></input>
                        <input class="field" id="postal_code" name="postcode" type="hidden" value="<%= inValid != null ? inValid.getLocation().getPostcode() : ""%>"></input>
                        <% if (request.getParameter("noAutoComplete") != null) { %>
                        <div class ="inputError">
                            Please use an address from the AutoComplete field.
                        </div>
                        <% } %>
                        <script src="maps.js" type="text/javascript"></script>
                    </div>  
                    <div class="formGroup">
                        <input type="submit" value="Create"></input>
                    </div>
                </form> 
                <% }%>
            </div>
        </div>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBlMER6O9z71DYKrE7JORf96715JKPa_AY&libraries=places&callback=initAutocomplete" async defer></script>

    </body>
</html>
