package knowledgebank.service.cdi;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;

/**
 * インターフェースにインジェクションするクラスをCDI限定子
 * TestQualifierおよびAlternativeアノテーションを
 * 利用する例のためのダミークラスです
 */

// CDI限定子 @TestQualifier と @Alternative は両立しないので
// いずれかをコメントアウトします。本書では @Alternative を利用するよりも
// 限定子を使用する方法をお勧めしているため、@Alternative をコメントアウトしています
//@Alternative
@Dependent
@TestQualifier
public class KnowledgeFacadeDummyImpl implements KnowledgeFacadeInterface {
    
}
