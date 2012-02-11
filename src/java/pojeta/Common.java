/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojeta;

import javax.faces.context.FacesContext;

/**
 *
 * @author michael
 */
public class Common {
 
    // retourne le nom d'utilisateur utilisé pour l'athentification
    // au web-service (se trouve dans web.xml)
    public static String getWSUsername(){
        FacesContext fc = FacesContext.getCurrentInstance();
	return fc.getExternalContext().getInitParameter("wsusername");
    }
    
    // retourne le mot de passe utilisé pour l'athentification
    // au web-service (se trouve dans web.xml)
    public static String getWSPassword(){
        FacesContext fc = FacesContext.getCurrentInstance();
	return fc.getExternalContext().getInitParameter("wspassword");
    }
}
