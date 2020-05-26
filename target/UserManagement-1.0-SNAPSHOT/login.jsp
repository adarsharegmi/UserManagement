<%-- 
    Document   : login
    Created on : May 12, 2020, 9:24:17 PM
    Author     : Runner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
    <title>Login page for user management</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.10.2/css/all.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container mt-0">    
        
            <div class="col-md-6 mx-auto bg-dark">
              
                <div style="background-color: #5DC3DA;">
                    <img src="images/upp.jpg" width="200px" height="200px" style= "margin-left: 150px;">
                </div>
                
                <form action="login" method="POST"  class="p-5 text-white" autocomplete="off">
                  
                  <div class="form-group">
                      <label for="email"><i class="fas fa-envelope"></i> Email</label>
                      <input type="email" name = "uemail"  class="form-control">
                  </div>
                  
                    <div class="form-group">
                      <label for="pwd"><i class="fas fa-lock"></i> Password</label>
                      <input type="password" name= "upassword" class="form-control" id="pwd">
                  </div>
                    
                    
                  <div class="form-group form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="checkbox"> Remember me
                      </label>
                  </div>
                  
               
                  <button type="submit" class="btn mb-3 mt-2 float-right" style="background-color: #c7defe;">Login</button>
                </form>
                
                <a href="registerpage" class="btn mb-3 mt-2 float-left" style="background-color: #c7defe;">Sign up</a>
            </div>
            </div>
  </div>
    
</body>
</html>
