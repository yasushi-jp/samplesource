package knowledgebank.service;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import knowledgebank.entity.Knowledge;
import knowledgebank.service.interceptor.LogInterceptor;

@Stateless
@Interceptors(LogInterceptor.class)
public class KnowledgeFacade extends AbstractFacade<Knowledge> {
    
    @PersistenceContext(unitName = "knowledgebankPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KnowledgeFacade() {
        super(Knowledge.class);
    }
    
    public Knowledge find(Long id){
        Query query = em.createNamedQuery("Knowledge.findWithComment", Knowledge.class);
        query.setParameter("knowledgeId", id);
        return (Knowledge)query.getSingleResult();
    }
}
