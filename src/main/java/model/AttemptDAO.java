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


import java.sql.*;
import model.*;
import java.util.*; 


public class AttemptDAO implements ConnectionProperties{

  DB db;

    public AttemptDAO() throws SQLException{
    this.db = DB.getConnection();
    }
//     protected void disconnect() throws SQLException {
//        if (con != null && !con.isClosed()) {
//            con.close();
//        }
//    }
     

    
public void recordAttempts(String email) throws SQLException{
    int attempts=1;
    String sql = "insert into attempt values(?,?)";
    String sql2 = "UPDATE attempt SET attempts = ? WHERE emailAddress = ?";
    String sq = "select attempts from attempt where emailAddress = ? ";

    PreparedStatement statement = db.con.prepareStatement(sq);
    statement.setString(1, email);
    ResultSet rs = statement.executeQuery();
    if(rs!=null){
        while(rs.next()){
            attempts = rs.getInt("attempts");
            }

        rs.close();
        statement.close();

    // we retrieved the data now updating the data if attempts 

        
        attempts+=1;
        if(attempts%5==0){  // this allows to proceed when admin activates client
            new UserDAO().blockUser(email);
        }else{
        
        PreparedStatement ps = db.con.prepareStatement(sql2);
        ps.setInt(1,attempts);
        ps.setString(2,email);
        ps.executeUpdate(); 
        ps.close();
        }


            // if the attempts reaches 5 it is either blocked or sent mail
        
        

    }else{
        PreparedStatement ps =db.con.prepareStatement(sql);
        ps.setString(1,email);
        ps.setInt(2,attempts);
        ps.executeUpdate(); 
        ps.close();
    
    }
    }


    // method to clear the attempts 
    public void clearAttempts(String email) throws SQLException{
    int attempts=0;
    String sql = "UPDATE attempt SET attempts = 0 WHERE emailAddress = ?";
    PreparedStatement ps =db.con.prepareStatement(sql);
    ps.setString(1,email);
    ps.executeUpdate(); 
    ps.close();
    }
}