/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author michael
 */
@ManagedBean
@RequestScoped
public class AuthBackingBean {

    // nom d'utilisateur.
    private String username; 
    // mot de passe.
    private String password;
    
    // Constructeur.
    public AuthBackingBean() {
    }
    
    private static final Logger log = Logger.getLogger(AuthBackingBean.class.getName());

    // Déconnecter l'utilisateur.
    public String logout() {
        String result = "/index.xhtml?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            result = "/loginError?faces-redirect=true";
        }

        return result;
    }
    
    // Propriété : retourne true si l'utilisateur a une session ouverte.
    //             retourne false si l'utilisateur n'est pas connecté.
    public Boolean getLoggedIn() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        if (request.getUserPrincipal() == null)
            return false;
        else 
            return true;
    } 
    
    // getter pour username (nom d'utilisateur).
    public String getUsername(){
        return username;
    }
    
    // setter pour username (nom d'utilisateur).
    public void setUsername(String username) {
        this.username = username;
    }
    
    // getter pour password (mot de passe).
    public String getPassword(){
        return password;
    }
    
    // setter pour password (mot de passe).
    public void setPassword(String password) {
        this.password = password;
    }
    
    // ouvrir une session en utilisant le username spécifié.
    public String login() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.login(username, password);
            
//            if (request.isUserInRole("administrator") || request.isUserInRole("developer") || request.isUserInRole("user")) {
//                // login OK.
//                return "index.xhtml?faces-redirect=true";
//            }
//            
//            throw new Exception();
            
            return "index.xhtml?faces-redirect=true";
            
            
        } catch (Exception ex) {
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login incorrect", "Login incorrect");

            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            return "login.xhtml";
        }
    }
}
