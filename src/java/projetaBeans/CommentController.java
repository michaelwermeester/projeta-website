/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.Comment;
import be.luckycode.projetawebservice.CommentDummy;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pojeta.Common;
import pojeta.WSCommentHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@ViewScoped
public class CommentController {

    /**
     * Creates a new instance of CommentController
     */
    private List<Comment> commentList;
    
    private WSCommentHelper wch;
    
    public CommentController() {
    }

    /**
     * @return the commentList
     */
    public List<Comment> getCommentList() {
        
        
        wch = new WSCommentHelper();
        wch.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());
        
        CommentDummy tmpCommentDummy = wch.findCommentsByTaskIdWebsite(CommentDummy.class, "2");
        
        this.commentList = tmpCommentDummy.getListComment();
        
        return commentList;
    }

    /**
     * @param commentList the commentList to set
     */
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
