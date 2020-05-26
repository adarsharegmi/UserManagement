<%-- 
    Document   : report.jsp
    Created on : May 24, 2020, 12:06:48 PM
    Author     : Runner
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate report</title>
    </head>
    <body>
        <h1>Just enter the date and generate the report based upon the date </h1>
        
        <form action="generate">
            <input type="text" placeholder="Enter the date you want the data of :: " name="date"/>
            <input type="Submit">
        </form>

        
        
          <!--show  report when searched  by date -->
          <h2 style="margin: 50px 0px 0px 500px;">Report generation</h2>
          
          
          <h4>The list of the users blocked at the given date ::  </h4>
        <c:forEach var="report" items="${rlist}">
                
            <ol><p> >> The user with email address        
          <c:out value="${report.emailAddress}"/>'s status was changed to
          <c:out value="${report.userStatus}"/> at 
          <c:out value="${report.date}"/> by the admin</ol>
         </p>
         
      </c:forEach>
                
          
    </body>
</html>
