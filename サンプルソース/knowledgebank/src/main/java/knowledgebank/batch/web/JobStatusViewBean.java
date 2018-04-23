package knowledgebank.batch.web;

import java.util.Date;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "JobStatusViewBean")
@RequestScoped
public class JobStatusViewBean {
    private final JobOperator jo = BatchRuntime.getJobOperator();
    private long instanceId = 0;
    private String jobInName;
    private long exId = 0;
    private String jobName;
    private Properties jobParams;
    private String batchStatus;
    private String exitStatus;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private Date lastUpdatedTime;            

    public String viewStatus(){
        JobInstance jin = jo.getJobInstance(exId);
        instanceId = jin.getInstanceId();
        jobInName = jin.getJobName();
        
        JobExecution jex = jo.getJobExecution(exId);
        jobName = jex.getJobName();
        jobParams = jex.getJobParameters();
        batchStatus = jex.getBatchStatus().toString();
        exitStatus = jex.getExitStatus();
        createTime = jex.getCreateTime();
        startTime = jex.getStartTime();
        endTime = jex.getEndTime();
        lastUpdatedTime = jex.getLastUpdatedTime();    
        
        return "status.xhtml";
    }
    
    // 以下、setter/getter
    public long getExId() {
        return exId;
    }

    public void setExId(long exId) {
        this.exId = exId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Properties getJobParams() {
        return jobParams;
    }

    public void setJobParams(Properties jobParams) {
        this.jobParams = jobParams;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public String getJobInName() {
        return jobInName;
    }

    public void setJobInName(String JobInName) {
        this.jobInName = JobInName;
    }
    
}
