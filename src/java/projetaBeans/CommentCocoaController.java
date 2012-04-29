/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Comment;
import be.luckycode.projetawebservice.CommentDummy;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pojeta.Common;
import pojeta.WSCommentHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@RequestScoped
public class CommentCocoaController {

    private List<Comment> commentList;
    private WSCommentHelper wch;
    // ID de du projet, tâche, bug.
    private String requestId;
    // type (project, task, bug).
    private String requestType;

    /**
     * Creates a new instance of CommentCocoaController
     */
    public CommentCocoaController() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // lire ID de du projet, tâche, bug.
        this.requestId = request.getParameter("id");
        // lire le type (projet, tâche, bug).
        this.requestType = request.getParameter("type");
    }

    /**
     * @return the commentList
     */
    public List<Comment> getCommentList() {

        try {
            wch = new WSCommentHelper();
            wch.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            // si le type = "task".
            if (requestType.equals("task")) {
                CommentDummy tmpCommentDummy = wch.findCommentsByTaskIdWebsite(CommentDummy.class, this.requestId);

                this.commentList = tmpCommentDummy.getListComment();
            } // si le type = "project".
            else if (requestType.equals("project")) {
                CommentDummy tmpCommentDummy = wch.findCommentsByProjectIdWebsite(CommentDummy.class, this.requestId);

                this.commentList = tmpCommentDummy.getListComment();
            }

            // line breaks.
            for (Comment c : commentList) {
                c.setComment(c.getComment().replace("\n", "<br />"));
            }
        } catch (Exception e) {
        }

        return commentList;
    }

    /**
     * @param commentList the commentList to set
     */
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
