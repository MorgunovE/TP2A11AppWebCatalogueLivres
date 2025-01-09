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
public class DeleteBookServlet extends HttpServlet {
    private LivreService livreService;
    private BasketService basketService;

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

        String destination;
        String bookIdParam = request.getParameter("id");

        if (bookIdParam == null || bookIdParam.isEmpty()) {
            destination = "/ErrorBookServlet";
        } else {
            Long bookId = Long.parseLong(bookIdParam);
            Livre livre = livreService.findLivreById(bookId);

            if (livre == null ) {
                destination = "/ErrorBookServlet";
            } else {

                List<Basket> baskets = basketService.findAllBaskets();
                for (Basket basket : baskets) {
                    List<Livre> livres = basket.getLivres();
                    livres.stream().filter(l -> l.getId().equals(bookId)).findFirst().ifPresent(livres::remove);
                    basket.setLivres(livres);
                    basketService.updateBasket(basket);
                }

                livreService.deleteLivre(livre);
                Livre deletedLivre = livreService.findLivreById(bookId);

                if (deletedLivre != null) {
                    destination = "/DeleteBookErrorServlet";
                } else {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    destination = "/DeleteBookSuccessServlet";
                }
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
