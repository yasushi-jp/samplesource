package knowledgebank.service.event;

/**
 * イベント情報を格納するクラス
 */
public class EventItem {
    
    // イベントの内容を表す文字列
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
