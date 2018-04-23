package knowledgebank.web.auth;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import knowledgebank.entity.Account;

@Named
@SessionScoped
public class LoginSession implements Serializable {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
