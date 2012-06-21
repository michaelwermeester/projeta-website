/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.BugDummy;
import be.luckycode.projetawebservice.BugSimpleWebSite;
import be.luckycode.projetawebservice.ProjectDummy;
import be.luckycode.projetawebservice.ProjectSimpleWebSite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
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
public class BugController implements Serializable {

    private Integer projectId;
    private ProjectSimple projectSimple;
    private WSBugHelper bugHelper;
    private TreeNode root;
    private TreeNode selectedNode;
    private TreeNode[] selectedNodes;
    
    /**
     * Creates a new instance of BugController
     */
    public BugController() {
        
        showBugs();
    }
    
    public String showBugs() {

        try {
            this.projectId = projectSimple.getId();

            bugHelper = new WSBugHelper();
            bugHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            root = new DefaultTreeNode("root", null);


            BugDummy bugDummy = bugHelper.findBugsByProjectIdPOJO(BugDummy.class, this.projectId.toString());

            for (BugSimpleWebSite b : bugDummy.getListBug()) {

                if (b.getBugTitle() != null) {
                    DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(b.getBugId(), b.getBugId().toString(), b.getBugTitle(), b.getBugType(), b.getProjectStatus()), root);
                }
            }

            return "bugs.xhtml?faces-redirect=true";
        } catch (Exception e) {
            // No bugs...
            return "";
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
