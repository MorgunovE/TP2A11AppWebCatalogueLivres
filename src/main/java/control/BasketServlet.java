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
        /**
         * Locale de l'utilisateur.
         */
        Locale locale = request.getLocale();
        request.setAttribute("locale", locale);

        /**
         * Langue de l'utilisateur.
         */
        request.setAttribute("Language", locale.getLanguage());
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
        LocaleUtil.setLocaleAttributes(request);

        String destination = null;

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
                baskets = basketService.findBasketsByUserId(userId);
                session.setAttribute("baskets", baskets);
                updateBasketAndSetDestination(session, basketId, livre);
            } else {
                basketId = baskets.get(0).getId();
                session.setAttribute("basketId", basketId);
                session.setAttribute("baskets", baskets);
                updateBasketAndSetDestination(session, basketId, livre);
            }
        } else if (livre.getQuantity() == 0) {
            destination = "/BookOutOfStockServlet";
        } else{
            updateBasketAndSetDestination(session, basketId, livre);
        }

        if (destination == null) {
            destination = (String) session.getAttribute("destination");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);

    }

    private void updateBasketAndSetDestination(HttpSession session, Long basketId, Livre livre) {
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
                System.out.println("basket found in BasketServlet");
                System.out.println("basketId from session: " + basketId);
                System.out.println("basket: " + basketAfterUpdate);
                System.out.println("basket id: " + basketAfterUpdate.getId());
                System.out.println("basket livres: " + basketAfterUpdate.getLivres());
                session.setAttribute("livres", updatedLivres);
                destination = "/BasketSuccessServlet";
            }
        }
        session.setAttribute("destination", destination);
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
