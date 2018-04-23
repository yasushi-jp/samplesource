package knowledgebank.service.cdi;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;

/**
 * CDI基礎クラスです。
 * 各種エンティティに対する操作のメソッドを持ちます。
 */
//@Dependent
public abstract class AbstractFacadeBean<T> {
    
    private Class<T> entityClass;
    protected abstract EntityManager getEntityManager();
    
    public AbstractFacadeBean(Class<T> entityClass){
        this.entityClass = entityClass;        
    }
    
    public void create(T entity){
        getEntityManager().persist(entity);
    }
    
    public void edit(T entity){
        getEntityManager().merge(entity);
    }
    
    public void remove(T entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public T find(Object id){
        return getEntityManager().find(entityClass, id);
    }
    
}
