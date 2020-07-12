package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yuhao.api.RespBean;
import yuhao.api.Email;
import yuhao.service.IMailService;

/**
 * @author 喻浩
 * @create 2020-07-12-12:53
 */
@RestController
public class MailController {
    @Autowired
    IMailService mailBusiService;

    @PostMapping("/sendEmail")
    public RespBean<Object> sendEMail(@RequestBody Email email){
        return mailBusiService.sendEMail(email);
    }
}
