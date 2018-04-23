package knowledgebank.batch.decider;

import javax.batch.api.Decider;
import javax.batch.runtime.StepExecution;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import knowledgebank.service.interceptor.LogInterceptor;

@Dependent
@Named("StatusDecider")
@Interceptors(LogInterceptor.class)
public class StatusDecider implements Decider {
    @Inject
    private StepContext sctx;

    @Override
    public String decide(StepExecution[] executions) throws Exception {
        String ret = "SUCCESS";
        for(StepExecution se : executions){
            if(se.getExitStatus().contains("ERROR")) ret = "ERROR";
            if(se.getExitStatus().contains("WARN")) {
                // すでにERRORがセットされていたら上書きしない
                if(ret.equals("SUCCESS")) ret = "WARN"; 
            }
        }
        return ret;
    }
}
