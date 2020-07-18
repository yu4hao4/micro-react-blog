package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.req.EmailReqDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.Article;
import yuhao.service.IService;
import yuhao.service.IMailOpenService;

import java.util.List;

/**
 * @author 喻浩
 * @create 2020-07-12-14:04
 */
@RestController
public class Controller {
    @Autowired
    IMailOpenService iMailOpenService;
    @Autowired
    IService iService;

    @PostMapping("/sendCode")
    public RespDTO<Object> sendCode(@RequestBody EmailReqDTO email){
        return  iMailOpenService.sendEMail(email);
    }

    /**
     * 获得文章
     * @param articleReqDTO
     * @return
     */
    @PostMapping("/getArticle")
    public RespDTO<Object> searchArticleByFilterConditions(@RequestBody ArticleReqDTO articleReqDTO){
        return iService.searchArticleByFilterConditions(articleReqDTO);
    }

    /**
     * 获得首页文章
     * @return
     */
    @GetMapping("/getIndexArticle")
    public RespDTO<Object> getIndexArticle(){
        return iService.searchArticleByFilterConditions(
                new ArticleReqDTO().setId("9").setArticleTitle("index")
        );
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/addArticle")
    public RespDTO<Object> addArticle(@RequestBody Article article){
        return iService.addArticle(article);
    }

    /**
     * 删除文章
     * @param articles
     * @return
     */
    @PostMapping("/removeArticles")
    public RespDTO<Object> removeArticles(@RequestBody List<Article> articles){
        return iService.removeArticles(articles);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @PostMapping("/updateArticle")
    public RespDTO<Object> updateArticle(@RequestBody Article article){
        return iService.updateArticle(article);
    }
}
