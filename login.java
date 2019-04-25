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
import listener.onlinelistener;


/**
 *
 * @author Praveen
 */
public class login extends HttpServlet {
   
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
        onlinelistener on=new onlinelistener();
        String uname=request.getParameter("uname").toLowerCase().toString();
        String pass=request.getParameter("pass").toLowerCase();       
        // out.println(uname+"  "+pass);
       
        try {
            /* TODO output your page here. You may use following sample code. */
            int n=3;
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","mysql123");
          if(con==null)
             out.close();
           st = con.createStatement();
           int val=uname.compareTo("admin");
           if(val==0)               
            { 
             rs=st.executeQuery("select count(*) as tot from register where pf='"+uname+"' and password='"+pass+"'"); 
             rs.next();
             if(rs.getInt("tot")==1) {               
                n=1;
                    HttpSession session = request.getSession(true);       
                    on.setattrib(uname);
             }
            }
           else
           { 
             rs=st.executeQuery("select count(*) as tot from register where pf='"+uname+"' and password='"+pass+"'"); 
             rs.next();
             if(rs.getInt("tot")==1)
             { 
             n=2;    
                   HttpSession session = request.getSession(true);                       
                   on.setattrib(uname);                    
              }
            }  
           
          out.println(n);  
           
        } 
        catch(SQLException e)
        {
            
         out.println(e);  
          }
        catch(Exception e)
        {
            
         out.println(e);  
          }finally { 
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
