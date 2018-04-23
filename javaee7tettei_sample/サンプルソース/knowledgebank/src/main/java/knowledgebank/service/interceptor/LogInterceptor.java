package knowledgebank.service.interceptor;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * 対象メソッドの実行時間を採取、出力するインターセプタ
 * @see GenericBeanType
 */
@Interceptor
public class LogInterceptor {
    
    private static final Logger LOG = Logger.getLogger(LogInterceptor.class.getName());
    
    public LogInterceptor(){
        
    }
    
    /**
     * 処理の前後で時間を取得し、差分を実行時間としてログ出力する
     * @param context 実行コンテキスト
     * @throws Exception 
     */
    @AroundInvoke
    public Object turnAroundTimeLog(InvocationContext context) throws Exception {

        long before = System.nanoTime();
        Object ret = context.proceed();
        long after = System.nanoTime();
        
        Class clazz = context.getMethod().getDeclaringClass();
        Method method = context.getMethod();
        LOG.info(clazz.getCanonicalName() + "#" + method.getName() + "() was invoked, elapsed :" + (after - before) + "nsecs.");
        return ret;
        
    }
    
}
