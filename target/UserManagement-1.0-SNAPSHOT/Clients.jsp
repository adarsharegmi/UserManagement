
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

 <jsp:include page ="static/navbar.jsp"/>
    
    <%
    String type = (String)session.getAttribute("type");
    Integer pagecount = (Integer)request.getAttribute("pagecount");
    %>

 <c:if test="${type == 'Admin'}">
    <h3>List of users Registered in the system </h3>
 </c:if>
    <div class="container">
 
        <table class="table">
         <thead class="thead-dark">
      <tr>
        <th>Username</th>
        <th>password</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Status</th>
        <th>UserType</th>
        <th>Change</th>
        <th>update</th>
        <th>History</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userlist}">
      <tr>
          <td><c:out value="${user.username}"/></td>
          <td><c:out value="${user.password}"/></td>
          <td><c:out value="${user.firstName}"/></td>
          <td><c:out value="${user.lastName}"/></td>
          <td><c:out value="${user.emailAddress}"/></td>
          <td><c:out value="${user.userStatus}"/></td>
          <td><c:out value="${user.userType}"/></td>
          <td><a href="block?uemail=<c:out value="${user.emailAddress}"/>"> <c:out value="${user.userStatus}"/></a></td>
          <td><a href="update?uemail=<c:out value="${user.emailAddress}"/>"> Update changes</a></td>
          <td><a href="history?uemail=<c:out value="${user.emailAddress}"/>"> View History</a></td>
          <td><a href="delete?uemail=<c:out value="${user.emailAddress}"/>"> Delete</a></td>
          
      </tr>
      </c:forEach>
    </tbody>
  </table>
    </div>

   
 <c:forEach var="i" begin="1"  end="${pagecount}">
     <a href ='clients?p=${i}'>${i}</a>
 </c:forEach>
     
</body>
</html> 
