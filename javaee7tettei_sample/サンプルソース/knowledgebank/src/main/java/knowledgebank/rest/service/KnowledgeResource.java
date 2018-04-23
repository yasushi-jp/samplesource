package knowledgebank.rest.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import knowledgebank.entity.Account;
import knowledgebank.entity.Category;
import knowledgebank.entity.Knowledge;
import knowledgebank.service.KnowledgeFacade;
import knowledgebank.service.SearchKnowledgeFacade;
import knowledgebank.rest.exception.KnowledgeNotFoundException;
import knowledgebank.service.AccountFacade;
import knowledgebank.service.CategoryFacade;

@RequestScoped
@Path("/knowledge")
public class KnowledgeResource {

    @EJB
    private SearchKnowledgeFacade searchKnowledgeFacade;

    @EJB
    private KnowledgeFacade knowledgeFacade;

    @EJB
    private AccountFacade accountFacade;

    @EJB
    private CategoryFacade categoryFacade;

    public KnowledgeResource() {
    }

    /**
     * GET http://[ホスト名]/knowledgebank/webresources/knowledge/search[/[検索カテゴリ1]/[検索カテゴリ2]/..][?query=[検索語]]
     * でナレッジを検索するリソースメソッド
     * ex) 
     * URI: http://localhost:8080/knowledgebank/webresources/knowledge/search/Java/DB?query=JavaEE
     * 
     * @param param リクエストのメタデータ
     * @return ヒットしたナレッジのリスト
     */
    @GET
    @Path("/search{category:.*}")
    @Produces({"application/xml", "application/json"})
    public List<RestKnowledge> retrieveKnowledge(@BeanParam KnowledgeParamBean param) {

        String searchString = param.getSearchString();

        List<PathSegment> categoryPathList = param.getCategoryPathList();       
        List<String> categoryStringList = categoryPathList.stream()
                .map(PathSegment::toString).collect(Collectors.toList());
        
        // カテゴリリストを取得
        List<Category> categoryList = categoryFacade.findAll();
        
        // カテゴリ文字列に合致したカテゴリオブジェクトのリストを生成
        List<Category> selectedCategoryList = categoryList.stream()
                .filter(category -> categoryStringList.contains(category.getName())).collect(Collectors.toList());

        List<Knowledge> resultKnowlegeList
                = searchKnowledgeFacade.searchKnowledge(searchString, selectedCategoryList);
        
        if (resultKnowlegeList.isEmpty()) {
            throw new KnowledgeNotFoundException("ナレッジが見付かりませんでした");
        }
        
        // JPA用KnowledgeエンティティのリストをREST用RestKnowledgeオブジェクトに変換
        List<RestKnowledge> resultRestKnowledgeList = resultKnowlegeList.stream()
                .map(knowledge -> new RestKnowledge(knowledge))
                .collect(Collectors.toList());
        return resultRestKnowledgeList;
    }

    /**
     * POST http://[ホスト名]/knowledgebank/webresources/knowledge
     * でナレッジ作成するリソースメソッド
     * ex)
     * Content-Typeヘッダ: application/json; charset=UTF-8
     * メッセージボディ:
     * {
     * "title":"POSTテスト", 
     * "description": "POSTのテスト。titleとdescriptionのみPOSTします。" 
     * } 
     *
     * @param param リクエストのメタデータ
     * @param restKnowledge リクエストのメッセージボディ
     * @return locationヘッダに新規作成ナレッジのURI、メッセージボディに新規作成ナレッジを記述したレスポンス
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response createKnowledge(@BeanParam KnowledgeParamBean param, @Valid RestKnowledge restKnowledge) {

        Account account = accountFacade.findByUserId(param.getSecurityContext().getUserPrincipal().getName());
        Knowledge knowledge = new Knowledge();
        knowledge.setTitle(restKnowledge.getTitle());
        knowledge.setDescription(restKnowledge.getDescription());
        knowledge.setAccount(account);
        knowledgeFacade.create(knowledge);

        Long knowledgeId = knowledge.getId();

        restKnowledge.setId(knowledgeId);
        restKnowledge.setAccountName(account.getName());

        // リクエストURIの絶対パスをUriBuilder型で取得
        UriBuilder uriBuilder = param.getUriInfo().getRequestUriBuilder();
        // リクエストURIの後続パスにナレッジのIDを付加して作成ナレッジへアクセスするURIを生成
        URI newUri = uriBuilder.path(knowledgeId.toString()).build();
        
        return Response.created(newUri).entity(restKnowledge).build();
    }

    /**
     * PUT http://[ホスト名]/knowledgebank/webresources/knowledge/[ナレッジID]
     * でidのナレッジを更新するリソースメソッド
     * ex)
     * URI: http://localhost:8080/knowledgebank/webresources/knowledge/123
     * Content-Typeヘッダ: application/json; charset=UTF-8
     * メッセージボディ:
     * }
     * "title":"PUTテスト",
     * "description": "PUTのテスト。titleとdescriptionのみPUTしてみます。" 
     * } 
     * 
     * @param param リクエストのメタデータ
     * @param restKnowledge リクエストのメッセージボディ
     */
    @PUT
    @Path("/{id}")
    @Consumes({"application/xml", "application/json"})
    public void editKnowledge(@BeanParam KnowledgeParamBean param, @Valid RestKnowledge restKnowledge) {
        
        Account account = accountFacade.findByUserId(param.getSecurityContext().getUserPrincipal().getName());
        Knowledge knowledge = null;     
        
        try {
            knowledge = knowledgeFacade.find(param.getId());
        } catch (EJBException e) {
        }
        
        if (knowledge != null) {         
            if (account.getId() != knowledge.getAccount().getId()) {
                throw new ForbiddenException("ナレッジの投稿者しかナレッジの更新ができません。");
            }
            // IDが存在する場合更新
            knowledge.setTitle(restKnowledge.getTitle());
            knowledge.setDescription(restKnowledge.getDescription());
            knowledgeFacade.edit(knowledge);
        } else {
            // IDが存在しない場合新規作成
            knowledge = new Knowledge();
            knowledge.setId(param.getId());
            knowledge.setTitle(restKnowledge.getTitle());
            knowledge.setDescription(restKnowledge.getDescription());
            knowledge.setAccount(account);
            knowledgeFacade.create(knowledge);
        }
    }

    /**
     * DELETE http://[ホスト名]/knowledgebank/webresources/knowledge/{ナレッジID}
     * でidのナレッジを削除するリソースメソッド
     * ex)
     * URI: http://localhost:8080/knowledgebank/webresources/knowledge/123
     * 
     * @param param リクエストのメタデータ
     */
    @DELETE
    @Path("/{id}")
    public void removeKnowledge(@BeanParam KnowledgeParamBean param) {
        
        Account account = accountFacade.findByUserId(param.getSecurityContext().getUserPrincipal().getName());
        Knowledge knowledge = null;
        
        try {
            knowledge = knowledgeFacade.find(param.getId());
        } catch (EJBException e) {
            throw new KnowledgeNotFoundException("指定したIDを持つナレッジがありません。");
        }
        
        if (account.getId() != knowledge.getAccount().getId()) {
            throw new ForbiddenException("ナレッジの投稿者しかナレッジの削除ができません。");
        }
        knowledgeFacade.remove(knowledge);
    }
}
