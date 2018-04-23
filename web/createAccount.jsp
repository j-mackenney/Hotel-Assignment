<%-- 
    Document   : index
    Created on : 23/09/2016, 6:54:10 PM
    Author     : minniemanZ
--%>
<%@page import="Model.User"%>
<%@page import="Model.FormValidator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String filePath = application.getRealPath("WEB-INF/users.xml");%>
<jsp:useBean id="userApp" class="Model.UserApplication" scope="application">
    <jsp:setProperty name="userApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>
<%
    String username = request.getParameter("username");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");
    User temp = new User(email, name, password, username);
    session.setAttribute("tempUser", temp);
    String errors = FormValidator.getErrors(temp, confirmPassword);
    if (!errors.equals("")) {
        pageContext.setAttribute("errors", errors);
%>
<c:redirect url="/login.jsp?${errors}"/>
<% } else {
    userApp.addUser(temp);
    session.setAttribute("user", temp);
    session.removeAttribute("tempUser");
%>
<c:redirect url="/account.jsp"/>
<% }%>
