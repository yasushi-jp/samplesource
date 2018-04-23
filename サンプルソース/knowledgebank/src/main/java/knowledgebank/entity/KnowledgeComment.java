package knowledgebank.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "KNOWLEDGE_COMMENT",
        indexes = {@Index(name = "KC_KNOWLEDGE_ID", columnList ="KNOWLEDGE_ID" )})
@NamedQueries({
    @NamedQuery(name = "KnowledgeComment.findAll", query = "SELECT k FROM KnowledgeComment k")})
@SequenceGenerator(name="knowledgeCommentSeq", initialValue=1)
public class KnowledgeComment implements Serializable {
    
    @Lob
    @Column(name = "MESSAGE")
    private String message;
    
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="knowledgeCommentSeq")
    private long id;
    
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Account account;
    
    @JoinColumn(name = "KNOWLEDGE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Knowledge knowledge;

    public KnowledgeComment() {
    }

    public KnowledgeComment(long id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist(){
        Timestamp now = new Timestamp(new Date().getTime());
        this.setUpdateAt(now);
        this.setCreateAt(now);
        // KnowledgeCommentFacade#addComment() に移動
        // ビジネスロジック側の処理だろう、ということで
//        this.knowledge.setLastCommentAt(now);
    }
    
    @PreUpdate
    public void preUpdate(){
        Timestamp now = new Timestamp(new Date().getTime());
        this.setUpdateAt(now);
//        this.knowledge.setLastCommentAt(now);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KnowledgeComment other = (KnowledgeComment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "knowledgebank.entity.KnowledgeComment[ id=" + id + " ]";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    
}
