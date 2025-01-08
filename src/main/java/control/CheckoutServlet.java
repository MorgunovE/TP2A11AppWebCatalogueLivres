/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.Basket;
import model.Livre;
import service.BasketService;
import service.LivreService;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Evgenii Morgunov
 */
public class CheckoutServlet extends HttpServlet {
    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        basketService = (BasketService) getServletContext().getAttribute("basketService");
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
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));


        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        Long basketId = (Long) session.getAttribute("basketId");

        if (userId == null) {
            response.sendRedirect("LoginRequiredServlet");
            return;
        }

        if (basketId == null) {
            List<Basket> baskets = basketService.findBasketsByUserId(userId);
            Basket basket = baskets.get(0);
            basketId = basket.getId();
            session.setAttribute("basketId", basketId);
            List<Livre> livres = basket.getLivres();
            if (livres == null || livres.isEmpty()) {
                response.sendRedirect("BasketEmptyServlet");
                return;
            } else {
                session.setAttribute("basketId", basketId);
            }
        }

        Basket basket = basketService.findBasketById(basketId);
        List<Livre> livres = basket.getLivres();
        session.setAttribute("livres", livres);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/checkout.jsp");
        dispatcher.forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
