package knowledgebank.rest.client;

import java.io.Console;
import java.util.List;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class KnowledgebankClient {

    public static void main(String args[]) {

        Client client = ClientBuilder.newClient();
        try {
            Console con = System.console();
            // ユーザIDとパスワードを入力させる
            String userId = con.readLine("ユーザIDは：");
            char[] passCharArr = con.readPassword("パスワードは：");
            String password = new String(passCharArr);

            // BASIC認証用クラスを登録
            client.register(HttpAuthenticationFeature.basic(userId, password));

            WebTarget webTarget = client.target("http://localhost:8080/knowledgebank/webresources/knowledge");
            webTarget = webTarget.path("search");

            String searchString = con.readLine("検索語は：");

            String categoryString = null;
            while (!(categoryString = con.readLine("カテゴリは：")).isEmpty()) {
                webTarget = webTarget.path(categoryString);
            }

            webTarget = webTarget.queryParam("query", searchString);
            List<RestKnowledge> restKnowledgeList = webTarget
                    .register(ClientSideLoggingFilter.class)
                    .request(MediaType.APPLICATION_XML)
                    .get(new GenericType<List<RestKnowledge>>() {
                    });

            System.out.println("===検索結果===");
            restKnowledgeList.stream().forEach(restKnowledge -> {
                System.out.println("タイトル：" + restKnowledge.getTitle());
                System.out.println("投稿者：" + restKnowledge.getAccountName());
                System.out.println("=============");
            });
            
        } catch (NotAuthorizedException e) {
            System.out.println("ユーザIDまたはパスワードが違います");
        } catch (NotFoundException e) {
            System.out.println("ナレッジが見つかりませんでした");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
    
    public List<RestKnowledge> callRetrieveKnowledge() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target("http://localhost:8080/knowledgebank/webresources/knowledge");
        webTarget = webTarget.path("search");
        List<RestKnowledge> restKnowledgeList = webTarget
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<List<RestKnowledge>>() {
                });
        client.close();
        return restKnowledgeList;
    }

    public List<RestKnowledge> callRetrieveKnowledge(String searchString) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target("http://localhost:8080/knowledgebank/webresources/knowledge");
        webTarget = webTarget.path("search");
        webTarget = webTarget.queryParam("query", searchString);
        List<RestKnowledge> restKnowledgeList = webTarget
                .register(ClientSideLoggingFilter.class)
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<List<RestKnowledge>>() {
                });
        client.close();
        return restKnowledgeList;
    }

    public void callCreateKnowledge(RestKnowledge restKnowledgee) {
        Client client = ClientBuilder.newClient();
        Response response
                = client.target("http://localhost:8080/knowledgebank/webresources/knowledge")
                .request()
                .post(Entity.entity(restKnowledgee, "application/xml; charset=UTF-8"), Response.class);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("作成しました。");
        } else {
            System.out.println("作成に失敗しました。");
        }
        response.close();
        client.close();
    }
}
