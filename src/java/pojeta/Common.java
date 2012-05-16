/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author michael
 */
public class Common {

    // retourne le nom d'utilisateur utilisé pour l'athentification
    // au web-service (se trouve dans web.xml)
    public static String getWSUsername() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getInitParameter("wsusername");
    }

    // retourne le mot de passe utilisé pour l'athentification
    // au web-service (se trouve dans web.xml)
    public static String getWSPassword() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getInitParameter("wspassword");
    }

    // convertit une date en String. Format dd-MM-yyyy.
    public static String dateToString(Date date) {
        
        // valeur de retour par défaut.
        String retDate = "-";
        
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            retDate = formatter.format(date);
        } catch (Exception e) { }

        return retDate;
    }
}
