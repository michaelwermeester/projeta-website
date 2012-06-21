/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.BugDummy;
import be.luckycode.projetawebservice.BugSimpleWebSite;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pojeta.ProjectSimple;
import pojeta.WSBugHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@RequestScoped
public class MyProjectBugsController {

    private Integer projectId;
    private ProjectSimple projectSimple;
    private WSBugHelper bugHelper;
    private TreeNode root;
    private TreeNode selectedNode;
    private TreeNode[] selectedNodes;

    /**
     * Creates a new instance of BugController
     */
    public MyProjectBugsController() {
        //this.projectId = projectSimple.getId();

        bugHelper = new WSBugHelper();
        FacesContext context = FacesContext.getCurrentInstance();
        AuthBackingBean authBean = (AuthBackingBean) context.getApplication().evaluateExpressionGet(context, "#{authBackingBean}", AuthBackingBean.class);
        bugHelper.setUsernamePassword(authBean.getUsername(), authBean.getPassword());

        root = new DefaultTreeNode("root", null);


        BugDummy bugDummy = bugHelper.findMyProjectBugsPOJO(BugDummy.class);

        for (BugSimpleWebSite b : bugDummy.getListBug()) {

            if (b.getBugTitle() != null) {
                DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(b.getBugId(), b.getBugId().toString(), b.getBugTitle(), b.getBugType(), b.getProjectStatus()), root);
            }
        }
    }

    public String showBugs() {

        try {
            this.projectId = projectSimple.getId();

            bugHelper = new WSBugHelper();
            FacesContext context = FacesContext.getCurrentInstance();
            AuthBackingBean authBean = (AuthBackingBean) context.getApplication().evaluateExpressionGet(context, "#{authBackingBean}", AuthBackingBean.class);
            bugHelper.setUsernamePassword(authBean.getUsername(), authBean.getPassword());

            root = new DefaultTreeNode("root", null);


            BugDummy bugDummy = bugHelper.findBugsReportedPOJO(BugDummy.class);

            for (BugSimpleWebSite b : bugDummy.getListBug()) {

                if (b.getBugTitle() != null) {
                    DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(b.getBugId(), b.getBugId().toString(), b.getBugTitle(), b.getBugType(), b.getProjectStatus()), root);
                }
            }

            return "myBugs.xhtml?faces-redirect=true";
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
