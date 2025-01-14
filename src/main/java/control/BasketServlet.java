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
 * Servlet for basket
 * @author Evgenii Morgunov
 */
public class BasketServlet extends HttpServlet {
    private BasketService basketService;
    private LivreService livreService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        basketService = (BasketService) getServletContext().getAttribute("basketService");
        livreService = (LivreService) getServletContext().getAttribute("livreService");
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
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));

        String destination = null;

        // Get the user id and basket id from the session
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        Long basketId = (Long) session.getAttribute("basketId");
        Long bookId = Long.parseLong(request.getParameter("bookId"));
        Livre livre = livreService.findLivreById(bookId);

        if (userId == null) {
            destination = "/LoginRequiredServlet";
        } else if (basketId == null ) {
            List<Basket> baskets = basketService.findBasketsByUserId(userId);
            if (baskets == null || baskets.isEmpty()) {
                Basket newBasket = new Basket(userService.findUserById(userId), null);
                basketService.createBasket(newBasket);
                basketId = newBasket.getId();
                session.setAttribute("basketId", basketId);
                Basket basket = basketService.findBasketById(basketId);
                session.setAttribute("livres", basket.getLivres());
                destination = updateBasketAndSetDestination(session, basketId, livre);
            } else {
                basketId = baskets.get(0).getId();
                session.setAttribute("basketId", basketId);
                session.setAttribute("baskets", baskets);
                destination = updateBasketAndSetDestination(session, basketId, livre);
            }
        } else if (livre.getQuantity() == 0) {
            destination = "/BookOutOfStockServlet";
        } else{
            destination = updateBasketAndSetDestination(session, basketId, livre);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);

    }

    /**
     * Updates the basket and sets the destination
     * @param session the session
     * @param basketId the basket id
     * @param livre the book
     * @return the destination
     */
    private String updateBasketAndSetDestination(HttpSession session, Long basketId, Livre livre) {
        Basket basket = basketService.findBasketById(basketId);
        List<Livre> livres = basket.getLivres();
        String destination = null;

        if (livres.stream().anyMatch(l -> l.getId().equals(livre.getId()))) {
            session.setAttribute("livres", basket.getLivres());
            destination = "/BookAlreadyInBasketServlet";
        } else {
            livres.add(livre);
            basket.setLivres(livres);
            basketService.updateBasket(basket);

            Basket basketAfterUpdate = basketService.findBasketById(basketId);
            List<Livre> updatedLivres = basketAfterUpdate.getLivres();

            if (updatedLivres.stream().noneMatch(l -> l.getId().equals(livre.getId()))) {
                destination = "/BasketErrorServlet";
            } else {
                destination = "/BasketSuccessServlet";
            }
        }
        return destination;
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
