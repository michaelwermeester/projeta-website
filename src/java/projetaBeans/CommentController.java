/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Comment;
import be.luckycode.projetawebservice.CommentDummy;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import pojeta.Common;
import pojeta.ProjectSimple;
import pojeta.WSCommentHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@SessionScoped
public class CommentController {

    /**
     * Creates a new instance of CommentController
     */
    private List<Comment> commentList;
    
    private WSCommentHelper commentHelper;
    
    // bug = b; task = t; project = p.
    private char commentType; 
    
    private ProjectSimple project;
    
    public CommentController() {
    }

    /**
     * @return the commentList
     */
    public List<Comment> getCommentList() {
        
        
        /*wch = new WSCommentHelper();
        wch.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        
        CommentDummy tmpCommentDummy = wch.findCommentsByTaskIdWebsite(CommentDummy.class, "2");
        
        this.commentList = tmpCommentDummy.getListComment();*/
        
        return commentList;
    }

    /**
     * @param commentList the commentList to set
     */
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

    /**
     * @return the commentType
     */
    public char getCommentType() {
        return commentType;
    }

    /**
     * @param commentType the commentType to set
     */
    public void setCommentType(char commentType) {
        this.commentType = commentType;
    }
    
    public String showProjectComments() {
        
        //this.projectId = Integer.parseInt(getStrProjectId());
        
        //this.projectId = projectSimple.getId();
        
        commentHelper = new WSCommentHelper();
        commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        
        CommentDummy tmpCommentDummy = commentHelper.findCommentsByProjectIdWebsite(CommentDummy.class, project.getId().toString());
        
        this.commentList = tmpCommentDummy.getListComment();
        
        return "comments.xhtml?faces-redirect=true";
    }
    
    public String showTaskComments() {
        
        //this.projectId = Integer.parseInt(getStrProjectId());
        
        //this.projectId = projectSimple.getId();
        
        commentHelper = new WSCommentHelper();
        commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        
        CommentDummy tmpCommentDummy = commentHelper.findCommentsByTaskIdWebsite(CommentDummy.class, project.getId().toString());
        
        this.commentList = tmpCommentDummy.getListComment();
        
        return "comments.xhtml?faces-redirect=true";
    }
}
