<%-- 
    Document   : header.jsp
    Created on : 26/09/2016, 8:47:43 PM
    Author     : minniemanZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.User" %>
<div id="header">
    Hotels.com<br>
    <a href="index.jsp">Home</a>
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
    </br>Welcome, <%=user.getName()%>
    <a href="account.jsp">My Account</a> | <a href="logoutAction.jsp">Logout</a>
        <% } else { %>
    </br><a href="login.jsp">Login/Sign Up</a>
    <% }%>
    <div class="listingFilter">
        <form action="index.jsp" method="GET">
            <div class="searchInput">
                Username <input type="text" name="username">
            </div>
            <div class="searchInput">
                Status 
                <select name="status">
                    <option value="available">Available</option>
                    <option value="unavailable">Unavailable</option> 
                </select>
            </div>
            <div class="searchInput">
                Number of Guests <input type="text" name="guests">
            </div>
            <div class="searchInput">
                <input type="submit" value="Search">
            </div>    
        </form>
    </div>
</div>
