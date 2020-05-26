<%-- 
    Document   : Data.jssp
    Created on : May 25, 2020, 9:20:33 PM
    Author     : Runner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    
<div class="row">
  <div class="col-sm-4">
      
<div class="card bg-primary text-white">
    <img class="card-img" src="https://pngtree.com/so/technological-sense" alt="Card image" height="150px">
  <div class="card-img-overlay">
    <h5 class="card-title">Total Registered users</h5>
    <p class="card-text"><%= request.getParameter("count")%></p>
  </div>
</div>
</div>
  
   <div class="col-sm-4">
      
<div class="card bg-primary text-white">
    <img class="card-img" src="https://pngtree.com/so/technological-sense" alt="Card image" height="150px">
  <div class="card-img-overlay">
    <h5 class="card-title">Total Active Users</h5>
    <p class="card-text"><%= request.getParameter("active")%></p>
  </div>
</div>
</div>
  
  
  
   <div class="col-sm-4">
      
<div class="card bg-primary text-white">
    <img class="card-img" src="https://pngtree.com/so/technological-sense" alt="Card image" height="150px">
  <div class="card-img-overlay">
    <h5 class="card-title">Total Blocked users</h5>
    <p class="card-text"><%= request.getParameter("block")%></p>
  </div>
</div>
</div>
  
  
</div>