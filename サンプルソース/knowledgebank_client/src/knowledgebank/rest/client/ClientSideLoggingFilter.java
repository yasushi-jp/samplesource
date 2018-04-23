package knowledgebank.rest.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientSideLoggingFilter implements ClientRequestFilter, ClientResponseFilter {
    static final Logger logger = Logger.getLogger(KnowledgebankClient.class.getName());

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        logger.log(Level.INFO, "リクエストを送信しました。");
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        logger.log(Level.INFO, "レスポンスを受信します。");
    }
}