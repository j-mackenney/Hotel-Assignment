<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="Model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String filePath = application.getRealPath("WEB-INF/users.xml");%>
<jsp:useBean id="userApp" class="Model.UserApplication" scope="application">
    <jsp:setProperty name="userApp" property="filePath" value="<%=filePath%>"/>
</jsp:useBean>

<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    session.setAttribute("loginEmail", email);
    session.setAttribute("loginPassword", password);
    String errors = FormValidator.getErrors(email, password);
    if (!errors.equals("")) {
        pageContext.setAttribute("errors", errors);
%>
<c:redirect url="/login.jsp?loginError${errors}"/>
<%
    }
    Users users = userApp.getUsers();
    User user = users.login(email, password);
    if (user != null) { // ie the login worked
        session.setAttribute("user", user);
        session.removeAttribute("loginEmail");
        session.removeAttribute("loginPassword");
%>
<c:redirect url="/account.jsp"/>
<% } else { %>
<c:redirect url="/login.jsp?loginIncorrect"/>
<% }%>
