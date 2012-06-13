/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Project;
import be.luckycode.projetawebservice.Task;
import be.luckycode.projetawebservice.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pojeta.*;

/**
 *
 * @author michael
 */
@ManagedBean
@SessionScoped
public class TaskDetailController {

    /**
     * Creates a new instance of ProjectDetailController
     */
    //@ManagedProperty("#{param.strProjectId}")
    private String strProjectId;
    private Integer taskId;
    private ProjectSimple projectSimple;
    private Task task;
    private WSTaskHelper taskHelper;
    private WSUserHelper userHelper;

    public TaskDetailController() {
    }

    public String showTaskId(int id) {
        
        this.setTaskId((Integer) id);

        return "taskDetail.xhtml";
    }

    public String showTaskDetails() {

        this.setTaskId((Integer) projectSimple.getId());

        taskHelper = new WSTaskHelper();
        taskHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

        this.task = taskHelper.find(Task.class, this.taskId.toString());

        return "taskDetail.xhtml?faces-redirect=true";

    }

    /**
     * @return the taskId
     */
    public Integer getTaskId() {
        return taskId;
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
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    // retourne le nom de l'utilisateur qui a créé la tâche.
    public String getUserCreated() {

        try {
            // instancier l'objet web-service et s'authentifier.
            userHelper = new WSUserHelper();
            userHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            // obtenir l'objet utilisateur.
            User user = userHelper.find(User.class, this.task.getUserCreated().getUserId().toString());

            // retourner son nom.
            return user.getUsername() + " " + user.getFullName();
        } catch (Exception e) {
            return "";
        }
    }
    
    // retourne 'Oui' si le projet est terminé
    // et 'Non' si le projet est en cours. 
    public String taskCompletedString() {

        if (task.getCompleted() == null || task.getCompleted() == false) {
            return "Non";
        } else {
            return "Oui";
        }
    }

    public String taskResponsableString() {

        String responsable = "";

        if (task.getUserAssigned() != null) {

            if (task.getUserAssigned().getFullName() != null) {
                responsable += task.getUserAssigned().getFullName() + " ";
            }
            if (task.getUserAssigned().getUsername() != null) {
                responsable += "(" + task.getUserAssigned().getUsername() + ")";
            }
        }

        return responsable;
    }
}
