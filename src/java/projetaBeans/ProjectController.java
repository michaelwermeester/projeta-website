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
        //List<Project> listProj = wph.findProjectsPOJO(List.class);
        
        // works !
        /*for (Project p : projDummy.getListProject()) {
         
            if (p.getProjectTitle() != null) {
                TreeNode test = new DefaultTreeNode(new Document(p.getProjectTitle(), "-", "-", "En cours"), root);
            }
        }*/
        
        for (ProjectSimpleWebSite p : projDummy.getListProject()) {
         
            if (p.getProjectTitle() != null) {
                DefaultTreeNode treeNode = new DefaultTreeNode(new ProjectSimple(p.getProjectTitle(), "-", "-", "En cours"), root);
                
                treeAddChildProjects(treeNode, p);
            }
        }
    }  
      
    public TreeNode getRoot() {  
        
        
        
        
        /*for (int i = 0; i < 50; i++) {
            //TreeNode test = new DefaultTreeNode(new Document("test", "-", "-", "En cours"), root);
        }*/
        
        /*for (ProjectSimpleWebSite p : projDummy.getListProject()) {
         
            if (p.getProjectTitle() != null) {
                TreeNode test = new DefaultTreeNode(p, root);
            }
        }*/
        
        //Project p = wph.find(Project.class, "6");
        //TreeNode test = new DefaultTreeNode(new Document(p.getProjectTitle(), "-", "-", "En cours"), root);
        
        
        /*TreeNode documents = new DefaultTreeNode(new Document("Site web AT systems", "-", "-", "En cours"), root);  
        TreeNode pictures = new DefaultTreeNode(new Document("ERP - Module facturation", "-", "-", "Point zéro"), root);  
        TreeNode movies = new DefaultTreeNode(new Document("Upgrade serveurs", "-", "-", "Point zéro"), root);  
          
        TreeNode work = new DefaultTreeNode(new Document("Analyse", "-", "-", "En cours"), documents);  
        TreeNode primefaces = new DefaultTreeNode(new Document("Prototype", "-", "-", "Point zéro"), documents);  
          
        //Documents  
        TreeNode expenses = new DefaultTreeNode("document", new Document("Collecte d'informations", "-", "-", "Point zéro"), work);  
        TreeNode resume = new DefaultTreeNode("document", new Document("Règles de gestion", "-", "-", "En cours"), work);  
        TreeNode refdoc = new DefaultTreeNode("document", new Document("Mockup", "-", "-", "Point zéro"), primefaces);  
          
        //Pictures  
        TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image", "Point zéro"), pictures);  
        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image", "Point zéro"), pictures);  
        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image", "Point zéro"), pictures);  
          
        //Movies  
        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder", "Point zéro"), movies);  
        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder", "Point zéro"), movies);  
          
        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File", "Point zéro"), pacino);  
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File", "Point zéro"), pacino);  
          
        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File", "Point zéro"), deniro);  
        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File", "Point zéro"), deniro);
        */
        return root;  
    }  
    
    public void treeAddChildProjects(DefaultTreeNode treeNode, ProjectSimpleWebSite project) {
        
        if (project != null && project.getChildProject() != null) {
        
        for (ProjectSimpleWebSite p : project.getChildProject()) {
         
            if (p.getProjectTitle() != null) {
                DefaultTreeNode defTreeNode = new DefaultTreeNode(new ProjectSimple(p.getProjectTitle(), "-", "-", "En cours"), treeNode);
                
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
