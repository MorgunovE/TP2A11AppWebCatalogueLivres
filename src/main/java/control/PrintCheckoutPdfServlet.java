/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Livre;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Evgenii Morgunov
 */
public class PrintCheckoutPdfServlet extends HttpServlet {

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
        request.setAttribute("locale", LocaleUtil.setLocaleAttributes(request));
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=checkout.pdf");
        OutputStream out = response.getOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            HttpSession session = request.getSession();
            List<Livre> livres = (List<Livre>) session.getAttribute("livres");
            LocaleUtil.setLocaleAttributes(request);

            if (livres == null || livres.isEmpty()) {
                if (request.getAttribute("locale").equals("fr_FR")) {
                    document.add(new Paragraph("Votre panier est vide"));
                } else {
                    document.add(new Paragraph("Your basket is empty"));
                }

            } else {

                if (request.getAttribute("locale").equals("fr_FR")) {
                    Paragraph titre = new Paragraph("Facture", FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLDITALIC, BaseColor.BLUE));
                    titre.setAlignment(Element.ALIGN_CENTER);
                    titre.setSpacingAfter(30f);
                    document.add(titre);

                    PdfPTable table = new PdfPTable(4);
                    table.addCell(new Paragraph("Titre", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                    table.addCell(new Paragraph("Auteur", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                    table.addCell(new Paragraph("Genre", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                    table.addCell(new Paragraph("Prix", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));

                    float total = 0;
                    for (Livre livre : livres) {
                        table.addCell(livre.getTitle());
                        table.addCell(livre.getAuthor());
                        table.addCell(livre.getGenre());
                        table.addCell(String.format("%.2f $", livre.getPrice()));
                        total += (float) livre.getPrice();
                    }

                    document.add(table);

                    Paragraph totalPrice = new Paragraph("Total: " + String.format("%.2f $", total), FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK));
                    totalPrice.setAlignment(Element.ALIGN_RIGHT);
                    totalPrice.setSpacingBefore(20f);
                    document.add(totalPrice);

                    Paragraph totalBooks = new Paragraph("Total Livres: " + livres.size(), FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK));
                    totalBooks.setAlignment(Element.ALIGN_RIGHT);
                    totalBooks.setSpacingBefore(10f);
                    document.add(totalBooks);

                } else {

                    Paragraph titre = new Paragraph("Invoice", FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLDITALIC, BaseColor.BLUE));
                    titre.setAlignment(Element.ALIGN_CENTER);
                    titre.setSpacingAfter(30f);
                    document.add(titre);

                    PdfPTable table = new PdfPTable(4);
                    table.addCell(new Paragraph("Title", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                    table.addCell(new Paragraph("Author", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                    table.addCell(new Paragraph("Genre", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                    table.addCell(new Paragraph("Price", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));

                    float total = 0;
                    for (Livre livre : livres) {
                        table.addCell(livre.getTitle());
                        table.addCell(livre.getAuthor());
                        table.addCell(livre.getGenre());
                        table.addCell(String.format("%.2f $", livre.getPrice()));
                        total += (float) livre.getPrice();
                    }
                    document.add(table);

                    Paragraph totalPrice = new Paragraph("Total: " + String.format("%.2f $", total), FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK));
                    totalPrice.setAlignment(Element.ALIGN_RIGHT);
                    totalPrice.setSpacingBefore(20f);
                    document.add(totalPrice);

                    Paragraph totalBooks = new Paragraph("Total Books: " + livres.size(), FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK));
                    totalBooks.setAlignment(Element.ALIGN_RIGHT);
                    totalBooks.setSpacingBefore(10f);
                    document.add(totalBooks);
                }
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
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
        processRequest(request, response);
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
