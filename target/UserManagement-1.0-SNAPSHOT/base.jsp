<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
    <title>Registration page for user management</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.10.2/css/all.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<body>
  <div class="register-form mt-3 mb-3">
    <div class="container">
          <div class="row"  style="font-family: Georgia, serif;" >
              <div class="col-md-6 d-none d-md-block" style="background-color: #c7defe;">
                <h1 class="text-center pt-3">Create Account<br> Now</h1>
                <br><br><br>
                <img src="images/signin.jpg" width="500" style="margin-left: 30px;"/>
                <br><br>
                <h3 class="text-center">Connecting people around the world</h3>
            </div>
            <div class="col-md-6 bg-dark">
                <form action="register" method="POST" class="p-4 text-white" autocomplete="off">
                  <div class="form-group">
                      <label for="name"><i class="fas fa-user"></i> UserName</label>
                      <input type="text" name = "uname" class="form-control" required>
                  </div>
                  <div class="form-group">
                      <label for="email"><i class="fas fa-envelope"></i> Email</label>
                      <input type="email" name = "uemail"  class="form-control" id="email" required>
                  </div>
                  
                    <div class="form-group">
                      <label for="pwd"><i class="fas fa-lock"></i> Enter Password</label>
                      <input type="password" name= "upassword" class="form-control" id="upa1" onkeyup="check();"  required>
                  </div>
                    
                     <div class="form-group">
                      <label for="pwd"><i class="fas fa-lock"></i> Confirm Password</label>
                      <input type="password" name= "ucheckpassword" class="form-control"  id="upa2" onkeyup="check();" required>
                      
                     </div>
                    
                    <div class="form-group">
                      <label for="first-name"><i class="fas fa-user"></i> First Name</label>
                      <input type="text" name = "ufirstname" class="form-control" required>
                  </div>
                    
                    <div class="form-group">
                      <label for="lastname"><i class="fas fa-user"></i> Last Name</label>
                      <input type="text" name = "ulastname" class="form-control" required>
                  </div>
                    <div class="form-group">
                      <label for="usertype"><i class="fas fa-users-crown"></i>User type</label>
                      <select name = "uusertype" id="userttype"  class="form-control" required>
                          <option value="Admin">Admin</option>
                          <option value ="Client">Client</option>
                      </select>
                  </div>
                    
                  <div class="form-group form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="checkbox"> Remember me
                      </label>
                  </div>
                  <button type="submit" class="btn mb-3 mt-3 float-right" style="background-color: #c7defe;">Register</button>
                </form>

            </div>
          </div>
      </div>
  </div>
    <div>
            
    </div>
</body>

<script>
var password = document.getElementById("upa1")
  , confirm_password = document.getElementById("upa2"),
          email = document.getElementById("email");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
  
}
upa1.onchange = validatePassword;
upa2.onkeyup = validatePassword;
email.onchange = validateEmail;

function validateEmail(){
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value))
    {
        email.setCustomValidity("");
    }else{
        email.setCustomValidity("Email address is not valid");
    }
}
    
    
</script>


</html>
