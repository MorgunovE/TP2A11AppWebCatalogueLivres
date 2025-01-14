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
 * Servlet for catalog
 * @author Evgenii Morgunov
 */
public class CatalogServlet extends HttpServlet {
    // Services
    private LivreService livreService;

    @Override
    public void init() throws ServletException {
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
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Set user and locale attributes
     * @param request servlet request
     */
    private void setUserAndLocaleAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        request.setAttribute("userName", userName);
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));
    }

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

        setUserAndLocaleAttributes(request);

        if (request.getAttribute("livres") == null) {
            List<Livre> livres = livreService.findAllLivres();
            request.setAttribute("livres", livres);
        }

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

        // Filter
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

        setUserAndLocaleAttributes(request);
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
