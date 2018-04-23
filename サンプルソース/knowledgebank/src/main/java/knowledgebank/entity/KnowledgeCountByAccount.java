package knowledgebank.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "KnowledgeCount_By_Account")
@SequenceGenerator(name="knowledgeCountSeq", initialValue=1)
public class KnowledgeCountByAccount implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="knowledgeCountSeq")
    private BigDecimal id;    
    
    @Column(name = "ACCOUNT_ID")
    private BigDecimal accountId;
    
    @Size(max = 255)
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    
    @Column(name = "COUNT")
    private long count;
    
    @Column(name = "AS_OF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date asOf;

    public KnowledgeCountByAccount() {
    }

    public KnowledgeCountByAccount(BigDecimal accountId, String accountName, long count, Date asOf) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.count = count;
        this.asOf = asOf;
    }

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return the accountId
     */
    public BigDecimal getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * @return the asOf
     */
    public Date getAsOf() {
        return asOf;
    }

    /**
     * @param CountAt the asOf to set
     */
    public void setAsOf(Date asOf) {
        this.asOf = asOf;
    }

}
