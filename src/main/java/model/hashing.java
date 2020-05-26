/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.security.*;
import java.math.BigInteger;
/**
 *
 * @author Runner
 */
public class hashing {

public String hash(String str)
{
    String hashtext;
    try{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(str.getBytes());
        BigInteger no = new BigInteger(1,messageDigest);
        hashtext = no.toString(16);
        while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
    }catch(NoSuchAlgorithmException e){hashtext="";}
        
return hashtext;
}
}
