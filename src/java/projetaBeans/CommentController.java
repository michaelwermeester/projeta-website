/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Comment;
import be.luckycode.projetawebservice.CommentDummy;
import be.luckycode.projetawebservice.Task;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    private String comment;
    private Comment commentObject;
    // bug = b; task = t; project = p.
    private char commentType;
    private ProjectSimple project;

    public CommentController() {
    }

    /**
     * @return the commentList
     */
    public List<Comment> getCommentList() {


        /*
         * wch = new WSCommentHelper();
         * wch.setUsernamePassword(Common.getWSUsername(),
         * Common.getWSPassword());
         *
         * CommentDummy tmpCommentDummy =
         * wch.findCommentsByTaskIdWebsite(CommentDummy.class, "2");
         *
         * this.commentList = tmpCommentDummy.getListComment();
         */

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
        try {
            commentHelper = new WSCommentHelper();
            commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            CommentDummy tmpCommentDummy = commentHelper.findCommentsByProjectIdWebsite(CommentDummy.class, project.getId().toString());

            this.commentList = tmpCommentDummy.getListComment();

            // line breaks.
            for (Comment c : commentList) {
                c.setComment(c.getComment().replace("\n", "<br />"));
            }
        } catch (Exception e) {
        }

        return "comments.xhtml?faces-redirect=true";
    }

    public String showTaskComments() {

        //this.projectId = Integer.parseInt(getStrProjectId());

        //this.projectId = projectSimple.getId();
        try {
            commentHelper = new WSCommentHelper();
            commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            CommentDummy tmpCommentDummy = commentHelper.findCommentsByTaskIdWebsite(CommentDummy.class, project.getId().toString());

            this.commentList = tmpCommentDummy.getListComment();

            // line breaks.
            for (Comment c : commentList) {
                c.setComment(c.getComment().replace("\n", "<br />"));
            }
        } catch (Exception e) {
        }

        return "comments.xhtml?faces-redirect=true";
    }

    
    public String sendComment() {

        this.setCommentObject(new Comment());
        this.getCommentObject().setComment(this.getComment());

        // lier le commentaire à la tâche.
        this.commentObject.setTaskId(new Task(project.getId()));


        commentHelper = new WSCommentHelper();
        commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

        commentHelper.createNewComment(Comment.class, getCommentObject());

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Sample warn message", "Watch out for PrimeFaces!"));

        // refresh.
        this.showTaskComments();
        this.comment = "";

        return "comments.xhtml?faces-redirect=true";
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the commentObject
     */
    public Comment getCommentObject() {
        return commentObject;
    }

    /**
     * @param commentObject the commentObject to set
     */
    public void setCommentObject(Comment commentObject) {
        this.commentObject = commentObject;
    }
}
