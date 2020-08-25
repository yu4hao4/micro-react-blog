package yuhao.service;

import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.Article;

import java.util.List;

/**
 * @author yuhao5
 * @date 2020-07-13
 */
public interface IService {
    /**
     * 获得文章
     * @param articleReqDTO
     * @return
     */
    RespDTO<Object> searchArticleByFilterConditions(ArticleReqDTO articleReqDTO);

    /**
     * 添加文章
     * @author yuhao5
     * @date 2020-07-14
     */
    RespDTO<Object> addArticle(Article article);

    /**
     * 更新文章
     * @author yuhao5
     * @date 2020-07-14
     */
    RespDTO<Object> updateArticle(Article article);

    /**
     * 删除文章
     * @author yuhao5
     * @date 2020-07-14
     */
    RespDTO<Object> removeArticles(List<Article> articles);

    /**
     * 更新首页文章
     * @param article
     * @return
     */
    RespDTO<Object> updateIndexArticle(Article article);
}
