package knowledgebank.web.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import knowledgebank.entity.Category;
import knowledgebank.service.CategoryFacade;

@FacesConverter("categoryId")
public class CategoryIdConverter implements Converter {

    @EJB
    private CategoryFacade categoryFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return categoryFacade.find(new Long(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Category category = (Category) value;
        return String.valueOf(category.getId());
    }
    
}
