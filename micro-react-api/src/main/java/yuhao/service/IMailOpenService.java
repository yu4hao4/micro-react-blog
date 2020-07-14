package yuhao.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import yuhao.dto.req.EmailReqDTO;
import yuhao.dto.resp.RespDTO;

/**
 * @author 喻浩
 * @create 2020-07-12-13:45
 */
@FeignClient("micro-react-mail")
public interface IMailOpenService {
    /**
     * 发送邮件
     * @param email
     * @return
     */
    @PostMapping("/sendEmail")
    RespDTO<Object> sendEMail(@RequestBody EmailReqDTO email);
}
