package knowledgebank.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import knowledgebank.validator.UserId;

@Entity
@Table(name = "ACCOUNT",uniqueConstraints = {@UniqueConstraint(name = "USER_ID_CONST", columnNames = {"USER_ID"} )})
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account a WHERE a.userId = :userId")})
@SequenceGenerator(name="accountSeq", initialValue=1)
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accountSeq")
    private long id;
    
    @Size(max = 255)
    @NotNull
    @Column(name = "ACCOUNT_GROUP", length = 255)
    private String accountGroup;
    
    @Size(max = 255)
    @Column(name = "MAIL", length = 255)
    private String mail;
    
    @Size(max = 255)
    @NotNull
    @Column(name = "NAME", length = 255)
    private String name;
    
    @Size(max = 255)
    @NotNull
    @Column(name = "PASSWORD", length = 255)
    private String password;
    
    @UserId
    @NotNull
    @Column(name = "USER_ID", length = 255)
    private String userId;
    
    @OneToMany(mappedBy = "account")
    private List<Knowledge> knowledgeList;
    
    @OneToMany(mappedBy = "account")
    private List<KnowledgeComment> knowledgeCommentList;

    public Account() {
    }

    public Account(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    @XmlTransient
    public List<KnowledgeComment> getKnowledgeCommentList() {
        return knowledgeCommentList;
    }

    public void setKnowledgeCommentList(List<KnowledgeComment> knowledgeCommentList) {
        this.knowledgeCommentList = knowledgeCommentList;
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
        final Account other = (Account) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "knowledgebank.entity.Account[ id=" + id + " ]";
    }
}
