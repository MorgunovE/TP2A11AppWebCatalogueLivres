/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;
 
import model.Basket;
import model.Livre;
import service.BasketService;
import service.LivreService;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
 
public class UpdateBasketServlet extends HttpServlet {
    private BasketService basketService;
    private LivreService livreService;
 
    @Override
    public void init() throws ServletException {
        basketService = (BasketService) getServletContext().getAttribute("basketService");
        livreService = (LivreService) getServletContext().getAttribute("livreService");
    }
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long basketId = Long.parseLong(request.getParameter("basketId"));
        Long bookId = Long.parseLong(request.getParameter("bookId"));
        String locale = request.getParameter("locale");
 
        Basket basket = basketService.findBasketById(basketId);
        Livre livre = livreService.findLivreById(bookId);
 
        if (basket != null && livre != null) {
            List<Livre> livres = basket.getLivres();
            if (livres.contains(livre)) {
                livres.remove(livre);
                basket.setLivres(livres);
                basketService.updateBasket(basket);
            }
        }
 
        response.sendRedirect("AccountServlet?locale=" + locale);
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    public String getServletInfo() {
        return "Remove Book From Basket Servlet";
    }
}
