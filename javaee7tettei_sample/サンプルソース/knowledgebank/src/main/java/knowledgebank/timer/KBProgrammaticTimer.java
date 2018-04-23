package knowledgebank.timer;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 * タイマーサービスのサンプルコード
 * - アプリケーション実行と同時にスタート
 * - シングルトン
 */
@Startup
@Singleton
public class KBProgrammaticTimer {
    
    @Resource
    TimerService timerService;
    
    @PostConstruct
    public void initialize(){
        
        logger.log(Level.INFO, "--- TimerService is started. ---");
        ScheduleExpression tenSeconds = new ScheduleExpression();
        tenSeconds.second("*/10");
        tenSeconds.minute("*");
        tenSeconds.hour("*");
        timerService.createCalendarTimer(tenSeconds, new TimerConfig("*_*_*_*_*_*" + new Date(), false));
    }
    
    @Timeout
    public void timeout(Timer timer){
        logger.log(Level.INFO, "--- TimerService is ended. at " + new Date() + ", created at "+ timer.getInfo());
    }
    
    private static final Logger logger = Logger.getLogger(KBProgrammaticTimer.class.getName());
    
}
