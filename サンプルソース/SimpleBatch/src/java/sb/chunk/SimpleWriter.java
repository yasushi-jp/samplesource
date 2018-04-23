package sb.chunk;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("SimpleWriter")
public class SimpleWriter implements ItemWriter {

    @Override
    public void open(Serializable checkpoint) throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleWriter] open()");
    }
    
    @Override
    public void close() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleWriter] close()");
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleWriter] writeItems() : {0}", items);
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        Logger.getGlobal().log(Level.INFO, "[SimpleWriter] checkPointInfo()");
        return null;
    }
}
