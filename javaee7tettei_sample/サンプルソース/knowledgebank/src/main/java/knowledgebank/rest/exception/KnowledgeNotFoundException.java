package knowledgebank.rest.exception;

public class KnowledgeNotFoundException extends RuntimeException{
   public KnowledgeNotFoundException(String s){
      super(s);
   }
}
