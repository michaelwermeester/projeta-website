/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Bug;
import be.luckycode.projetawebservice.Task;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojeta.Common;
import pojeta.ProjectSimple;
import pojeta.WSBugHelper;
import pojeta.WSTaskHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@SessionScoped
public class BugDetailController {

    
    private String strProjectId;
    
    private Integer bugId;
    
    private ProjectSimple projectSimple;
    
    private Bug bug;
    
    private WSBugHelper bugHelper;
    
    /**
     * Creates a new instance of BugDetailController
     */
    public BugDetailController() {
    }
    
    
    public String showBugId(int id) {
        this.setBugId((Integer) id);
        
        return "bugDetail.xhtml";
    }
    
    public String showBugDetails() {
        
        this.setBugId((Integer) projectSimple.getId());
        
        bugHelper = new WSBugHelper();
        bugHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        
        this.bug = bugHelper.find(Bug.class, this.bugId.toString());
        
        return "bugDetail.xhtml?faces-redirect=true";
    }

    /**
     * @return the strProjectId
     */
    public String getStrProjectId() {
        return strProjectId;
    }

    /**
     * @param strProjectId the strProjectId to set
     */
    public void setStrProjectId(String strProjectId) {
        this.strProjectId = strProjectId;
    }

    /**
     * @return the bugId
     */
    public Integer getBugId() {
        return bugId;
    }

    /**
     * @param bugId the bugId to set
     */
    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    /**
     * @return the projectSimple
     */
    public ProjectSimple getProjectSimple() {
        return projectSimple;
    }

    /**
     * @param projectSimple the projectSimple to set
     */
    public void setProjectSimple(ProjectSimple projectSimple) {
        this.projectSimple = projectSimple;
    }

    /**
     * @return the bug
     */
    public Bug getBug() {
        return bug;
    }

    /**
     * @param bug the bug to set
     */
    public void setBug(Bug bug) {
        this.bug = bug;
    }

    /**
     * @return the bugHelper
     */
    public WSBugHelper getBugHelper() {
        return bugHelper;
    }

    /**
     * @param bugHelper the bugHelper to set
     */
    public void setBugHelper(WSBugHelper bugHelper) {
        this.bugHelper = bugHelper;
    }
    
    // retourne 'Oui' si le projet est terminé
    // et 'Non' si le projet est en cours. 
    public String bugFixedString() {

        if (bug.getFixed() == null || bug.getFixed() == false) {
            return "Non";
        } else {
            return "Oui";
        }
    }

    public String bugResponsableString() {

        String responsable = "";

        if (bug.getUserAssigned() != null) {

            if (bug.getUserAssigned().getFullName() != null) {
                responsable += bug.getUserAssigned().getFullName() + " ";
            }
            if (bug.getUserAssigned().getUsername() != null) {
                responsable += "(" + bug.getUserAssigned().getUsername() + ")";
            }
        }

        return responsable;
    }
    
    public String bugReportedByString() {

        String userReported = "";

        if (bug.getUserReported() != null) {

            if (bug.getUserReported().getFullName() != null) {
                userReported += bug.getUserReported().getFullName() + " ";
            }
            if (bug.getUserReported().getUsername() != null) {
                userReported += "(" + bug.getUserReported().getUsername() + ")";
            }
        }

        return userReported;
    }
}
