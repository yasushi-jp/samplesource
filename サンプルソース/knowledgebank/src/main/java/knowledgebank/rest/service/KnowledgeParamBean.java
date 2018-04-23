package knowledgebank.rest.service;

import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public class KnowledgeParamBean {
    
    @PathParam("id")
    private Long id;
    
    @QueryParam("query") 
    private String searchString;
    
    @PathParam("category")
    private List<PathSegment> categoryPathList;
    
    @Context
    private UriInfo uriInfo;
    
    @Context
    private SecurityContext securityContext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<PathSegment> getCategoryPathList() {
        return categoryPathList;
    }

    public void setCategoryPathList(List<PathSegment> categoryPathList) {
        this.categoryPathList = categoryPathList;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }
}

