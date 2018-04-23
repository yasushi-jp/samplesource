package knowledgebank.util;

/**
 * メール送信機能に関するインターフェース
 */
public interface MailSender {
    
    public void send(String smtpinfo, Object message);
    
}
