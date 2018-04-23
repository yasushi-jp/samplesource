package knowledgebank.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query="SELECT c FROM Category c")})
@SequenceGenerator(name="categorySeq", initialValue=1)
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categorySeq")
    private long id;
    
    @Column(name = "NAME")
    @NotNull
    private String name;
    
    @ManyToMany(mappedBy = "categoryList")
    private List<Knowledge> knowledgeList;

    public Category(){
        super();
    }
    
    public Category(long id){
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "knowledgebank.entity.Category[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }
}