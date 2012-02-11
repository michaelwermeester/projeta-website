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

    public String getEmailAddress() {
        return user.getEmailAddress();
    }

    public void setEmailAddress(String emailaddress) {
        user.setEmailAddress(emailaddress);
    }

    public String getAddress() {
        return user.getAddress();
    }

    public void setAddress(String address) {
        user.setAddress(address);
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public void setPhoneNumber(String phonenumber) {
        user.setPhoneNumber(phonenumber);
    }

    public void registerUser() {
    }

    public void checkUserIfAvailable(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String username = (String) value;

        WSUserHelper userHelper = new WSUserHelper();
        userHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

        String wsuserjsonstring = userHelper.findByUsername(username, null);

        if (wsuserjsonstring.trim().equals("")) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Find the node I want using a DOM-like model.
            JsonNode rootNode = mapper.readValue(wsuserjsonstring, JsonNode.class);
            JsonNode interestingObjectNode = rootNode.get("users");

            // Parse it into a Java object.
            User[] wsuser = mapper.readValue(interestingObjectNode, User[].class);

            if ((wsuser[0].getUsername().equals(username)) == true) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le nom d'utilisateur existe déjà.",
                        "Le nom d'utilisateur existe déjà."));
            }

        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur.",
                    ex.getMessage()));
        }

    }
//    public void submit(ActionEvent event) {
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
}
