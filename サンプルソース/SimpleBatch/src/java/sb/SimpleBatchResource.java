package sb;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/jbatch")
public class SimpleBatchResource {
    @POST
    @Path("start")
    @Produces("text/plain")
    public String startBatch(){
        JobOperator jo = BatchRuntime.getJobOperator();
        long id = jo.start("SimpleBatch", null);
        return "SimpleBatch has started. id = " + id;
    }
}
