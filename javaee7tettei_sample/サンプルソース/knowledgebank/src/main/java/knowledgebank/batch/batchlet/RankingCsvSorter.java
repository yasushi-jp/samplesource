package knowledgebank.batch.batchlet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import knowledgebank.service.interceptor.LogInterceptor;

@Dependent
@Named("RankingCsvSorter")
@Interceptors(LogInterceptor.class)
public class RankingCsvSorter implements Batchlet {
    @Inject
    private JobContext jctx;
    @Inject
    private StepContext sctx;
    
    private String tempCsvFile;
    private String sortedCsvFile;
    
    @Override
    public String process() throws Exception {
        // 入力ファイルからの読み込み
        tempCsvFile = (String) jctx.getProperties().get("tempCsvFile");
    //  tempCsvFile = (String) sctx.getProperties().get("tempCsvFile"); // KbDailyBatch は sctx から取得
        Path tempCsvPath = new File(tempCsvFile).toPath();
        List<String[]> sortArea = null;
        
        // ファイルを１行づつ読み取る Stream を生成し、
        // １行のデータをカンマ区切りで分割して String[] に変換したあと
        // 全行分のデータを String[] の List として保持
        try(Stream<String> stream = Files.lines(tempCsvPath)){ 
            sortArea = stream.map(s -> s.split(","))  
                       .collect(Collectors.toList()); 
        }
        catch(Exception e){
            sctx.setExitStatus("SORT-READ-ERROR");
            return "FAILED";
        }

        // String[] の3番目の要素を Long に変換し、この値をキーとして
        // List 全体を数値の昇順で並び替え
        sortArea.sort(Comparator.comparingLong( s -> Long.valueOf(s[2].trim()) )); 
        Collections.reverse(sortArea);  // List 全体を降順に並び替え

        sortedCsvFile = (String) sctx.getProperties().get("sortedCsvFile");
        try{
          Path sortedCsvPath = new File(sortedCsvFile).toPath();
          Files.write(sortedCsvPath, 
                      // String[] を、カンマを区切り文字とした単一の文字列に戻し、
                      // 上位10データに絞って List<String> に変換
                      sortArea.stream().map(s -> String.join(",", s))
                              .limit(10)
                              .collect(Collectors.toList()),
                                StandardOpenOption.WRITE,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch(Exception e){
            sctx.setExitStatus("SORT-WRITE-ERROR");
            return "FAILED";
        }
        sctx.setExitStatus("SORT-OK");
        return "COMPLETED";
    }

    @Override
    public void stop() throws Exception {
    	// 実装は特になし
    }
}
