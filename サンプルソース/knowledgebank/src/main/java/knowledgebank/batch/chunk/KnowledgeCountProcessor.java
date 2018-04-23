package knowledgebank.batch.chunk;

import java.util.Date;
import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import knowledgebank.entity.Account;
import knowledgebank.batch.model.Ranking;
import knowledgebank.service.interceptor.LogInterceptor;

@Dependent
@Named("KnowledgeCountProcessor")
@Interceptors(LogInterceptor.class)
public class KnowledgeCountProcessor implements ItemProcessor {
    @Inject
    private JobContext jctx;
    @Inject
    private EntityManager em;
    
    private Query knowledgeCountQuery;
    private long accountId;
    private String accountName;
    private Long kcount;

    @Override
    public Object processItem(Object item) throws Exception {
        accountName = ((Account)item).getName();

        // item で渡された Account の、前日までのナレッジ数を取得するクエリを作成
        knowledgeCountQuery = em.createQuery("select count(k) from Knowledge k where k.account.id = :id and k.createAt < :asOf");
        // アカウントIDの設定
        accountId = ((Account)item).getId();
        knowledgeCountQuery.setParameter("id", accountId);
        // 集計日の設定
        Date asOf = (Date)jctx.getTransientUserData();
        knowledgeCountQuery.setParameter("asOf", asOf, TemporalType.TIMESTAMP);
        
        // クエリを実行 (count集計関数の戻り値は Long)
        kcount = (Long) knowledgeCountQuery.getSingleResult();
        
        return new Ranking(accountId, accountName, kcount);
    }
}
