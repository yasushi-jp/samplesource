package knowledgebank.service.event;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;

/**
 * イベント処理を行うオブザーバクラス
 */
@Dependent
public class EventHandler {
    
    public void handleEvent(@Observes EventItem item){        
        logger.log(Level.INFO, "EVENT:" + item.getMessage());
    }
    
    private static final Logger logger = Logger.getLogger(EventHandler.class.getName());    
    
}
