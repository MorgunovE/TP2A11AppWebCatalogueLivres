/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.Basket;
import model.User;
import service.BasketService;
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
public class AccountServlet extends HttpServlet {
    private UserService userService = new UserService();
    private BasketService basketService = new BasketService();

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
        setLocaleAttributes(request);

        RequestDispatcher rd = request
                .getRequestDispatcher("/jsp/account.jsp");
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

        request.getRequestDispatcher("/jsp/account.jsp")
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

        String destination = null;

        if ("create".equals(action)) {
            String name = request.getParameter("name");
            String familyName = request.getParameter("familyName");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");

            User user = new User();
            user.setName(name);
            user.setFamilyName(familyName);
            user.setTel(tel);
            user.setEmail(email);

            List<User> existingUsers = userService.findUsersByEmail(email);
            if (existingUsers != null && !existingUsers.isEmpty()) {
                destination = "/AccountErrorCreationServlet";
            } else {

                Basket basket = new Basket(user, null);

                try {
                    userService.createUser(user);
                    basketService.createBasket(basket);
                    HttpSession session = request.getSession();
                    session.setAttribute("name", name);
                    session.setAttribute("familyName", familyName);
                    session.setAttribute("email", email);
                    session.setAttribute("tel", tel);
                    session.setAttribute("id", user.getId());
                    session.setAttribute("basketId", basket.getId());
                    destination = "/AccountPortalServlet";
                } catch (Exception e) {
                    destination = "/AccountErrorCreationServlet";
                }
            }
        }


        if("findUsersByEmail".equals(action)) {
            String email = request.getParameter("email_user");


            List<User> users = userService.findUsersByEmail(email);

            try {
                if (users.isEmpty()) {
                    destination = "/AccountErrorServlet";
                } else {
                    User user = users.get(0);
                    HttpSession session = request.getSession();
                    session.setAttribute("name", user.getName());
                    session.setAttribute("familyName", user.getFamilyName());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("tel", user.getTel());
                    session.setAttribute("id", user.getId());

                    List<Basket> baskets = basketService.findBasketsByUserId(user.getId());
                    if (baskets != null) {
                        session.setAttribute("baskets", baskets);
                    }
                    destination = "/AccountPortalServlet";
                }
            } catch (Exception e) {
                e.printStackTrace();
                destination = "/AccountErrorServlet";
            }
        }

        setLocaleAttributes(request);

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