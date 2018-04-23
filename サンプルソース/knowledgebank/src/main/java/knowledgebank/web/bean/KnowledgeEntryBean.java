package knowledgebank.web.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import knowledgebank.entity.Knowledge;
import knowledgebank.service.KnowledgeFacade;
import knowledgebank.web.auth.LoginSession;

@Named
@ViewScoped
public class KnowledgeEntryBean implements Serializable {
    @Inject
    LoginSession loginSession;
    @EJB
    private KnowledgeFacade knowledgeFacade;

    private Knowledge knowledge;

    @PostConstruct
    private void init() {
        knowledge = new Knowledge();
    }

    public String save() {
        knowledge.setAccount(loginSession.getAccount());
        knowledgeFacade.create(knowledge);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage("ナレッジを登録しました。"));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);

        return "/knowledge/index?faces-redirect=true";
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }
}
