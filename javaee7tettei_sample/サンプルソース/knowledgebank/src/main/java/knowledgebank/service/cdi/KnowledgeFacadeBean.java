package knowledgebank.service.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import knowledgebank.entity.Knowledge;
import knowledgebank.service.cdi.stereotype.GenericBeanType;
import knowledgebank.service.interceptor.LogInterceptor;

/**
 *
 */

// 「5.3 CDI」で作成したものをコメントアウトし、
// 「5.5.2 ステレオタイプ」で説明した @GenericBeanTypeに変更しています
//@RequestScoped
//@Interceptors(LogInterceptor.class)
@GenericBeanType
public class KnowledgeFacadeBean extends AbstractFacadeBean {

    // プロデューサから取得するように変更するためコメントアウト
    // @PersistenceContext(unitName = "knowledgebankPU")    
    @Inject
    private EntityManager em;

    @Inject
    @TestQualifier
    private KnowledgeFacadeInterface knowledgeFacadeDummy;
    
    public KnowledgeFacadeBean() {
        super(Knowledge.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Knowledge find(Long id) {
        Query query = em.createNamedQuery("Knowledge.findWithComment", Knowledge.class);
        query.setParameter("knowledgeId", id);
        return (Knowledge) query.getSingleResult();
    }

}
