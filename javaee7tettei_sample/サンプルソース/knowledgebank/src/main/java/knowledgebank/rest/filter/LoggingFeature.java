package knowledgebank.rest.filter;

import javax.ws.rs.GET;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import knowledgebank.rest.service.KnowledgeResource;

@Provider
public class LoggingFeature implements DynamicFeature {
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext featureContext) {
        if (resourceInfo.getResourceClass().equals(KnowledgeResource.class)
                && resourceInfo.getResourceMethod().isAnnotationPresent(GET.class)) {
            featureContext.register(ServerSideLoggingFilter.class);
        }
    }
}