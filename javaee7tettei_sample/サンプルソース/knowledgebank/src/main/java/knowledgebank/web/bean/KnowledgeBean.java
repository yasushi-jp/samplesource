package knowledgebank.web.bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import knowledgebank.entity.Category;
import knowledgebank.entity.Knowledge;
import knowledgebank.service.SearchKnowledgeFacade;

@Named
@ViewScoped
public class KnowledgeBean implements Serializable {
    @EJB
    private SearchKnowledgeFacade searchKnowledgeFacade;
    private List<Knowledge> knowledgeList;
    private String searchString;
    private List<Category> categoryList;

    public void list() {
        knowledgeList = searchKnowledgeFacade.searchKnowledge(searchString, categoryList);
    }

    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
}
