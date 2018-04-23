package knowledgebank.util;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Dependent
public class EntityManagerProducer {
    
    private static final String PROPERTY_FILE = "knowledgebank";
    private static final String PERSISTENCE_CONTEXT = "knowledgebank.persistenceContext";

    private EntityManager em;

    @PostConstruct
    public void initialize(){
        logger.log(Level.INFO, "::: ENTITYMANAGER_PRODUCER IS INITIALIZED! ::: " + System.identityHashCode(this));      
    }
    
    @ApplicationScoped
    @Produces
    public EntityManager getEntityManager() {

        ResourceBundle resource = ResourceBundle.getBundle(PROPERTY_FILE);
        String persistenceContext = resource.getString(PERSISTENCE_CONTEXT);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceContext);
        em = factory.createEntityManager();
        logger.log(Level.INFO, "::: ENTITYMANAGER IS CREATED! ::: " + System.identityHashCode(em) + "on " + System.identityHashCode(this));
        return em;

    }

    public void closeEntityManager(@Disposes EntityManager em) {
        logger.log(Level.INFO, "::: ENTITYMANAGER IS CLOSED! ::: " + System.identityHashCode(em) + "on " + System.identityHashCode(this));
        em.close();
    }

    private static final Logger logger = Logger.getLogger(EntityManagerProducer.class.getName());
    
}
