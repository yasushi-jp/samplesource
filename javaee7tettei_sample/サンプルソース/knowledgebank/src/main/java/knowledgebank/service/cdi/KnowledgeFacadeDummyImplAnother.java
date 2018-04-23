package knowledgebank.service.cdi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 * インジェクションするクラスの指定にAlternativeアノテーションを
 * 利用する例のためのダミークラスです
 */
@Dependent
@Alternative
public class KnowledgeFacadeDummyImplAnother implements KnowledgeFacadeInterface {
    
}
