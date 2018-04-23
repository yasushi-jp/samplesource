package knowledgebank.timer;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class LogTimer {

    /**
     * 時報 (9-17時に限定した出力)
     */
    @Schedule(dayOfWeek = "Mon-Fri", month = "*", hour = "9-17", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
    public void myTimer() {
        System.out.println("Timer event: " + new Date());
    }
    
    public void run(){
        try {
            InitialContext ic = new InitialContext();
            ic.lookup("aaa");
        } catch (NamingException ex) {
            Logger.getLogger(LogTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
