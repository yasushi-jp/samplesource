package knowledgebank.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import knowledgebank.entity.Account;

@Stateless
public class AccountFacade extends AbstractFacade<Account> {
    @PersistenceContext(unitName = "knowledgebankPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    /**
     * IDを元にUserIdに紐づくアカウント情報を返す
     * @param id
     * @return 
     */
    public Account findByUserId(String userid){
        
        Query query = em.createNamedQuery("Account.findByUserId");
        query.setParameter("userId", userid);
        Account account = (Account)query.getSingleResult();
        return account;
        
    }
    

}
