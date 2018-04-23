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
@Named("CommentCountProcessor")
@Interceptors(LogInterceptor.class)
public class CommentCountProcessor implements ItemProcessor {
    @Inject
    private JobContext jctx;
    @Inject
    private EntityManager em;
    
    private Query commentCountQuery;
    private long accountId;
    private String accountName;
    private Long ccount;

    @Override
    public Object processItem(Object item) throws Exception {
        accountName = ((Account)item).getName();

        // item で渡された Account の、前日までのコメント数を取得するクエリを作成
        commentCountQuery = em.createQuery("select count(kc) from KnowledgeComment kc where kc.account.id = :id and kc.createAt < :asOf");
        // アカウントIDの設定
        accountId = ((Account)item).getId();
        commentCountQuery.setParameter("id", accountId);
        // 集計日の設定
        Date asOf = (Date)jctx.getTransientUserData();
        commentCountQuery.setParameter("asOf", asOf, TemporalType.TIMESTAMP);
        
        // クエリを実行 (count集計関数の戻り値は Long)
        ccount = (Long)commentCountQuery.getSingleResult();

        return new Ranking(accountId, accountName, ccount);
    }
}
