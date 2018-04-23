package knowledgebank.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@Table(name = "KNOWLEDGE")
@NamedQueries({
    @NamedQuery(name = "Knowledge.findAll", query = "SELECT k FROM Knowledge k ORDER BY k.updateAt"),
@NamedQuery(name = "Knowledge.findWithComment", query = "SELECT k FROM Knowledge k LEFT JOIN FETCH k.knowledgeCommentList c WHERE k.id = :knowledgeId ORDER BY c.updateAt"),
@NamedQuery(name = "Knowledge.count", query = "SELECT count(k) AS num FROM Knowledge k")})
@SequenceGenerator(name="knowledgeSeq", initialValue=1)
@XmlRootElement
public class Knowledge implements Serializable {
    @NotNull
    @Size(max = 255)
    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;
    
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "LAST_COMMENT_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCommentAt;
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="knowledgeSeq")
    private long id;
    
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Account account;
    
    @OneToMany(mappedBy = "knowledge", cascade = {CascadeType.REMOVE})
    private List<KnowledgeComment> knowledgeCommentList;
    
    @JoinTable(name="category_has_knowledge")
    @JoinFetch()
    @ManyToMany
    private List<Category> categoryList;

    public Knowledge() {
    }

    public Knowledge(long id) {
        this.id = id;
    }
    
    @PrePersist
    public void prePersist(){
        Timestamp now = new Timestamp(new Date().getTime());
        this.setUpdateAt(now);
        this.setCreateAt(now);
        this.setLastCommentAt(now);
    }
    
    @PreUpdate
    public void preUpdate(){
        Timestamp now = new Timestamp(new Date().getTime());
        this.setUpdateAt(now);
        this.setLastCommentAt(now);
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

    @XmlTransient
    public List<KnowledgeComment> getKnowledgeCommentList() {
        return knowledgeCommentList;
    }

    public void setKnowledgeCommentList(List<KnowledgeComment> knowledgeCommentList) {
        this.knowledgeCommentList = knowledgeCommentList;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Knowledge other = (Knowledge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "knowledgebank.entity.Knowledge[ id=" + id + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Date getLastCommentAt() {
        return lastCommentAt;
    }

    public void setLastCommentAt(Date lastCommentAt) {
        this.lastCommentAt = lastCommentAt;
    }
}
