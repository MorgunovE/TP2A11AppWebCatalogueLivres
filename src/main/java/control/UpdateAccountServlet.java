/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.BasketService;
import service.LivreService;
import service.UserService;

/**
 *
 * @author user
 */
public class UpdateAccountServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }
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
        // 
        HttpSession session = request.getSession();
        
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            //send to login required servlet
            response.sendRedirect("AccountServlet");
        } else {
            // Find the user
            User user = userService.findUserById(userId);
            if (user == null) {
            // User not found            
            request.getRequestDispatcher("WEB-INF/updateAccountError.jsp").forward(request, response);
            } else {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("WEB-INF/updateAccount.jsp").forward(request, response);
                }
        }
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
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        String name = request.getParameter("name");
        String familyName = request.getParameter("familyName");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");

       
        User user = userService.findUserById(userId);

        user.setName(name);
        user.setFamilyName(familyName);
        user.setTel(tel);
        user.setEmail(email);
        userService.updateUser(user);

        // Update in DB
        userService.updateUser(user);

        //
        session.setAttribute("user", user);

        String destination = request.getContextPath() + "/account.jsp";
        LocaleUtil.setLocaleAttributes(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
