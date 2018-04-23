package knowledgebank.web.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import knowledgebank.entity.Knowledge;
import knowledgebank.entity.KnowledgeComment;
import knowledgebank.service.KnowledgeCommentFacade;
import knowledgebank.service.KnowledgeFacade;
import knowledgebank.service.cdi.KnowledgeFacadeInterface;
import knowledgebank.service.cdi.TestQualifier;
import knowledgebank.web.auth.LoginSession;

@Named
@ViewScoped
public class KnowledgeShowBean implements Serializable {

    @Inject
    LoginSession loginSession;
    @EJB
    KnowledgeFacade knowledgeFacade;

    // 実行時にnon-passivation-capableとなるためtransientを用いてシリアライズ対象外としています
    // @TestQualifier は @Alternative 使用時には不要のため、試す際にはコメントアウトしてください
    @Inject
    @TestQualifier
    private transient KnowledgeFacadeInterface knowledgeFacadeDummy;

    @EJB
    KnowledgeCommentFacade knowledgeCommentFacade;

    private Long id; //表示しているナレッジのID
    private Knowledge knowledge; //表示しているナレッジ
    private KnowledgeComment knowledgeComment; //投稿コメント

    public void show() {
        knowledge = knowledgeFacade.find(id);
        //コメントの初期化
        knowledgeComment = new KnowledgeComment();
    }

    public void addComment() {
        //コメント情報を設定
        getKnowledgeComment().setAccount(loginSession.getAccount());
        getKnowledgeComment().setKnowledge(knowledge);
        knowledgeCommentFacade.addComment(getKnowledgeComment());

        //表示処理を再実施
        show();
    }

    public String remove() {
        knowledgeFacade.remove(knowledge);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage("ナレッジを削除しました。"));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        return "/knowledge/index?faces-redirect=true";
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

    public KnowledgeComment getKnowledgeComment() {
        return knowledgeComment;
    }

    public void setKnowledgeComment(KnowledgeComment knowledgeComment) {
        this.knowledgeComment = knowledgeComment;
    }

}
