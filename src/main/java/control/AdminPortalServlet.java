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

/**
 *
 * @author Evgenii Morgunov
 */
public class AdminPortalServlet extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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

        RequestDispatcher rd = request
                .getRequestDispatcher("WEB-INF/adminPortal.jsp");
        rd.forward(request, response);
    }

    private void setLocaleAttributes(HttpServletRequest request) {
        String locale = request.getParameter("locale");
        String language = request.getParameter("Language");
        if ("fr_FR".equals(locale)) {
            locale = "fr_FR";
        } else if ("en_US".equals(locale)) {
            locale = "en_US";
        } else if ("fr".equals(language)) {
            locale = "fr_FR";
        } else if (locale == null || locale.isEmpty()) {
            locale = "en_US";
        } else {
            locale = "en_US";
        }
        request.setAttribute("locale", locale);
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

        setLocaleAttributes(request);

        if (request.getAttribute("livres") == null) {
            List<Livre> livres = livreService.findAllLivres();
            request.setAttribute("livres", livres);
        }

        request.getRequestDispatcher("WEB-INF/adminPortal.jsp")
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

        if ("addBook".equals(action)) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String genre = request.getParameter("genre");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");

            Livre livre = new Livre();
            livre.setTitle(title);
            livre.setAuthor(author);
            livre.setGenre(genre);
            livre.setDescription(description);
            livre.setPrice(price);
            livre.setQuantity(quantity);
            livre.setImage(image);

            livreService.createLivre(livre);
        }

        setLocaleAttributes(request);
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
