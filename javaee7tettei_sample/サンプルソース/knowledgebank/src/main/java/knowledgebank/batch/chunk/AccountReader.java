package knowledgebank.batch.chunk;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import knowledgebank.entity.Account;
import knowledgebank.service.interceptor.LogInterceptor;

@Dependent
@Named("AccountReader")
@Interceptors(LogInterceptor.class)
public class AccountReader implements ItemReader {
    @Inject
    private JobContext jctx;
    @Inject
    private EntityManager em;

    private Date asOf;
    private List<Account> accounts;
    private int count = 0;

    public AccountReader() {
    }
    
    @Override
    public void open(Serializable checkpoint) throws Exception {   
        // 処理日のセット
        String s = (String) jctx.getProperties().get("asOf");
        SimpleDateFormat propFormat = new SimpleDateFormat("yyyy-MM-dd");
        asOf = propFormat.parse(s); // これで該当日付の 00:00:00 にセットされる
        jctx.setTransientUserData(asOf); // Job 全体で持ち回り
        
        // アカウントレコードの読み取り
        Query accountQuery = em.createNamedQuery("Account.findAll");
        int pos = (checkpoint == null)? 0 : (int)checkpoint; 
        // 前回までの読み込み位置へ移動
        accountQuery.setFirstResult(pos);
        accounts = accountQuery.getResultList(); 
        /** P533～534 チェックポイントの挙動確認用
        Logger.getGlobal().info("pos = " + pos);
        Logger.getGlobal().info("accounts.size() = " + accounts.size()); 
        */
        count = 0;
    }

    @Override
    public Object readItem() throws Exception {
        if(count >= accounts.size()){
            return null;  // 処理終端時は null で応答
        }
        /** P533 ジョブの中断/再開の挙動確認用
        Logger.getGlobal().info("count = " + count);
        Thread.sleep(2000);
        */
        return accounts.get(count++);
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return count;
    }

    @Override
    public void close() throws Exception {
        // Inject で em を取得した場合は、em.close() の必要なし。
    }
}
