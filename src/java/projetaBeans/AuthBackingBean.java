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

    /** Creates a new instance of AuthBackingBean */
    
    private String username;
    private String password;
    
    public AuthBackingBean() {
    }
    
    private static final Logger log = Logger.getLogger(AuthBackingBean.class.getName());

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
    
    public Boolean getLoggedIn() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        if (request.getUserPrincipal() == null)
            return false;
        else 
            return true;
    } 
    
   
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.login(username, password);
            
            return "regConfirmation.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login incorrect", "Login incorrect");

            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            return "login.xhtml";
        }
    }
}
