package knowledgebank.web.session;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import knowledgebank.entity.Category;
import knowledgebank.service.CategoryFacade;

@Named
@SessionScoped
public class CategorySession implements Serializable {
    @EJB
    private CategoryFacade categoryFacade;
    private List<Category> categoryList;
    
    @PostConstruct
    private void init() {
        categoryList = categoryFacade.findAll();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
}
