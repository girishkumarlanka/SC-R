/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.JSONObject;

/**
 *
 * @author Praveen
 */
public class category extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Connection con;
        JSONObject js=new JSONObject();
        Statement stmt;
        ResultSet rs;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","mysql123");
        if(con==null)
          out.close();
        stmt = con.createStatement();       
        rs=stmt.executeQuery("select distinct Topic from mysql.techobjectiveenglish");
        int n=0;
        while(rs.next())
        {
         js.accumulate("category",rs.getString("Topic"));   
         n++;   
          }   
         js.put("catno",n);
         rs=stmt.executeQuery("select lang from mysql.language");
         n=0;
         while(rs.next())
         {
          js.accumulate("lang",rs.getString("lang"));          
          n++;
           }   
         js.put("langno",n);
         out.println(js.toString());
        } 
        catch(Exception e)
        {
            out.println(e);
           }   finally { 
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
