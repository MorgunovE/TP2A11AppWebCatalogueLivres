/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.User;
import service.UserService;

import java.io.IOException;
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
 * @author Rustam Zholdubayev
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
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));
        request.getRequestDispatcher("WEB-INF/deleteAccountConfirmation.jsp").forward(request, response);

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
    //
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user id
        String locale = LocaleUtil.setLocaleAttributes(request);
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
                request.getRequestDispatcher("DeleteAccountErrorServlet").forward(request, response);
            } else {
                // Find the user's baskets
                List<Basket> baskets = basketService.findBasketsByUserId(userId);
                // Delete all baskets associated with the user
                for (Basket basket : baskets) {
                    basketService.deleteBasket(basket);
                }

                // Delete the user
                userService.deleteUser(user);
                User deletedUser = userService.findUserById(userId);
                if(deletedUser != null) {
                    request.getRequestDispatcher("DeleteAccountErrorServlet").forward(request, response);
                } else {
                    session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    request.getRequestDispatcher("DeleteAccountSuccessServlet").forward(request, response);

                }

            }

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
