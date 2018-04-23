package knowledgebank.service;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import knowledgebank.entity.KnowledgeComment;
import knowledgebank.service.event.EventItem;

/**
 * メール送信機能を非同期に行うためのステートレスセッションビーン
 */
@Stateless
@LocalBean
public class AsyncMailBean implements Notifier {

    private static final Logger logger = Logger.getLogger(AsyncMailBean.class.getName());

    @Inject
    Event<EventItem> event;
    
    @EJB
    private SetupBean setup;

    private String smtpUser;

    private String smtpPassword;

    private String smtpServer;

    private int smtpPort;

    @PostConstruct
    private void setup() {

        smtpUser = setup.get("smtp.user");
        smtpPassword = setup.get("smtp.password");
        smtpServer = setup.get("smtp.server");
        smtpPort = Integer.parseInt(setup.get("smtp.port"));

    }

    /**
     * コメントのあったナレッジについて、情報を非同期でメール送信するためのメソッド
     * (サンプルのためメール送信は行いません)
     * @param knowledgeId コメント対象となったナレッジのID
     * @param comment コメント内容
     */
    @Override
    @Asynchronous
    public void send(long knowledgeId, KnowledgeComment comment) {

        logger.info("メールを送信しました To:" + knowledgeId + " comment:" + comment.getMessage());
        
        EventItem eItem = new EventItem();
        eItem.setMessage("ASYNC!!!!");
        event.fire(eItem);

    }

}
