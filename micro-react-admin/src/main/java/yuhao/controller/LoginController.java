package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yuhao.dto.ArticleReqDTO;
import yuhao.api.EmailReqDTO;
import yuhao.api.RespDTO;
import yuhao.service.ILoginService;
import yuhao.service.IMailOpenService;

/**
 * @author 喻浩
 * @create 2020-07-12-14:04
 */
@RestController
public class LoginController {
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
}
