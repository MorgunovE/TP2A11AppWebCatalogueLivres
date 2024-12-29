/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Evgenii Morgunov
 */
import javax.servlet.http.HttpServletRequest;

public class LocaleUtil {
    public static void setLocaleAttributes(HttpServletRequest request) {
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
}