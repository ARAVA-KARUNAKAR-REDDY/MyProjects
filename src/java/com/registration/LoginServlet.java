/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.registration;
import jakarta.servlet.RequestDispatcher;
//import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

   
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String uemail=request.getParameter("username");
      String upwd=request.getParameter("password");
       Connection con = null;
        PreparedStatement pst;

       HttpSession session =request.getSession();
       RequestDispatcher rd=null;
       
       if(uemail == null || uemail.equals("")){
            request.setAttribute("status","InvalidEmail");
                  rd=request.getRequestDispatcher("login.jsp");
                  rd.forward(request, response);
       }
        if(upwd == null || upwd.equals("")){
            request.setAttribute("status","Invalidpassword");
                  rd=request.getRequestDispatcher("login.jsp");
                  rd.forward(request, response);
       }
        
      try{
            Class.forName("com.mysql.jdbc.Driver");
           
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","rgukt123");
           pst = con.prepareStatement("select * from users where uemail=? and upwd=?");
            pst.setString(1,uemail);
            
              pst.setString(2,upwd);
             
               ResultSet rs=pst.executeQuery();
              if(rs.next()){
                  session.setAttribute("name",rs.getString("uname"));
                  rd=request.getRequestDispatcher("index.jsp");
              }
              else{
                                 request.setAttribute("status","failed");
                  rd=request.getRequestDispatcher("login.jsp");

              }
              rd.forward(request, response);
        }
        catch(ServletException | IOException | ClassNotFoundException | SQLException e){
        }
      finally{
          try {
              con.close();
          } catch (SQLException ex) {
              Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
        

    }

  

}
