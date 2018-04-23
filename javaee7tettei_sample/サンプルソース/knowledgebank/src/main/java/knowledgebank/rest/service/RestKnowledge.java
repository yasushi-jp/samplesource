package knowledgebank.rest.service;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import knowledgebank.entity.Category;
import knowledgebank.entity.Knowledge;

@XmlRootElement
public class RestKnowledge {

    private long id;
    @NotNull
    private String title;
    @NotNull
    private String description;

    private String accountName;

    private List<String> categoryNameList;

    public RestKnowledge() {
    }

    public RestKnowledge(Knowledge knowledge) {
        
        id = knowledge.getId();
        title = knowledge.getTitle();
        description = knowledge.getDescription();
        accountName = knowledge.getAccount().getName();
        
        categoryNameList = new ArrayList<>();
        for (Category category : knowledge.getCategoryList()) {
            categoryNameList.add(category.getName());
        }
    }

    public RestKnowledge(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public List<String> getCategoryNameList() {
        return categoryNameList;
    }

    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RestKnowledge other = (RestKnowledge) obj;
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
        return "knowledgebank.entity.RestKnowledge[ id=" + id + " ]";
    }
}
