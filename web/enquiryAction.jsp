<%-- 
    Document   : index
    Created on : 23/09/2016, 6:54:10 PM
    Author     : minniemanZ
--%>
<%@page import="Model.FormValidator"%>
<%@page import="Model.Enquiry"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String filePath = application.getRealPath("WEB-INF/enquiries.xml");%>
<jsp:useBean id="enquiryApp" class="Model.EnquiryApplication" scope="application">
    <jsp:setProperty name="enquiryApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    String message = request.getParameter("message");
    String listingId = request.getParameter("id");
    pageContext.setAttribute("id", listingId);
    Enquiry enquiry = new Enquiry(name, email, startDate, endDate, message, listingId);
    session.setAttribute("enquiry", enquiry);
    String errors = FormValidator.getErrors(enquiry);
    if (!errors.equals("")) {
        pageContext.setAttribute("errors", errors);
%>
<c:redirect url="/listing.jsp?id=${id}${errors}"/>
<% } else {
    enquiryApp.addEnquiry(enquiry);
    session.removeAttribute("enquiry");
%>
<c:redirect url="/listing.jsp?id=${id}&submitted="/>
<% }%>
