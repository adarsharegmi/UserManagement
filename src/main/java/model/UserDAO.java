/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import java.sql.*;
import model.*;
import java.util.*;  
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

/**
 *
 * @author Runner
 */
public class UserDAO implements ConnectionProperties{

    DB db;

    public UserDAO() throws SQLException{
    this.db = DB.getConnection();
    }

    



    public boolean insert(User u) throws SQLException{
        
        String sql = "insert into user values(?,?,?,?,?,?,?)";
        try{
        
        PreparedStatement ps = db.con.prepareStatement(sql);
        ps.setString(1,u.getUsername());  
        ps.setString(2,u.getPassword()); 
        ps.setString(3,u.getFirstName());   
        ps.setString(4,u.getLastName());
        ps.setString(5,u.getEmailAddress());
        ps.setString(6,u.getUserStatus());
        ps.setString(7,u.getUserType());
        ps.executeUpdate(); 
        ps.close();
        
        RecordInDb(u.getEmailAddress(),"Signed up",u.getUsername());
        return true;
        
        }catch(Exception e){
        
        return false;
        }
       
        

    }

   // check if the user is admin or not
    public boolean isAdmin(String Type){
   
    if (Type.equals("Admin")){
    return true;
    }else{
    return false;
    }
    }


    // check if user exists

    public int checkuser(String usern,String emailn) throws SQLException  {
      
        int val= 0;
        Statement ps=null;
        ResultSet rs=null;
        try{
            String query = "select * from user;";
            ps = db.con.createStatement();
            rs = ps.executeQuery(query);

            while (rs.next()){ 
                if (emailn.equals(rs.getString("emailAddress")))
                {
                    return 10;
                }else if (usern.equals(rs.getString("username")))
                {
                    return 5;
                }
                }

        }catch(Exception e){}
      
        return 0;
}
    // Getting the data from the database

    public User retrieve(String emailSe)throws SQLException
    {
        User usr = null;
        String pass="";
        String type="";
       

        try{
        
        String sql = "select * from user;";
        

        Statement ps=db.con.createStatement();
      
        ResultSet rs= ps.executeQuery(sql);
        
        // Iterating over the result
        while(rs.next()){
        if (emailSe.equals(rs.getString("emailAddress"))){ 
        pass = rs.getString("password"); 
        type = rs.getString("userType");
        
        usr = new User(emailSe,pass,type);
        break;
        }     
        }      
        }catch(Exception e){ pass="";}
       
        return usr;
    }

    // method to list all the user

    public List<User> listallUser() throws SQLException{
        
        List<User> userlist = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        
        Statement stmt = db.con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
    
        // creating user class and then adding to the list

        while(rs.next()){
            String username = rs.getString("username");  
            String password = rs.getString("password");
            String firstName = rs.getString("firstName");  
            String lastName = rs.getString("lastName");
            String emailAddress = rs.getString("emailAddress");
            String Status = rs.getString("Status");
            String userType = rs.getString("userType");

            // Creating a user object
            User user =new User(username,password,firstName,lastName,emailAddress,Status,userType);
            userlist.add(user);

            }

        rs.close();
        stmt.close();
        return userlist;
    }


 public boolean deleteUser(String email) throws SQLException {
        String sql = "DELETE FROM user where emailAddress = ?";
        
         
        PreparedStatement stmt = db.con.prepareStatement(sql);
        stmt.setString(1, email);

        boolean rowDeleted = stmt.executeUpdate() > 0;
        RecordInDb(email,"updated details","admin");
        stmt.close();
        return rowDeleted;     
    }



public boolean updateUser(User user) throws SQLException {
     
 try{
        String sql = "UPDATE user SET emailAddress = ?, firstName = ?, lastName = ?, password=?,userType=?, Status = ?";
        sql += " WHERE username = ?";
     
        
        PreparedStatement statement= db.con.prepareStatement(sql);
        
        statement.setString(1, user.getEmailAddress());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getPassword());
        statement.setString(5,user.getUserType());
        statement.setString(6, user.getUserStatus()); 
        statement.setString(7, user.getUsername());
        statement.executeUpdate();
        RecordInDb(user.getEmailAddress(),"updated details","admin");
        statement.close();
        return true;
        
        }catch(Exception e){}
        
            return false;
        
        
    }


    // REturns USer object if found in database

    public User getUser(String EmailSe) throws SQLException{
        String sql = "SELECT * FROM user ";
        sql+= "WHERE emailAddress = ? ";
    
        PreparedStatement stmt = db.con.prepareStatement(sql);
        stmt.setString(1, EmailSe);

        ResultSet rs = stmt.executeQuery();
        
        // creating user class and then adding to the list
        if(rs.next()){
            String username = rs.getString("username");  
            String password = rs.getString("password");
            String firstName = rs.getString("firstName");  
            String lastName = rs.getString("lastName");
            String emailAddress = rs.getString("emailAddress");
            String Status = rs.getString("Status");
            String userType = rs.getString("userType");
            
             User user = new User(username,password,firstName,lastName,emailAddress,Status,userType);
             return user;
            }
            return null;
    }




    // method to block the user if exists
    
    public void blockUser(String EmailSe) throws SQLException{

    User user = getUser(EmailSe);

        if(user.getUserStatus().equalsIgnoreCase("Active")){
            user.setUserStatus("Blocked");
        }else{
            user.setUserStatus("Active");
        }

        // calling the updating method    
        updateUser(user);   
        // after the task is performed it is recorded in the database
        RecordInDb(user.getUsername(),"updated details","admin");
        new HistoryDAO().insertReport(user, user.getUserStatus());
    }
    

    public String RecordInDb(String email, String activity,String Performer)throws SQLException
    {
    

        try{
        PreparedStatement ps=db.con.prepareStatement("insert into history values(?,?,?,?)");
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
          
        
        ps.setString(1,email);  
        ps.setString(2,activity); 
        ps.setString(3,dtf.format(now).toString());   
        ps.setString(4,Performer);
        
        ps.executeUpdate(); 
          return "";

        }catch(Exception e){
        return e.getMessage();
        }
      
       
    }


    public boolean isBlocked(String status) 
    {
    return status.equalsIgnoreCase("Blocked");
    }

   
   
}
