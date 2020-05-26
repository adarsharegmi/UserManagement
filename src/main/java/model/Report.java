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
public class Report {

private String emailAddress, userStatus, date;


public Report()
{
super();
}
public Report(String email,String userStatus,String Date)
{
this.emailAddress = email;
this.userStatus = userStatus;
this.date = Date;

}


public String getEmailAddress(){
return this.emailAddress;
}

public String getUserStatus(){
return this.userStatus;
}

public String getDate(){
return this.date;
}


}
