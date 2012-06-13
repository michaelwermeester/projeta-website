/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Project;
import be.luckycode.projetawebservice.ProjectDummy;
import be.luckycode.projetawebservice.ProjectSimpleWebSite;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pojeta.Common;

import pojeta.ProjectSimple;
import pojeta.WSProjectHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@SessionScoped
public class ProjectController implements Serializable {

    private TreeNode root;
    private TreeNode selectedNode;
    private TreeNode[] selectedNodes;
    WSProjectHelper wph;

    public ProjectController() {

        wph = new WSProjectHelper();
        wph.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());


        root = new DefaultTreeNode("root", null);


        ProjectDummy projDummy = wph.findProjectsPOJO(ProjectDummy.class);

        for (ProjectSimpleWebSite p : projDummy.getListProject()) {

            if (p.getProjectTitle() != null) {


                DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(p.getProjectId(), p.getProjectTitle(), Common.dateToString(p.getStartDate()), Common.dateToString(p.getEndDate()), p.getProjectStatus()), root);

                treeAddChildProjects(treeNode, p);
            }
        }
    }

    public TreeNode getRoot() {

        return root;
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
}
