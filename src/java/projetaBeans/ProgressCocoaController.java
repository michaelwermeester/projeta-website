/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetaBeans;

import be.luckycode.projetawebservice.*;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pojeta.Common;
import pojeta.WSCommentHelper;
import pojeta.WSProgressHelper;
import pojeta.WSStatusHelper;

/**
 *
 * @author michael
 */
@ManagedBean
@RequestScoped
public class ProgressCocoaController {

    private List<Progress> progressList;
    private WSProgressHelper progressHelper;
    // ID de du projet, tâche, bug.
    private String requestId;
    // type (project, task, bug).
    private String requestType;
    
    /**
     * Creates a new instance of ProgressCocoaController
     */
    public ProgressCocoaController() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // lire ID de du projet, tâche, bug.
        this.requestId = request.getParameter("id");
        // lire le type (projet, tâche, bug).
        this.requestType = request.getParameter("type");
    }

    /**
     * @return the progressList
     */
    public List<Progress> getProgressList() {
        
        try {
            progressHelper = new WSProgressHelper();
            progressHelper.setUsernamePassword(Common.getWSUsername(), Common.getWSPassword());

            // si le type = "task".
            if (requestType.equals("task")) {
                ProgressDummy tmpProgressDummy = progressHelper.findProgressByTaskIdWebsite(ProgressDummy.class, this.requestId);

                this.progressList = tmpProgressDummy.getListProgress();
            } // si le type = "project".
            else if (requestType.equals("project")) {
                ProgressDummy tmpProgressDummy = progressHelper.findProgressByProjectIdWebsite(ProgressDummy.class, this.requestId);

                this.progressList = tmpProgressDummy.getListProgress();
            }

            // line breaks.
            for (Progress p : progressList) {
                p.setProgressComment(p.getProgressComment().replace("\n", "<br />"));
            }
        } catch (Exception e) {
        }

        return progressList;
    }

    /**
     * @param progressList the progressList to set
     */
    public void setProgressList(List<Progress> progressList) {
        this.progressList = progressList;
    }
}
