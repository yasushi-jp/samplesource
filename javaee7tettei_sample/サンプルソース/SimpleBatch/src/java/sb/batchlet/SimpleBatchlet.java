package sb.batchlet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.Batchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SimpleBatchlet")
public class SimpleBatchlet implements Batchlet {
    
    @Override
    public String process() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleBatchlet] process()");
        return "SUCCESS";
    }

    @Override
    public void stop() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleBatchlet] stop()");
    } 
}
