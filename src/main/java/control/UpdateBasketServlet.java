/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.Basket;
import model.Livre;
import service.BasketService;
import service.LivreService;

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
public class UpdateBasketServlet extends HttpServlet {
    private BasketService basketService;
    private LivreService livreService;

    @Override
    public void init() throws ServletException {
        basketService = (BasketService) getServletContext().getAttribute("basketService");
        livreService = (LivreService) getServletContext().getAttribute("livreService");
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
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        Long basketId = Long.parseLong(request.getParameter("basketId"));
        Long bookId = Long.parseLong(request.getParameter("bookId"));
        String destination;

        if (userId == null) {
            destination = "/LoginRequiredServlet";
        } else if( basketId == 0) {
            destination = "/BasketErrorServlet";
        } else if (bookId == 0) {
            destination = "/BasketErrorServlet";
        } else {
            Basket basket = basketService.findBasketById(basketId);
            Livre livre = livreService.findLivreById(bookId);
            List<Livre> livres = basket.getLivres();

            if (livre != null && livres.stream().anyMatch(l -> l.getId().equals(livre.getId()))) {
                livres.stream().filter(l -> l.getId().equals(livre.getId())).findFirst().ifPresent(livres::remove);
                basket.setLivres(livres);
                basketService.updateBasket(basket);

                Basket basketAfterUpdate = basketService.findBasketById(basketId);
                List<Livre> updatedLivres = basketAfterUpdate.getLivres();

                if (updatedLivres.stream().anyMatch(l -> l.getId().equals(livre.getId()))) {
                    destination = "/UpdateBasketErrorServlet";
                } else {
                    session.setAttribute("livres", basketAfterUpdate.getLivres());
                    destination = "/UpdateBasketSuccessServlet";
                }
            } else {
                destination = "/BasketErrorServlet";
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
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
