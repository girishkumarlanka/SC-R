/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.sql.*;
import org.apache.catalina.Session;

/**
 *
 * @author Praveen
 */
public class changepassword extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Statement st;
        Connection con;
        ResultSet rs;
        String uname=request.getParameter("pfno").toString();        
        String passold=request.getParameter("old").toString();
        String passnew=request.getParameter("new").toString();
        try {
            // TODO output your page here. You may use following sample code. 
            int n=0;
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","mysql123");
          if(con==null)
             out.close();
           st = con.createStatement();
           rs=st.executeQuery("select count(*) as tot from mysql.register where pf='"+uname+"' and password='"+passold+"'");
           rs.next();
           int tot=rs.getInt("tot");
           if(tot==1)
           {
            st.executeUpdate("update mysql.register set password='"+passnew+"' where pf='"+uname+"'");
            n=1;
            }  
           else
              n=0; 
           //out.println("select count(*) as tot from mysql.register where pf='"+uname+"' and password='"+passold+"'");  
           out.println(n);
           
        } 
        catch(Exception e)
        {
          out.println(e);  
          } finally { 
            out.close();
          }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
