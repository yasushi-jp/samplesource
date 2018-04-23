package knowledgebank.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InitializeCacheFacade {

    @PersistenceContext(unitName = "knowledgebankPU")
    private EntityManager em;

    public void initializeCache() {
        em.createNamedQuery("Category.findAll").getResultList();
    }

}
