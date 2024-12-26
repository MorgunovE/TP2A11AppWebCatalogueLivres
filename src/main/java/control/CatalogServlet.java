/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.Livre;
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

/**
 * Servlet pour afficher le catalogue des livres.
 * Cette servlet utilise un service pour accéder aux données des livres.
 * Elle affiche le catalogue des livres et permet de filtrer les livres par titre, auteur, genre ou prix.
 * Elle affiche également le nom de l'utilisateur connecté.
 * Elle affiche les informations de localisation de l'utilisateur.
 * Elle utilise un fichier JSP pour afficher les données.
 * Elle utilise un fichier de propriétés pour les messages d'erreur.
 * Elle utilise un fichier de propriétés pour les messages de succès.
 * Elle utilise un fichier de propriétés pour les messages d'information.
 * Elle utilise un fichier de propriétés pour les messages de confirmation.
 * Elle utilise un fichier de propriétés pour les messages d'avertissement.
 */
public class CatalogServlet extends HttpServlet {

    /**
     * Le service pour accéder aux données des livres.
     */
    private LivreService livreService = new LivreService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /**
         * Locale de l'utilisateur.
         */
        Locale locale = request.getLocale();

        /**
         * Langue de l'utilisateur.
         */
        request.setAttribute("Language", locale.getLanguage());

        /**
         * Pays de l'utilisateur.
         */
        request.setAttribute("Country", locale.getCountry());

        /**
         * Code ISO du pays de l'utilisateur.
         */
        request.setAttribute("isoCountry", locale.getISO3Country());

        RequestDispatcher rd = request
                .getRequestDispatcher("/jsp/catalog.jsp");
        rd.forward(request, response);
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
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        /**
         * Session de l'utilisateur.
         */
        HttpSession session = request.getSession();
        String userName = (String) session
                .getAttribute("userName");

        if (request.getAttribute("livres") == null) {
            List<Livre> livres = livreService.findAllLivres();
            request.setAttribute("livres", livres);
        }
        request.setAttribute("userName", userName);

        request.getRequestDispatcher("/jsp/catalog.jsp")
                .forward(request, response);
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
        String action = request.getParameter("action");
        if ("filter".equals(action)) {
            String filterType = request.getParameter("filterType");
            String filterValue = request.getParameter("filterValue");

            List<Livre> livres = null;
            switch (filterType) {
                case "title":
                    livres = livreService.findLivresByTitle(filterValue);
                    break;
                case "author":
                    livres = livreService.findLivresByAuthor(filterValue);
                    break;
                case "genre":
                    livres = livreService.findLivresByGenre(filterValue);
                    break;
                case "price":
                    try {
                        double price = Double.parseDouble(filterValue);
                        livres = livreService.findLivresByPriceLessThanOrEqual(price);
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Invalid price format");
                    }
                    break;
            }

            if (livres != null) {
                request.setAttribute("livres", livres);
            }
        }

        doGet(request, response);
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
