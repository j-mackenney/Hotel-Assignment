<%-- 
    Document   : index
    Created on : 23/09/2016, 6:54:10 PM
    Author     : minniemanZ
--%>
<%@page import="java.net.URLDecoder"%>
<%@page import="Model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Hotels.com</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="body">
            <% if (request.getParameter("loggedOut") != null) { %>
            <div class="success">
                Successfully logged out.
            </div>
            <% }
                if (request.getParameter("notAllowed") != null) { %>
            <div class="danger">
                Cannot do this action.
            </div>
            <% }
                if (request.getParameter("notFound") != null) { %>
            <div class="infoAlert">
                The resource does not exist. Please use the links to navigate.
            </div>

            <% }
                String queryString;
                try {
                    queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
                } catch (Exception e) {
                    queryString = "";
                }
                pageContext.setAttribute("queryString", queryString);
            %>
            Filter by type:
            <select id="filter">
                <option selected="selected">Show all</option>
                <option>Whole house</option>
                <option>Whole unit</option>
                <option>Bedroom</option>
            </select>
            <c:import var="xml" url="http://localhost:8080/hotel/rest/listings?${queryString}" />
            <c:import url="http://localhost:8080/hotel/rest/listings/listings.xsl" var="xslt"/>
            <x:transform xml="${xml}" xslt="${xslt}" />
        </div>
        <script src="filter.js"></script>
    </body>
</html>
