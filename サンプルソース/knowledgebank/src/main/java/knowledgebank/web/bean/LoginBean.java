package knowledgebank.web.bean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import knowledgebank.entity.Account;
import knowledgebank.service.AccountFacade;
import knowledgebank.web.auth.LoginSession;

@Named
@RequestScoped
public class LoginBean implements Serializable {
    @Inject
    private LoginSession loginSession;
    @Inject AccountFacade accountFacade;
    @NotNull
    private String userId;
    @NotNull
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            //ログイン処理
            request.login(userId, password);

            //ユーザIDからアカウント情報を取得
            Account account = accountFacade.findByUserId(userId);

            //セッションスコープのログインセッションクラスにアカウント情報を設定
            //ログイン後に別画面で利用するため
            loginSession.setAccount(account);

            //ログイン後にナレッジ一覧へ遷移
            return "knowledge/index?faces-redirect=true";
        } catch (Exception e) {
            //ログインエラー時はエラー画面へ遷移
            return "error";
        }
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            //ログアウト処理
            request.logout();
        } catch (Exception e) {
            //エラーでもログイン画面へ遷移
        }
        return "/login?faces-redirect=true";
    }
}
