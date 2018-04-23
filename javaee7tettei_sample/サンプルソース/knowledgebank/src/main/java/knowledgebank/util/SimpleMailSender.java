package knowledgebank.util;

import javax.enterprise.context.RequestScoped;

/**
 * メール送信機能の実装クラス
 */
@RequestScoped
public class SimpleMailSender implements MailSender {

    @Override
    public void send(String smtpinfo, Object message) {

    }

}
