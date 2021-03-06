package yuhao.service;

import org.springframework.web.bind.annotation.RequestBody;
import yuhao.dto.req.EmailReqDTO;
import yuhao.dto.resp.RespDTO;

/**
 * @author 喻浩
 * @create 2020-07-12-13:48
 */
public interface IMailService {
    /**
     * 发送邮件
     * @param email
     * @return
     */
    RespDTO<Object> sendEMail(@RequestBody EmailReqDTO email);
}
