/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.security.NoSuchAlgorithmException;

import be.luckycode.projetawebservice.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import service.UserFacadeREST;

import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.codehaus.jackson.JsonNode;
import pojeta.WSUserHelper;

import org.codehaus.jackson.map.ObjectMapper;
import pojeta.Common;

/**
 *
 * @author michael
 */
@ManagedBean
@RequestScoped
public class RegistrationBean {

//    private String password;
//    private String username;
//    private String firstname;
//    private String lastname;
    private User user = new User();

    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean() {
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        user.setPassword(password);
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        user.setUsername(username);
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public void setFirstName(String firstname) {
        user.setFirstName(firstname);
    }

    public String getLastName() {
        return user.getLastName();
    }

    public void setLastName(String lastname) {
        user.setLastName(lastname);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void registerUser() {
        
        
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Test"));
    }

    public void checkUserIfAvailable(FacesContext context, UIComponent component, 
            Object value) throws ValidatorException {

        String username = (String)value;
        
        WSUserHelper userHelper = new WSUserHelper();
        userHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        
        String wsuserjsonstring = userHelper.findByUsername(username, null);
        
        if (wsuserjsonstring.trim().equals(""))
            return;
        
        ObjectMapper mapper = new ObjectMapper();
        try {            
            // Find the node I want using a DOM-like model.
            JsonNode rootNode = mapper.readValue(wsuserjsonstring, JsonNode.class);
            JsonNode interestingObjectNode = rootNode.get("users");
        
            // Parse it into a Java object.
            User[] wsuser = mapper.readValue(interestingObjectNode, User[].class);
            
            if ((wsuser[0].getUsername().equals(username)) == true){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le nom d'utilisateur existe déjà.",
                            "Le nom d'utilisateur existe déjà."));
            }
                
        }catch(Exception ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur.",
                            ex.getMessage()));
        }
        
        /*throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "exists",
                            wsuserstring));*/
        
        
        /*ObjectMapper mapper = new ObjectMapper();
        
        try {
            User wsuser = mapper.readValue(wsuserstring, User.class);
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, wsuser.getUsername(),
                            wsuser.getUsername()));
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Error"));
        }*/
        
        
        /*if (UserHelper.userExists((String)value) == true){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "exists",
                            "exists"));
        }
        else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "no",
                            "no"));
        }*/
        
        
        
        /*throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, (String)value,
                            (String)value));*/
        

        /*try {
            UserFacadeREST userService = new UserFacadeREST();
            
            tmpUser = userService.getUser((String)value, -1);

            if (tmpUser == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username valid"));
                //FacesContext.getCurrentInstance().addMessage("completeform:username", new FacesMessage("Username valid"));
            
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not valid",
                            "Not valid"));
            
            } else {
                //FacesContext.getCurrentInstance().addMessage("completeform:username", new FacesMessage("Duplicate user"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Duplicate user"));
                
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not valid",
                            "Not valid"));
            }

        } catch (Exception ex) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("error"));
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(),
                            ex.toString()));
        }*/
    }

//    public void submit(ActionEvent event) {
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
}
