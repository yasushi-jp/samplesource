package knowledgebank.service;

import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * 初期化用
 * - MailBean 用 SMTP 設定を外部ファイルから読み込む
 */
@Singleton
@Startup
public class SetupBean {

    private static final String PROPERTY_FILE = "knowledgebank";
    
    private ResourceBundle resource;
    
    /**
     * ResourceBundleを利用して設定を読み込むメソッド。
     * コンストラクタでこの内容を書くと、コンテナの処理(EJBの準備)が完全に終わる前に
     * 実行されてしまうため、@PostConstructを利用して順序よくセットアップするようにする
     */
    @PostConstruct
    public void setup(){
        resource = ResourceBundle.getBundle(PROPERTY_FILE);
    }
    
    /**
     * キーに紐づく設定値を返却する
     * @param key プロパティのキー名称
     * @return プロパティの設定項目
     */
    public String get(String key){
        return resource.getString(key);
    }
    
}
