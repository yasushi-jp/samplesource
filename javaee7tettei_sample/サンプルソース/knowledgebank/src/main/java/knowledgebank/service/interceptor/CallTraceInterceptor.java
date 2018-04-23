package knowledgebank.service.interceptor;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class CallTraceInterceptor {

    private final SimpleDateFormat sdf;
    private final Logger logger = Logger.getLogger(CallTraceInterceptor.class.getName());
    private static final int DIVISOR = 1000;
    private static final NumberFormat nf = NumberFormat.getInstance();      

    public CallTraceInterceptor() {
        sdf = new SimpleDateFormat("yyyy/MM/dd_hh:mm:ss.SSS");
    }
    
    @AroundInvoke
    private Object trace(InvocationContext ic){
        String clazz = ic.getTarget().getClass().getSuperclass().getSimpleName();
        String method = ic.getMethod().getName();

        String beforeMsg = String.format(" START %s#%s().", clazz, method);
        logger.log(Level.INFO, sdf.format(new Date()) + beforeMsg);

        Object result = null;
        long t1 = System.nanoTime();
        try {
            result = ic.proceed();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        long t2 = System.nanoTime();
        String afterMsg = String.format(" END   %s#%s() - %sμsec.", clazz, method, nf.format((t2 - t1) / DIVISOR));
        logger.log(Level.INFO, sdf.format(new Date()) + afterMsg);
        
        return result;
    }
}
