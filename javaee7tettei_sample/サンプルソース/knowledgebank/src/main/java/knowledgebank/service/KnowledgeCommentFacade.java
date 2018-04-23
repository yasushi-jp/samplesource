package knowledgebank.service;

import javax.inject.Inject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import knowledgebank.entity.Knowledge;
import knowledgebank.entity.KnowledgeComment;


/**
 *
 */
@Stateless
public class KnowledgeCommentFacade extends AbstractFacade<KnowledgeComment> {
   
    @Inject
    MailBean mailBean;
    
    @EJB
    AsyncMailBean aMailBean;
    
    @EJB
    KnowledgeFacade knowledgeFacade;
    
    @PersistenceContext(unitName = "knowledgebankPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public KnowledgeCommentFacade() {
        super(KnowledgeComment.class);
    }

    /**
     * コメント投稿用処理
     *
     * @param comment コメント用クラス
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void addComment(KnowledgeComment comment) {
        
        create(comment);
        
        Knowledge knowledge = comment.getKnowledge();
        knowledge.setLastCommentAt(comment.getUpdateAt());
        knowledgeFacade.edit(knowledge);
        
        mailBean.send(comment.getKnowledge().getId(), comment);
        aMailBean.send(comment.getKnowledge().getId(), comment);
        
    }
    
}
