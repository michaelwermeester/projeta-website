/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Project;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pojeta.Common;
import pojeta.ProjectSimple;
import pojeta.WSProjectHelper;

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
    private String strProjectId;
    private Integer projectId;
    private ProjectSimple projectSimple;
    private Project project;
    private WSProjectHelper wph;

    public ProjectDetailController() {
    }

    public String showProjectId(int id) {
        this.projectId = id;

        return "projectDetail.xhtml";
    }

    public String showProjectDetails() {

        this.projectId = projectSimple.getId();

        wph = new WSProjectHelper();
        wph.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

        this.project = wph.find(Project.class, this.projectId.toString());

        return "projectDetail.xhtml?faces-redirect=true";
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
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

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    // retourne 'Oui' si le projet est terminé
    // et 'Non' si le projet est en cours. 
    public String projectCompletedString() {

        if (project.getCompleted() == null || project.getCompleted() == false) {
            return "Non";
        } else {
            return "Oui";
        }
    }

    // retourne 'Oui' s'il s'agit d'un projet public
    // et 'Non' pour un projet non-public.
    public String projectPublicString() {

        if (project.getFlagPublic() == null || project.getFlagPublic() == false) {
            return "Non";
        } else {
            return "Oui";
        }
    }

    public String projectResponsableString() {

        String responsable = "";

        if (project.getUserAssigned() != null) {

            if (project.getUserAssigned().getFullName() != null) {
                responsable += project.getUserAssigned().getFullName() + " ";
            }
            if (project.getUserAssigned().getUsername() != null) {
                responsable += "(" + project.getUserAssigned().getUsername() + ")";
            }
        }

        return responsable;
    }
}
