package sb.chunk;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SimpleReader")
public class SimpleReader implements ItemReader {   
    private int count = 1;
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleReader] open()");
    }

    @Override
    public Object readItem() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleReader] readItem() : count = {0}", count);
        if (count <= 7) {
            return "data" + count++;
        }
        return null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleReader] checkpointInfo()");
        return null;
    }

    @Override
    public void close() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleReader] close()");
    }
}
