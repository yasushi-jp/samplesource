package knowledgebank.rest.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import knowledgebank.rest.service.KnowledgeResource;

@Logging
@Provider
public class ServerSideLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    static final Logger logger = Logger.getLogger(KnowledgeResource.class.getName());
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.log(Level.INFO, "サーバが{0}メソッドのリクエストを受信しました。", requestContext.getMethod());
    }
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        logger.log(Level.INFO, "サーバがレスポンスを送信しました。");
    }
}
