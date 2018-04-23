package knowledgebank.service;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import knowledgebank.entity.KnowledgeComment;
import knowledgebank.service.event.EventItem;
import knowledgebank.service.interceptor.LogInterceptor;
import knowledgebank.util.MailSender;

/**
 * メール送信を行うためのCDIビーン
 */
@ApplicationScoped
public class MailBean implements Notifier{
    
    private static final Logger LOG = Logger.getLogger(MailBean.class.getName());

    //イベント発火用変数
    @Inject
    Event<EventItem> event;
    
    @Inject
    MailSender sender;
    
    @EJB
    private SetupBean setup;
    
    private String smtpUser;
    
    private String smtpPassword;
    
    private String smtpServer;
    
    private int smtpPort;
    
    @PostConstruct
    private void setup(){
        
        smtpUser = setup.get("smtp.user");
        smtpPassword = setup.get("smtp.password");
        smtpServer = setup.get("smtp.server");
        smtpPort = Integer.parseInt(setup.get("smtp.port"));
        
    }
    
    /**
     * ナレッジオーナーに通知メールを送る処理
     * @param knowledgeId
     * @param comment 
     */
    @Override
    @Interceptors(LogInterceptor.class)
    public void send(long knowledgeId, KnowledgeComment comment){
        
        //メール送る処理
        sender.send("smtpUser", comment);
        
        //イベント発火
        EventItem eItem = new EventItem();
        eItem.setMessage("コメント ID:" + comment.getId() + " が作成されたため、メールを送信しました！");
        event.fire(eItem);
        
    }
    
}
