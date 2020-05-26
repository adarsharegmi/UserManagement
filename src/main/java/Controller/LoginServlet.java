/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import java.util.List;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Runner
 */

public class LoginServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String action = request.getServletPath();

        try{
            switch(action){
            case "/":
                index(request,response);
                break;
            case "/register":
                registeruser(request,response);
                break;
            case "/block":
                blockuser(request,response);
                break;
            case "/history":
                viewhistory(request,response);
                break;
            case "/myhistory":
                viewhistory(request,response);
                break;
            case "/loginpage":
                loginpage(request,response);
                break;
            case "/login":
                loginuser(request,response);
                break;
            case "/updateuser":
                updateuser(request,response);
                break;
            case "/update":
                updatepage(request,response);
                break;

            case "/registerpage":
                registerpage(request,response);
                break;
            case "/delete":
                deleteuser(request,response);
                break;
            case "/logout":
                logoutuser(request,response);
                break;
            case "/report":
                reportpage(request,response);
                break;
            case "/generate":
                generatereport(request,response);
                break;
            case "/clients":
                listuser(request,response);
                break;
            case "/dashboard":
                showdashboard(request,response);
                break;
//            default :
//                index(request,response);
            }
            }catch(SQLException e){
            throw new ServletException(e);    
            }
    } 

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
//        passing the request in get parameter

    }



    // the below functions are responsible for corresponding tasks

    // this method renders the base.jsp or register page 

    private void loginpage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }


    private void registerpage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("base.jsp");
        dispatcher.forward(request, response);
    }

    // this register method pulls the data from the request and transfers it further to database 

     private void registeruser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ServletException {
        // getting the data from the form    
        String username = request.getParameter("uname");
        String password = request.getParameter("upassword");

        // hashing the password using MD5 
        password = new hashing().hash(password);

        String email = request.getParameter("uemail");
        String firstname = request.getParameter("ufirstname");
        String lastname = request.getParameter("ulastname");
        String usertype = request.getParameter("uusertype");
        String ustatus = "Active";


        // after getting the data, model class is loaded with  respective values
        User usr = new User(username,password,firstname,lastname,email,ustatus,usertype);
        UserDAO uD = new UserDAO();
        int data = uD.checkuser(username,email); // checks user if exists
        if (data==5)
        {
        response.getWriter().print("username exists");
        }else if(data==10)
        {
        response.getWriter().print("email exists");
        }
        else if(data==0){
        if(uD.insert(usr)){
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
        }else{
        response.getWriter().print("Database should be setup before the task");
        }
//        
        }

    }
    
    // method to login the user

    private void loginuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ServletException {
        String emailaddress = request.getParameter("uemail");
        String password = request.getParameter("upassword");
        password = new hashing().hash(password);

        // Getting the data from the database
        // The user should be able to have single account as per email address
        HttpSession session;
        UserDAO uD = new UserDAO();
        User user = uD.getUser(emailaddress);
       
        if (user==null){
            response.getWriter().print("no user ");
        }else{
        // this method records the attempts that the user is used to login 

        if (user.getPassword().equals(password))
        {
            if(uD.isAdmin(user.getUserType())){
            // HttpSession is used to create session to enable login and logout feature
            
             session = request.getSession();
             session.setAttribute("email", user.getEmailAddress());
             session.setAttribute("name", user.getUsername());
             session.setAttribute("type",user.getUserType());
             new AttemptDAO().clearAttempts(user.getEmailAddress());
             // calling the method to clear the attempts count

             // REcording the activity in database
             String str = uD.RecordInDb(user.getEmailAddress(),"logged into the system",user.getUsername());
             showdashboard(request,response);
             
            }else if(uD.isBlocked(user.getUserStatus())){
              response.getWriter().print("u are blocked by the admin");
            }else{
                session = request.getSession();
                session.setAttribute("email", user.getEmailAddress());
                session.setAttribute("name", user.getUsername());
                session.setAttribute("type",user.getUserType());
                

                request = calculatedata(request,response);
                RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            // calling the method to record the attempts in login page

            new AttemptDAO().recordAttempts(user.getEmailAddress());
            response.getWriter().print("you have entered wrong password");
        //        response.sendRedirect("login.jsp");
        }
        }
        


    }

    private void logoutuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ServletException {
         HttpSession session = request.getSession();
        
         UserDAO uD = new UserDAO();
         String str = uD.RecordInDb((String)session.getAttribute("email"),"logged out from the system",(String)session.getAttribute("name"));
         session.invalidate();
//         invalidates the session and unbinds any objecs associated with it
         index(request,response);
         
        }



        // method to block user
    private void blockuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ServletException {
       String email = request.getParameter("uemail").toString();
       UserDAO userd = new UserDAO();
       userd.blockUser(email);
       listuser(request,response);
    }



    // view the history for the user

     private void viewhistory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ServletException {

        String email = request.getParameter("uemail").toString();
        HistoryDAO historyd = new HistoryDAO();
        List<History> list = historyd.listhistory(email);
        request.setAttribute("historylist",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("history.jsp");
        dispatcher.forward(request, response);
    }


    
     private void listuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ServletException {

        List<User> userList=new ArrayList<User>();
        if(request.getSession().getAttribute("type").equals("Admin")){
            int page = Integer.parseInt(request.getParameter("p"));
            int size = 5;

            
            userList = new UserDAO().listallUser();
            int pages = (userList.size()+(size-1))/size;
            // calling the paginator method
            userList = retrieverecord((page-1)*size,page*size,userList); 
            


            request.setAttribute("p", page);
            request.setAttribute("pagecount", pages);

        }else{
            userList.add(new UserDAO().retrieve((String)request.getSession().getAttribute("email")));
        }
        request.setAttribute("userlist",userList);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Clients.jsp");
        dispatcher.forward(request, response);

    }
    
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        HttpSession session=request.getSession(false);  
        if(session!=null){  
 
        UserDAO dao = new UserDAO();

            if(dao.isAdmin((String)session.getAttribute("type"))){
            List<User> userList = dao.listallUser();
            request.setAttribute("userlist",userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);

            }
        
        }  
        else{  
            request = calculatedata(request,response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        }  
        
       
    }


    private void updatepage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        User user = new UserDAO().getUser(request.getParameter("uemail").toString());
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
        }

    private void updateuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String username = request.getParameter("rname");
        String rpassword = request.getParameter("rpass").toString();
        String ppassword  = request.getParameter("ppass").toString();
        if (!(rpassword.equals(ppassword))){
        // hashing the password using MD5 
           rpassword = new hashing().hash(rpassword);
        }

        String email = request.getParameter("remail");
        String firstname = request.getParameter("rfirstname");
        String lastname = request.getParameter("rlastname");
        String usertype = request.getParameter("rusertype");
        String status = request.getParameter("rstatus");

        
        User usr = new User(username,rpassword,firstname,lastname,email,status,usertype);
       
        UserDAO dao = new UserDAO();
        if (dao.updateUser(usr)){
            listuser(request,response);
        }
    }



    private void deleteuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    
        String email = request.getParameter("uemail");
        UserDAO dao = new UserDAO();
        HistoryDAO hisdao = new HistoryDAO();
        hisdao.deleteHistory(email,false);
        dao.deleteUser(email);

        listuser(request,response);
    }




    // method generate report gets the data and then calls to method to get list of the data and forward the request to open the page 

    public void generatereport(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        String Date = request.getParameter("date");
        List<Report> reportlist = new HistoryDAO().generatereport(Date);
        request.setAttribute("rlist", reportlist);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
        dispatcher.forward(request, response);

    }

    private void reportpage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
        dispatcher.forward(request, response);
    }

    private void showdashboard(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        

        request = calculatedata(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    
     private HttpServletRequest calculatedata(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        UserDAO dao = new UserDAO();
        List<User> listu = dao.listallUser();
        int size = listu.size();
        int active = 0;
            for (int i = 0; i< size; i++)   
            {
                boolean u = listu.get(i).getUserStatus().equals("Active");
                if (u)
                {
                    active = active + 1;
                }
            }
        request.setAttribute("active", active);
        request.setAttribute("count", size);
        request.setAttribute("block", size-active);
        return request;
    }



    // Creating the paginator class
    public List<User> retrieverecord(int start, int end, List<User> list)  
    {
        List<User> newlist = new ArrayList<User>();

        // iterating over the certain data
        if(end >list.size()){
            end = list.size();
        }
        for (int i = start; i<end; i++){
        
        newlist.add(list.get(i));
        }
     
        return newlist;
    }

}

