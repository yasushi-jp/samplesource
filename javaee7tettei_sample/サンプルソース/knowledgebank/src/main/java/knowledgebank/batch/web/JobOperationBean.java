package knowledgebank.batch.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "JobOperationBean")
@RequestScoped
public class JobOperationBean {
    private final JobOperator jo = BatchRuntime.getJobOperator();
    private String jobKind;
    private long exId;
    private long stopExId;
    private long restartExId;
    
    private Properties setProperty(){
        // 実行日付として現在日時をプロパティにセット
        Properties p = new Properties();
        LocalDateTime asOfDate = LocalDateTime.now(); // 現在の日時を取得
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd 形式
        p.setProperty("asofdate", asOfDate.format(dtf));        
        return p;
    }
    public String start(){ // ジョブの開始
        this.setExId(jo.start(jobKind, this.setProperty()));
        return "started.xhtml";
    }
    
    public String stop(){ // ジョブの中断
        jo.stop(this.stopExId);
        return "stopped.xhtml";
    }
    
    public String restart(){ // ジョブの再開
        this.setExId(jo.restart(this.restartExId, this.setProperty()));
        return "started.xhtml";
    }
    
    // 以下、getter/setter
    public long getExId() {
        return exId;
    }

    public void setExId(long exId) {
        this.exId = exId;
    }

    public long getStopExId() {
        return stopExId;
    }

    public void setStopExId(long stopExId) {
        this.stopExId = stopExId;
    }

    public long getRestartExId() {
        return restartExId;
    }

    public void setRestartExId(long restartExId) {
        this.restartExId = restartExId;
    }

    public String getJobKind() {
        return jobKind;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }
}
