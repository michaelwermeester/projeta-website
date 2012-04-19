/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pojeta.ProjectSimple;

/**
 *
 * @author michael
 */
@ManagedBean
@SessionScoped
public class ProjectDetailController {

    /**
     * Creates a new instance of ProjectDetailController
     */
    //@ManagedProperty("#{param.strProjectId}")
    private String strProjectId;
    
    private int projectId;
    
    private ProjectSimple projectSimple;
    
    public ProjectDetailController() {
    }
    
    public String showProjectId(int id) {
        //this.projectId = Integer.parseInt(id);
        //this.projectId = Integer.parseInt(strProjectId);
        this.projectId = id;
        
        return "projectDetail.xhtml";
    }
    
    public String showProjectDetails() {
        
        this.projectId = Integer.parseInt(getStrProjectId());
        
        return "projectDetail.xhtml";
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @return the project
     */
    public ProjectSimple getProject() {
        return projectSimple;
    }

    /**
     * @param project the project to set
     */
    public void setProject(ProjectSimple project) {
        this.projectSimple = project;
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
    
    
}
