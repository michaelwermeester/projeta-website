/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.*;
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
        
        if (commentType == 'p')
            showProjectComments();
    }

    /**
     * @return the commentList
     */
    public List<Comment> getCommentList() {

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

        commentType = 'p';
        
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
        
        commentType = 't';

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
    
    public String showBugComments() {
        
        commentType = 'b';

        try {
            commentHelper = new WSCommentHelper();
            commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            CommentDummy tmpCommentDummy = commentHelper.findCommentsByBugIdWebsite(CommentDummy.class, project.getId().toString());

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
        if (commentType == 't')
            this.commentObject.setTaskId(new Task(project.getId()));
        if (commentType == 'p')
            this.commentObject.setProjectId(new Project(project.getId()));
        if (commentType == 'b')
            this.commentObject.setBugId(new Bug(project.getId()));

        commentHelper = new WSCommentHelper();
        
        FacesContext context = FacesContext.getCurrentInstance();
        AuthBackingBean authBean = (AuthBackingBean) context.getApplication().evaluateExpressionGet(context, "#{authBackingBean}", AuthBackingBean.class);
        //String username = authBean.getUsername();
        //String password = authBean.getPassword();
        commentHelper.setUsernamePassword(authBean.getUsername(), authBean.getPassword());
        //commentHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

        commentHelper.createNewComment(Comment.class, getCommentObject());

        // refresh.
        if (commentType == 't')
            this.showTaskComments();
        if (commentType == 'p')
            this.showProjectComments();
        if (commentType == 'b')
            this.showBugComments();
        
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
