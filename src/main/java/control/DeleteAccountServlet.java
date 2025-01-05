/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.User;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Basket;
import service.BasketService;

/**
 *
 * @author user
 */
public class DeleteAccountServlet extends HttpServlet {
    private BasketService basketService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        basketService = (BasketService) getServletContext().getAttribute("basketService");
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
            // Get user id
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        if (locale != null) {
            session.setAttribute("locale", locale);
        }
        Long userId = (Long) session.getAttribute("id");

        if (userId == null) {
            //send to login required servlet
            response.sendRedirect("AccountServlet");
        } else {
            // Find the user
            User user = userService.findUserById(userId);
            if (user == null) {
            // User not found            
            request.getRequestDispatcher("WEB-INF/deleteAccountError.jsp").forward(request, response);
        } else {
                request.setAttribute("userId", user.getId());
                request.getRequestDispatcher("WEB-INF/deleteAccountConfirmation.jsp").forward(request, response);
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
    // Сервлет
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user id
        long userId = Long.parseLong(request.getParameter("id"));

        User user = userService.findUserById(userId);
          if (user == null) {
            // User not found
            request.setAttribute("errorMessage", "User not found");
            request.getRequestDispatcher("WEB-INF/deleteAccountError.jsp").forward(request, response);
        } else {
            // Find the user's baskets
            List<Basket> baskets = basketService.findBasketsByUserId(userId);
            // Delete all baskets associated with the user
            for (Basket basket : baskets) {
                basketService.deleteBasket(basket);
            }

            // Delete the user
            userService.deleteUser(user);

            // Send to success delete page
            request.getRequestDispatcher("WEB-INF/deleteAccountSuccess.jsp").forward(request, response);
        }
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
