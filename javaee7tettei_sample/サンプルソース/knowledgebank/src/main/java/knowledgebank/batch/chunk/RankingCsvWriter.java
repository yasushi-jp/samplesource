package knowledgebank.batch.chunk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import knowledgebank.batch.model.Ranking;
import knowledgebank.service.interceptor.LogInterceptor;

@Dependent
@Named("RankingCsvWriter")
@Interceptors(LogInterceptor.class)
public class RankingCsvWriter implements ItemWriter {
    @Inject
    private JobContext jctx;
    @Inject
    private StepContext sctx;    
    private String tempCsvFile;
    private BufferedWriter bw;

    @Override
    public void open(Serializable checkpoint) throws Exception {      
        // 出力ファイル名を取得
        tempCsvFile = (String)jctx.getProperties().get("tempCsvFile");
    //  tempCsvFile = (String)sctx.getProperties().get("tempCsvFile"); // KbDailyBatch は sctx から取得
        
        // 出力ファイルを open
        Path p = new File(tempCsvFile).toPath();
        bw = Files.newBufferedWriter(
                p, 
                StandardOpenOption.WRITE,  // 書き込みモード
                StandardOpenOption.CREATE, // ファイルが存在していなかったら作成
                // ファイルが存在する場合は追記(再実行時の考慮)
                StandardOpenOption.APPEND); 
            /**
          　　　// 開発中の期間など、１日に何度もジョブを実行する場合は、その都度ファイルの内容が
                // 消えるほうが都合がよいので、以下のようにしたほうが良いでしょう。
                p, 
                StandardOpenOption.WRITE,  // 書き込みモード
                StandardOpenOption.CREATE, // ファイルが存在していなかったら作成
                StandardOpenOption.TRUNCATE_EXISTING); // ファイルが存在する場合は内容を消去
             **/
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for(Object i : items){
            bw.write( ((Ranking)i).getId() 
             + ", " + ((Ranking)i).getName()
             + ", " + ((Ranking)i).getCount());
            bw.newLine();
        }    
        bw.flush();
    }

    @Override
    public void close() throws Exception {
        if(bw != null) bw.close();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
