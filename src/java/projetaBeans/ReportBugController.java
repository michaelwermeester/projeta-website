/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Bug;
import be.luckycode.projetawebservice.Bugcategory;
import be.luckycode.projetawebservice.Project;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pojeta.Common;
import pojeta.ProjectSimple;
import pojeta.WSBugHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@SessionScoped
public class ReportBugController implements Serializable {
    
    private ProjectSimple project;
            
    private Bug bug;
    
    private WSBugHelper bugHelper;
    
    /**
     * Creates a new instance of ReportBugController
     */
    public ReportBugController() {
        
        this.bug = new Bug();
        this.bug.setBugcategoryId(new Bugcategory());

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
     * @return the project
     */
    public ProjectSimple getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(ProjectSimple project) {
        this.project = project;
    }

    public String showBugReportForm() {
        
        this.bug.setProjectId(new Project(project.getId()));
        
        return "reportBug.xhtml?faces-redirect=true";
    }
    
    public String sendBugReport() {
        
        //Project p = new Project(project.getId());
        //this.bug.setProjectId(p);
        
        bugHelper = new WSBugHelper();
        //bugHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        FacesContext context = FacesContext.getCurrentInstance();
        AuthBackingBean authBean = (AuthBackingBean) context.getApplication().evaluateExpressionGet(context, "#{authBackingBean}", AuthBackingBean.class);
        //String username = authBean.getUsername();
        //String password = authBean.getPassword();
        bugHelper.setUsernamePassword(authBean.getUsername(), authBean.getPassword());
        
        bugHelper.createNewBug(Bug.class, bug);
        
        return "bugReportConfirmation.xhtml?faces-redirect=true";
    }
}
