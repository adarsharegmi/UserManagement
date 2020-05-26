
package model;


import java.sql.*;
import model.*;
import java.util.*;  
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


public class HistoryDAO implements  ConnectionProperties{

    DB db;

    // method to set the connection
    public HistoryDAO() throws SQLException{
    this.db = DB.getConnection();
    }


    public List<History> listhistory(String email) throws SQLException{

        List<History> historylist = new ArrayList<History>();
        String sql = "SELECT * FROM history where emailAddress = ?";
    
        PreparedStatement statement = db.con.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet rs = statement.executeQuery();
    
        // creating user class and then adding to the list

        while(rs.next()){
            String uemail = rs.getString("emailAddress");  
            String activity = rs.getString("activity");
            String date = rs.getString("Date");  
            String performer = rs.getString("Performer");

            // Creating a history object with intialization of the data
            History history =new History(uemail,activity,date,performer);
            historylist.add(history);
        }

        rs.close();
        statement.close();
        return historylist;
    }


    public boolean deleteHistory(String email,boolean record) throws SQLException {
        String sql = "DELETE FROM history where emailAddress = ?"; // Query to delete row from the table history 
         
        PreparedStatement stmt = db.con.prepareStatement(sql);
        stmt.setString(1, email);

        boolean rowDeleted = stmt.executeUpdate() > 0;
        
        if(record){
        new UserDAO().RecordInDb(email,"deleted history","admin");
        }

        stmt.close();
        return rowDeleted;     
    }


    public void insertReport(User u, String task) throws SQLException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  

        String sql = "insert into reportdetails values(?,?,?)";
        try{
        
        PreparedStatement ps = db.con.prepareStatement(sql);
        
        ps.setString(1,u.getEmailAddress());
        ps.setString(2,task);
        ps.setString(3,dtf.format(now).toString());
        ps.executeUpdate(); 
        ps.close();
        } catch(Exception e)  {}
    }



    public List<Report> generatereport(String date) throws SQLException{
    
    
        List<Report> reportlist = new ArrayList<Report>();
        String sql = "SELECT * FROM reportdetails where date = ? ";
    
        PreparedStatement statement = db.con.prepareStatement(sql);
        statement.setString(1, date);
        ResultSet rs = statement.executeQuery();
    
        // creating user class and then adding to the list

        while(rs.next()){
            
            String uemail = rs.getString("emailAddress");  
            String activity = rs.getString("status");
            String daten = rs.getString("Date");  

            // Creating a Report object
            Report r =new Report(uemail,activity,daten);
            reportlist.add(r);

            }

        rs.close();
        statement.close();
        return reportlist;
    }

}
