/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Runner
 */
public class User {

private String username, password, firstName, lastName, emailAddress, userStatus, userType;


public User()
{
super();
}
public User(String email,String password,String type)
{
this.emailAddress = email;
this.password = password;
this.userType = type;

}

public User(String username,String password, String firstName,String lastName, String emailAddress, String userStatus, String userType){
this.username = username;
this.password = password;
this.firstName = firstName;
this.lastName = lastName;
this.emailAddress = emailAddress;
this.userStatus = userStatus;
this.userType = userType;

}

public String getUsername(){
return this.username;
}

public String getPassword(){
return this.password;
}

public String getFirstName(){
return this.firstName;
}


public String getLastName(){
return this.lastName;
}

public String getEmailAddress(){
return this.emailAddress;
}

public String getUserStatus(){
return this.userStatus;
}

public String getUserType(){
return this.userType;
}
public void setUserStatus(String userStatus){
this.userStatus = userStatus;
}
}
