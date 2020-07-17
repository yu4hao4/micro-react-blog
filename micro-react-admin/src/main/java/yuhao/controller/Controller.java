package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.req.EmailReqDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.Article;
import yuhao.service.ILoginService;
import yuhao.service.IMailOpenService;

import java.util.List;

/**
 * @author 喻浩
 * @create 2020-07-12-14:04
 */
@RestController
public class Controller {
    @Autowired
    IMailOpenService mailOpenService;
    @Autowired
    ILoginService loginService;

    @PostMapping("/sendCode")
    public RespDTO<Object> sendCode(@RequestBody EmailReqDTO email){
        return  mailOpenService.sendEMail(email);
    }

    /**
     * 获得文章
     * @param articleReqDTO
     * @return
     */
    @PostMapping("/getArticle")
    public RespDTO<Object> searchArticleByFilterConditions(@RequestBody ArticleReqDTO articleReqDTO){
        return loginService.searchArticleByFilterConditions(articleReqDTO);
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/addArticle")
    public RespDTO<Object> addArticle(@RequestBody Article article){
        return loginService.addArticle(article);
    }

    /**
     * 删除文章
     * @param articles
     * @return
     */
    @PostMapping("/removeArticles")
    public RespDTO<Object> removeArticles(@RequestBody List<Article> articles){
        return loginService.removeArticles(articles);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @PostMapping("/updateArticle")
    public RespDTO<Object> updateArticle(@RequestBody Article article){
        return loginService.updateArticle(article);
    }
}
