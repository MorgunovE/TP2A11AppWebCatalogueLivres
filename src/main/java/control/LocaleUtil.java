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
import java.util.Locale;

public class LocaleUtil {
    public static String setLocaleAttributes(HttpServletRequest request) {
        Locale loc = request.getLocale();
        String locale = request.getParameter("locale");

        if(locale == null || locale.isEmpty()) {
            if ("fr_FR".equals(loc.toString()) ) {
                locale = "fr_FR";
            } else if ("en_US".equals(loc.toString()) ) {
                locale = "en_US";
            }  else {
                locale = "en_US";
            }
        } else if ("fr_FR".equals(locale)) {
                locale = "fr_FR";
        } else {
                locale = "en_US";
        }

        return locale;
    }
}