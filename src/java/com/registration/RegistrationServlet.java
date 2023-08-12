/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.registration;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
    
    

       String uname=request.getParameter("name");

        String uemail=request.getParameter("email");

        String upwd=request.getParameter("pass");

        String umobile=request.getParameter("contact");
         Connection con = null;
        PreparedStatement pst;

        RequestDispatcher rd=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
           
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","rgukt123");
           pst = con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)");
            pst.setString(1,uname);
            
              pst.setString(2,upwd);
              pst.setString(3,uemail);
               pst.setString(4,umobile);
               int rowcount=pst.executeUpdate();
               rd=request.getRequestDispatcher("registration.jsp");
               if(rowcount>0){
                   request.setAttribute("status","success");
               }
               else{
                   request.setAttribute("status","failed");
               }
               rd.forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
           try {
               con.close();
           } catch (SQLException ex) {
               Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
//        PrintWriter out=response.getWriter();
//        out.println(uname);
//                out.println(uemail);
//
//                        out.println(upwd);
//
//                                out.println(umobile);

        

      
    }

   

   

}
