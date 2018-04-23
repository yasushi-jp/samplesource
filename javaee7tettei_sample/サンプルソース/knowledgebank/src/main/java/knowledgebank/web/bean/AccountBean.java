package knowledgebank.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import knowledgebank.entity.Account;
import knowledgebank.service.AccountFacade;
import knowledgebank.util.PasswordUtil;
import knowledgebank.validator.Password;

/**
 * アカウント登録バッキングビーン
 */
@Named
@RequestScoped
public class AccountBean {
    @Inject
    AccountFacade accountFacade;

    private Account account = new Account();

    @Size(max = 255)
    @NotNull
    @Password
    private String password;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String register() {
        //ユーザにグループを設定
        account.setAccountGroup("userGroup");
        //パスワードをハッシュ化
        account.setPassword(PasswordUtil.hash(password));
        //ユーザの登録
        accountFacade.create(account);
        //FacesContextを取得
        FacesContext facesContext = FacesContext.getCurrentInstance();
        // フラッシュスコープにメッセージを設定
        facesContext.getExternalContext().getFlash().put("notice", "ユーザを追加しました。");
        // ログイン画面にリダイレクト遷移
        return "/login?faces-redirect=true";
    }
}
