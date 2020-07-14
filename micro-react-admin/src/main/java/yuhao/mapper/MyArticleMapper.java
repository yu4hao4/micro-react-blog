package yuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.resp.ArticleRespDTO;
import yuhao.entity.Article;

import java.util.List;

/**
 * @author 喻浩
 * @create 2020-07-13-23:43
 */
@Mapper
@Component
public interface MyArticleMapper {
    List<ArticleRespDTO> searchArticleByFilterConditions(ArticleReqDTO articleReqDTO);

    int batchDeleteArticle(List<Article> articles);
}
