/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import model.User;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateAccountServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
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
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));
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
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        String destination;

        if (userId == null) {
            destination = "/LoginRequiredServlet";
        } else {
            User user = userService.findUserById(userId);
            if (user == null) {
                destination = "/AccountErrorServlet";
            } else {
                request.setAttribute("user", user);
                destination = "WEB-INF/updateAccount.jsp";
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
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        String destination;

        if (userId == null) {
            destination = "/LoginRequiredServlet";
        } else {
            User user = userService.findUserById(userId);
            if (user == null) {
                destination = "/AccountErrorServlet";
            } else {
                String email = request.getParameter("email");
                if (!user.getEmail().equals(email)) {
                    if (!userService.findUsersByEmail(email).isEmpty()) {
                        destination = "/AccountUpdateErrorServlet";
                    } else {
                        updateUser(request, user, session);
                        userService.updateUser(user);
                        destination = "/AccountUpdateSuccessServlet";
                    }
                } else {
                    updateUser(request, user, session);
                    userService.updateUser(user);
                    destination = "/AccountUpdateSuccessServlet";
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, User user, HttpSession session) {
        user.setName(request.getParameter("name"));
        user.setFamilyName(request.getParameter("familyName"));
        user.setTel(request.getParameter("tel"));
        user.setEmail(request.getParameter("email"));
        
        session.setAttribute("name", user.getName());
        session.setAttribute("familyName", user.getFamilyName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("tel", user.getTel());
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
