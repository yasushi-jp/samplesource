package sb.chunk;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SimpleProcessor")
public class SimpleProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleProcessor] processItem() : {0}", item);
        return item;
    }    
}
