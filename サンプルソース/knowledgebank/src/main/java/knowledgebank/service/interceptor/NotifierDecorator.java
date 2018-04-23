package knowledgebank.service.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import knowledgebank.entity.KnowledgeComment;
import knowledgebank.service.Notifier;

@Decorator
@Dependent
public class NotifierDecorator implements Notifier {

    @Inject
    @Delegate
    Notifier notifier;
    
    @Override
    public void send(long knowledgeId, KnowledgeComment comment) {

        logger.log(Level.INFO, "-- decorating --");
        String message = comment.getMessage();
        message += "..";
        comment.setMessage(message);
        notifier.send(knowledgeId, comment);
        logger.log(Level.INFO, "-- decorated --");
        
    }
    
    private static final Logger logger = Logger.getLogger(NotifierDecorator.class.getName());
    
}
