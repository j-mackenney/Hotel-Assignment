<%-- 
    Document   : login
    Created on : Sep 24, 2016, 2:59:01 PM
    Author     : andrewbird
--%>

<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<% if (session.getAttribute("user") != null) { %>
<c:redirect url="/account.jsp"/>
<% } %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login or Sign up - Hotels.com</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="body">
            <div class="content">
                <div id="login">
                    <p class="instruction">Please login to see and manage your properties.</p>
                    <form method="post" action="loginAction.jsp">
                        <div class="formGroup">
                            <div class="formLabel">Email</div> 
                            <input type="text" name="email" value="<%= session.getAttribute("loginEmail") != null ? session.getAttribute("loginEmail") : ""%>"></input>
                            <% if (request.getParameter("loginEmailEmpty") != null) { %>
                            <div class="inputError">
                                The email field is required.
                            </div>
                            <% } %>
                        </div>
                        <div class="formGroup">
                            <div class="formLabel">Password</div> 
                            <input type="password" name="password" value="<%= session.getAttribute("loginPassword") != null ? session.getAttribute("loginPassword") : ""%>"></></input>
                            <% if (request.getParameter("loginPasswordEmpty") != null) { %>
                            <div class="inputError">
                                The password field is required.
                            </div>
                            <% } %>
                            <% if (request.getParameter("loginIncorrect") != null) { %>
                            <div class="inputError">
                                Invalid credentials given.
                            </div>
                            <% } %>
                        </div>
                        <div class="formGroup">
                            <input type="submit" value="Login"></input>
                        </div>
                    </form>
                </div>
                <div id="signUp">
                    <p class="instruction">Don't have an account? Sign up below.</p>
                    <form method="post" action="createAccount.jsp">
                        <%
                            User inValid = null;
                            if (session.getAttribute("tempUser") != null) {
                                inValid = (User) session.getAttribute("tempUser");
                            }
                        %>
                        <div class="formGroup">
                            <div class="formLabel">Username</div> 
                            <input type="text" name="username" value="<%= inValid != null ? inValid.getUsername() : ""%>"></input>
                            <% if (request.getParameter("userNameEmpty") != null) { %>
                            <div class="inputError">
                                The username field is required.
                            </div>
                            <% }%>
                        </div>
                        <div class="formGroup">
                            <div class="formLabel">Name</div> 
                            <input type="text" name="name" value="<%= inValid != null ? inValid.getName() : ""%>"></input>
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
                            <input type="text" name="email" value="<%= inValid != null ? inValid.getEmail() : ""%>"></input>
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
                            <div class="formLabel">Password</div> 
                            <input type="password" name="password" value="<%= inValid != null ? inValid.getPassword() : ""%>"></input>
                            <% if (request.getParameter("passwordEmpty") != null) { %>
                            <div class="inputError">
                                The password field is required.
                            </div>
                            <% }%>
                        </div>
                        <div class="formGroup">
                            <div class="formLabel">Confirm Password</div> 
                            <input type="password" name="confirmPassword"></input>
                            <% if (request.getParameter("confirmPasswordEmpty") != null) { %>
                            <div class="inputError">
                                The confirm password field is required.
                            </div>
                            <% }%>
                            <% if (request.getParameter("differentPasswords") != null) { %>
                            <div class="inputError">
                                Passwords do not match.
                            </div>
                            <% }%>
                        </div>
                        <div class="formGroup">
                            <input type="submit" value="Sign up"></input>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
