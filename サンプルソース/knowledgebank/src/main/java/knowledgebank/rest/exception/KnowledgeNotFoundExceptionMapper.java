package knowledgebank.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class KnowledgeNotFoundExceptionMapper implements ExceptionMapper<KnowledgeNotFoundException> {
   @Override
   public Response toResponse(KnowledgeNotFoundException exception) {
       // メッセージボディに例外メッセージを指定してレスポンス返す
       return Response.status(Response.Status.NOT_FOUND) // NOT_FOUNDはステータスコード：404
              .entity(exception.getMessage())
              .type("text/plain").build();
   }
}
