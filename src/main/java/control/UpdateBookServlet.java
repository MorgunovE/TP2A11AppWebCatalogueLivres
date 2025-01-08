/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.Livre;
import service.LivreService;

import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateBookServlet extends HttpServlet {
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

        String bookIdParam = request.getParameter("id");
        String destination;

        if (bookIdParam == null || bookIdParam.isEmpty()) {
            destination = "/ErrorBookServlet";
        } else {
            Long bookId = Long.parseLong(bookIdParam);
            Livre livre = livreService.findLivreById(bookId);

            if (livre == null) {
                destination = "/ErrorBookServlet";
            } else {
                request.setAttribute("livre", livre);
                destination = "WEB-INF/updateBook.jsp";
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
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));
        String bookIdParam = request.getParameter("id");
        String destination;

        if (bookIdParam == null || bookIdParam.isEmpty()) {
            destination = "/ErrorBookServlet";
        } else {
            Long bookId = Long.parseLong(bookIdParam);
            Livre livre = livreService.findLivreById(bookId);

            if (livre == null) {
                destination = "/ErrorBookServlet";
            } else {
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String genre = request.getParameter("genre");
                String description = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String image = request.getParameter("image");

                livre.setTitle(title);
                livre.setAuthor(author);
                livre.setGenre(genre);
                livre.setDescription(description);
                livre.setPrice(price);
                livre.setQuantity(quantity);
                livre.setImage(image);

                livreService.updateLivre(livre);

                Livre updatedLivre = livreService.findLivreById(bookId);

                if (updatedLivre == null) {
                    destination = "/UpdateBookErrorServlet";
                } else {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    destination = "/UpdateBookSuccessServlet";
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
