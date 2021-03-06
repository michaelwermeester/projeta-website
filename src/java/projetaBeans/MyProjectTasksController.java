/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.ProjectDummy;
import be.luckycode.projetawebservice.ProjectSimpleWebSite;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pojeta.Common;
import pojeta.ProjectSimple;
import pojeta.WSTaskHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@RequestScoped
public class MyProjectTasksController {

    private Integer projectId;
    private ProjectSimple projectSimple;
    private WSTaskHelper taskHelper;
    private TreeNode root;
    private TreeNode selectedNode;
    private TreeNode[] selectedNodes;

    /**
     * Creates a new instance of TaskController
     */
    public MyProjectTasksController() {

        /*taskHelper = new WSTaskHelper();
        taskHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

        root = new DefaultTreeNode("root", null);


        ProjectDummy projDummy = taskHelper.findMyProjectTasksPOJO(ProjectDummy.class);

        for (ProjectSimpleWebSite p : projDummy.getListProject()) {

            if (p.getProjectTitle() != null) {
                DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(p.getProjectId(), p.getProjectTitle(), Common.dateToString(p.getStartDate()), Common.dateToString(p.getEndDate()), p.getProjectStatus()), root);

                treeAddChildProjects(treeNode, p);
            }
        }*/
    }

    public String showTasks() {

        try {
            //this.projectId = projectSimple.getId();

            taskHelper = new WSTaskHelper();
            taskHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            root = new DefaultTreeNode("root", null);


            ProjectDummy projDummy = taskHelper.findTasksByProjectIdPOJO(ProjectDummy.class, this.projectId.toString());

            for (ProjectSimpleWebSite p : projDummy.getListProject()) {

                if (p.getProjectTitle() != null) {
                    DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(p.getProjectId(), p.getProjectTitle(), Common.dateToString(p.getStartDate()), Common.dateToString(p.getEndDate()), p.getProjectStatus()), root);

                    treeAddChildProjects(treeNode, p);
                }
            }

            return "tasks.xhtml?faces-redirect=true";
        } catch (Exception e) {
            // No tasks...
            return "";
        }

    }

    public void treeAddChildProjects(DefaultTreeNode treeNode, ProjectSimpleWebSite project) {

        if (project != null && project.getChildProject() != null) {

            for (ProjectSimpleWebSite p : project.getChildProject()) {

                if (p.getProjectTitle() != null) {
                    DefaultTreeNode defTreeNode = new DefaultTreeNode(new ProjectSimple(p.getProjectId(), p.getProjectTitle(), Common.dateToString(p.getStartDate()), Common.dateToString(p.getEndDate()), p.getProjectStatus()), treeNode);

                    treeAddChildProjects(defTreeNode, p);
                }
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
