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
public class History {

private String username,activity, date,performer;

public  History(String username, String activity, String date, String performer)
{
    this.username = username;
    this.date =date;
    this.activity = activity;
    this.performer = performer;
}

public String getUsername(){return this.username;}
public String getDate(){return this.date;}
public String getActivity(){return this.activity;}
public String getPerformer(){return this.performer;}


}
