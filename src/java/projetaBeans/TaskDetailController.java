/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Project;
import be.luckycode.projetawebservice.Task;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pojeta.Common;
import pojeta.ProjectSimple;
import pojeta.WSProjectHelper;
import pojeta.WSTaskHelper;

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
    
    public TaskDetailController() {
    }
    
    public String showTaskId(int id) {
        //this.projectId = Integer.parseInt(id);
        //this.projectId = Integer.parseInt(strProjectId);
        this.setTaskId((Integer) id);
        
        return "taskDetail.xhtml";
    }
    
    public String showTaskDetails() {
        
        //this.projectId = Integer.parseInt(getStrProjectId());
        
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
    
    
}
