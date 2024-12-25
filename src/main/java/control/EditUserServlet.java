/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import service.UserService;

/**
 *
 * @author user
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        long id = Long.parseLong(request.getParameter("id"));
    
        // get user attributs by ID 
        UserService userService = new UserService(); 
        User user = userService.findUserById(id); 

        // send user data to edit page         
        request.setAttribute("user", user); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editUser.jsp"); 
        dispatcher.forward(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
          long id = Long.parseLong(request.getParameter("id")); 
          String name = request.getParameter("name"); 
          String familyName = request.getParameter("familyName"); 
          String tel = request.getParameter("tel"); 
          String email = request.getParameter("email"); 
          
          UserService userService = new UserService(); 
          User user = userService.findUserById(id); 
          
          user.setName(name); 
          user.setFamilyName(familyName); 
          user.setTel(tel); 
          user.setEmail(email); 
          userService.updateUser(user);  
          
          // Update in DB
          userService.updateUser(user); 

          // redirect to users page
          response.sendRedirect("users");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
