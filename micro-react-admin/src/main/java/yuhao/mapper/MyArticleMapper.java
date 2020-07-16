package yuhao.mapper;

import yuhao.annotation.Storage;
import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.resp.ArticleRespDTO;
import yuhao.entity.Article;

import java.util.List;

/**
 * @author 喻浩
 * @create 2020-07-13-23:43
 */

@Storage
public interface MyArticleMapper {
    List<ArticleRespDTO> searchArticleByFilterConditions(ArticleReqDTO articleReqDTO);

    int batchDeleteArticle(List<Article> articles);
}
