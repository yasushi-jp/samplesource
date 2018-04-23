package knowledgebank.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import knowledgebank.entity.Category;
import knowledgebank.entity.Knowledge;
import knowledgebank.entity.Knowledge_;

@Stateless
public class SearchKnowledgeFacade {

    @PersistenceContext(unitName = "knowledgebankPU")
    private EntityManager em;

    /**
     * 検索キーワードと検索対象のカテゴリをもとにナレッジを返す 検索条件がnullか空文字、空のコレクションだと条件に加えない
     *
     * @param searchText 検索キーワード
     * @param categories 検索対象のカテゴリ
     * @return
     */
    public List<Knowledge> searchKnowledge(String searchText, List<Category> categories) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        // クエリを作成
        CriteriaQuery<Knowledge> query = builder.createQuery(Knowledge.class);
        // ナレッジの情報を取得
        Root<Knowledge> knowledge = query.from(Knowledge.class);

        // Where句を作成
        Predicate where = builder.conjunction();

        if (searchText != null && !searchText.isEmpty()) {

            //タイトルで検索する
            Predicate titleCriteria = builder.like(knowledge.get(Knowledge_.title), "%" + searchText + "%");
            //内容で検索する
            Predicate descCriteria = builder.like(knowledge.get(Knowledge_.description), "%" + searchText + "%");
            //タイトルと内容の OR で検索する
            Predicate searchTextCriteria = builder.or(titleCriteria, descCriteria);

            // Where句に検索文字列の条件を追加
            where = builder.and(where, searchTextCriteria);
        }

        // カテゴリの指定があれば、カテゴリで絞る条件を作成する
        if (categories != null && categories.size() > 0) {

            Subquery<Knowledge> categoryQuery = query.subquery(Knowledge.class);
            Root subRootEntity = categoryQuery.from(Knowledge.class);
            categoryQuery.select(subRootEntity);

            // カテゴリのIDカラムの IN 句を作成する
            In categoryCriteria = builder.in(knowledge.get(Knowledge_.categoryList));
            categories.stream().forEach(c -> categoryCriteria.value(c.getId()));

            //カテゴリ条件をサブクエリの条件にする
            categoryQuery.where(categoryCriteria);

            //サブクエリをクエリに追加する
            where = builder.and(where, builder.exists(categoryQuery));
        }

        // 条件句をクエリに反映
        query = query.where(where);

        query.orderBy(builder.desc(knowledge.get(Knowledge_.lastCommentAt)));

        return em.createQuery(query).getResultList();
    }
}
