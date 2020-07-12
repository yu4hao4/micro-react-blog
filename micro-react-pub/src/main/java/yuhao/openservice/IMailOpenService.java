package yuhao.openservice;

import com.netflix.hystrix.Hystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import yuhao.api.Email;
import yuhao.api.RespBean;

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
    RespBean<Object> sendEMail(@RequestBody Email email);
}
