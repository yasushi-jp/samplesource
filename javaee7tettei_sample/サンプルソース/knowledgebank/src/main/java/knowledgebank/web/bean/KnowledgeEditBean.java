package knowledgebank.web.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import knowledgebank.entity.Knowledge;
import knowledgebank.service.KnowledgeFacade;
import knowledgebank.web.auth.LoginSession;

@Named(value = "knowledgeEditBean")
@ViewScoped
public class KnowledgeEditBean implements Serializable {
    @Inject
    LoginSession loginSession;
    @EJB
    private KnowledgeFacade knowledgeFacade;

    private Long id;
    private Knowledge knowledge;

    public void edit() {
        knowledge = knowledgeFacade.find(id);
    }

    public String update() {
        //セキュリティチェック
        if (loginSession.getAccount().getId() != knowledge.getAccount().getId()) {
            throw new RuntimeException("セキュリティエラー");
        }
        knowledgeFacade.edit(knowledge);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage("ナレッジを変更しました。"));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);

        return "/knowledge/show?id=" + id + "&faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }
}
