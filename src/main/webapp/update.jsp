<%-- 
    Document   : update
    Created on : May 22, 2020, 5:42:41 AM
    Author     : Runner
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit changes</title>
    </head>
    <body>
        <h1>This page enables user to change the details of the user </h1>
        <form action="updateuser"  method="POST">
            Welcome to <label><c:out value="${user.username}"/></label>'s editing page:: <br>
            
            <input name="rname" value="<c:out value="${user.username}"/>" readonly/>
            <br>Username remains Unchanged<br>
            <input type="text" name="remail" value="<c:out value="${user.emailAddress}"/>"/>
            <input type="password" name="rpass" value="<c:out value="${user.password}"/>"/>
            <input type="hidden" name="ppass" value="<c:out value="${user.password}"/>"/> 
            <input type="text" name="rfirstname" value="<c:out value="${user.firstName}"/>"/>
            <input type="text" name="rlastname" value="<c:out value="${user.lastName}"/>"/>
            <input type="text" name="rusertype" value="<c:out value="${user.userType}"/>"/>
            <input name="rstatus" value="<c:out value="${user.userStatus}"/>" readonly/>
            <input type="submit" value="Update"/>
        </form>
    </body>
</html>
