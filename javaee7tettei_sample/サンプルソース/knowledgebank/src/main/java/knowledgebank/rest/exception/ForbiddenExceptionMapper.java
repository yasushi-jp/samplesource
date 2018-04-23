package knowledgebank.rest.exception;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException>{
    
   @Override
   public Response toResponse(ForbiddenException exception){   
       // メッセージボディに例外メッセージを指定してレスポンス返す
       return Response.status(Response.Status.FORBIDDEN) // FORBIDDENはステータスコード：403
              .entity(exception.getMessage())
              .type("text/plain").build();
   }
}
