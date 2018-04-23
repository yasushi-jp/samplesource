package knowledgebank.util;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

@Dependent
@Named
public class LoggerProducer {
    private static final Logger logger = Logger.getLogger(LoggerProducer.class.getName());
    
    @Produces
    private Logger getLogger(InjectionPoint ip){
        return logger;
    }
    
}
