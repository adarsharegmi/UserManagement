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
public class Attempt {

private String emailAddress;
private int attempts;

public Attempt(String emailAddress, int attempts){
    this.emailAddress = emailAddress;
    this.attempts = attempts;
}


public String getEmailAddress(){return this.emailAddress;}
public int getAttempts(){return this.attempts;}

}
