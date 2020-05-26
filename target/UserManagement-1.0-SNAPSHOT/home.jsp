
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
    String type = (String)session.getAttribute("type");
%>

<c:if test="${type == 'Admin'}">
   <jsp:include page ="static/navbar.jsp"/>
</c:if>

<c:if test="${type == 'Client'}">
   <jsp:include page ="static/navbar2.jsp"/>
</c:if>

<c:if test="${type == null}">
    <jsp:include page="static/navbar3.jsp"/>
</c:if>

<h3 style="">Welcome to the Home page ......... </h3>

<jsp:include page ="Data.jsp">
    <jsp:param name="count" value="${count}" />
  <jsp:param name="active" value="${active}" />
  <jsp:param name="block" value="${block}" />
</jsp:include>


<c:if test="${type == null}">
   <a href="loginpage" class="btn mb-3 mt-2 float-left" style="background-color: #c7defe;">Log in</a>
</c:if>

</body>
</html> 
