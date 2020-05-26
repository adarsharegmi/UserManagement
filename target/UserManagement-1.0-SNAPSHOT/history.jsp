<%-- 
    Document   : history
    Created on : May 22, 2020, 1:36:33 PM
    Author     : Runner
--%>

<%@page import="model.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<table>
         <thead >
      <tr>
        <th>Activity</th>
        <th>Date</th>
        <th>Performer</th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach var="his" items="${historylist}">
        
      <tr>
          <td><c:out value="${his.activity}"/></td>
          <td><c:out value="${his.date}"/></td>
          <td><c:out value="${his.performer}"/></td>
          
      </tr>
      </c:forEach>
    </tbody>
  </table>