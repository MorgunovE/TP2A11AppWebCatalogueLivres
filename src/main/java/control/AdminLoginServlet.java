/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author user
 */
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

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
            out.println("<title>Servlet adminLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminLogin at " + request.getContextPath() + "</h1>");
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
    
    protected boolean checkPassword(String rawPassword, String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogdb", "root", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT password_hash FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("password_hash");
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                return encoder.matches(rawPassword, storedHash);
            } else {
                return false; 
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // check password using method checkPassword
        if (checkPassword(password, username)) {
           
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // redirect to admin page
            response.sendRedirect("adminPage.jsp");
        } else {
            // wrong password
            request.setAttribute("errorMessage", "Wrong password");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
   
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward to the login page (adminLogin.jsp) on initial GET request
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);

    }

    private String getAdminPasswordFromConfig() {
        // Implement logic to retrieve the admin password from a secure location (e.g., configuration file)
        // This should not be hardcoded directly in the Servlet
        return "your_secure_admin_password"; // Replace with actual retrieval logic
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
