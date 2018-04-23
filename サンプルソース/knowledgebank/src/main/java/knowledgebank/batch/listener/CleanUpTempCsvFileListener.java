package knowledgebank.batch.listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import knowledgebank.service.interceptor.LogInterceptor;

@Dependent
@Named("CleanUpTempCsvFileListener")
@Interceptors(LogInterceptor.class)
public class CleanUpTempCsvFileListener implements StepListener {
    @Inject
    private JobContext jctx;
    @Inject
    private StepContext sctx;

    private String tempCsvFile;
    
    @Override
    public void beforeStep() throws Exception {
        // 実装は特になし.
    }

    @Override
    public void afterStep() throws Exception {
        String stepExitStatus = sctx.getExitStatus();
        if(stepExitStatus != null && stepExitStatus.equals("SORT-OK")){
            try {
               tempCsvFile = (String) jctx.getProperties().get("tempCsvFile");
            // tempCsvFile = (String) sctx.getProperties().get("tempCsvFile"); // KbDailyBatch は sctx から取得
                Path p = new File(tempCsvFile).toPath();
                Files.delete(p);
                Logger.getGlobal().log(Level.INFO, "File " + tempCsvFile + " has deleted.");
            }
            catch(InvalidPathException | IOException e){
                sctx.setExitStatus("FILE-DELETE-WARN");
                throw e;
            }
        }
        else {
            Logger.getGlobal().log(Level.INFO, "Retaining tempCsvFile.");
        }
    }    
}
