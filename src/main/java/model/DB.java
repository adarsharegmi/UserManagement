package model;


import java.sql.*;

public class DB implements ConnectionProperties {

Connection con;

private static DB dc;

 
// this class allows creation of single class object ie Singleton Design pattern

private DB() throws SQLException
{
    try {
        Class.forName(jbdc);
        this.con= DriverManager.getConnection(BASE_URL, USER, PASS);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
public static DB getConnection() throws SQLException{
   if (dc==null){
    dc = new DB();
    }
    return dc;
}


}
