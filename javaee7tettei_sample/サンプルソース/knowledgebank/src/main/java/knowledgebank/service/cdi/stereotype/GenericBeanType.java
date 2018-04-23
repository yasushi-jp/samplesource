package knowledgebank.service.cdi.stereotype;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.interceptor.Interceptors;
import knowledgebank.service.interceptor.LogInterceptor;

/**
 * ステレオタイプの例 GenericBeanType です
 * RequestScoped と インターセプタにLogInterceptorを設定しています
 */
@Stereotype
@Retention(RUNTIME)
@Target({METHOD, FIELD, TYPE})
@RequestScoped
@Interceptors(LogInterceptor.class)
public @interface GenericBeanType {
}
