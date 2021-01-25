package yuhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.resp.ArticleRespDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.Article;
import yuhao.entity.ArticleIndex;
import yuhao.mapper.ArticleIndexMapper;
import yuhao.mapper.ArticleMapper;
import yuhao.mapper.MyArticleMapper;
import yuhao.service.IService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author yuhao5
 * @date 2020-07-13
 */
@Service
public class ServiceImpl implements IService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    MyArticleMapper myArticleMapper;
    @Autowired
    ArticleIndexMapper articleIndexMapper;

    /**
     * 获得文章
     *
     * @param articleReqDTO
     * @return
     */
    @Override
    public RespDTO<Object> searchArticleByFilterConditions(ArticleReqDTO articleReqDTO) {
        PageHelper.startPage(Integer.parseInt(articleReqDTO.getCurrent())
                , Integer.parseInt(articleReqDTO.getPageSize()));

        List<ArticleRespDTO> list = myArticleMapper.searchArticleByFilterConditions(articleReqDTO);

        PageInfo<ArticleRespDTO> pageInfo = new PageInfo<ArticleRespDTO>(list);
        return RespDTO.commonly(201, pageInfo);
    }

    /**
     * 添加文章
     *
     * @param article
     * @author yuhao5
     * @date 2020-07-14
     */
    @Override
    public RespDTO<Object> addArticle(Article article) {
        if (articleMapper.insert(article) > 0){
            return RespDTO.ok("操作成功");
        }
        return RespDTO.error("请求失败");
    }

    /**
     * 更新文章
     *
     * @param article
     * @author yuhao5
     * @date 2020-07-14
     */
    @Override
    public RespDTO<Object> updateArticle(Article article) {
        if (articleMapper.updateByPrimaryKey(article) > 0){
            return RespDTO.ok("操作成功");
        }
        return RespDTO.error("请求失败");
    }

    /**
     * 删除文章
     *
     * @param articles
     * @author yuhao5
     * @date 2020-07-14
     */
    @Override
    public RespDTO<Object> removeArticles(List<Article> articles) {
        if (myArticleMapper.batchDeleteArticle(articles) > 0){
            return RespDTO.ok("操作成功");
        }
        return RespDTO.error("请求失败");
    }

    /**
     * 更新首页文章
     * @param article
     * @return
     */
    @Override
    public RespDTO<Object> updateIndexArticle(Article article) {
        ArticleIndex articleIndex = new ArticleIndex();
        articleIndex.setUpdateTime(LocalTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        articleIndex.setContent(article.getArticleContent());
        if (articleIndexMapper.update(articleIndex) > 0){
            return RespDTO.ok("操作成功");
        }
        return RespDTO.error("请求失败");
    }

    /**
     * 获得首页文章
     * time 2020/8/30
     *
     * @author yuhao5
     */
    @Override
    public RespDTO<Object> getIndexArticle() {
        ArticleRespDTO respDTO = articleIndexMapper.search();
        return RespDTO.commonly(201,respDTO);
    }
}
