package yuhao.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuhao.api.EmailReqDTO;
import yuhao.api.RespDTO;
import yuhao.api.ResultCode;
import yuhao.service.IMailService;
import yuhao.util.MailUtil;
import yuhao.util.RedisUtil;

/**
 * @author 喻浩
 * @create 2020-07-12-13:49
 */
@Slf4j
@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    MailUtil mailUtil;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 发送邮件
     *
     * @param email
     * @return
     */
    @Override
    public RespDTO<Object> sendEMail(EmailReqDTO email) {
//        mailUtil.sendEmail("1175827603@qq.com","验证码","111");
//        mailUtil.sendEmail("982276133@qq.com","测试","11111");
        if (mailUtil.sendEmail(email.getReceiveEmail(),email.getSubject(),email.getContent())){
            log.info("邮件发送成功 ---- 接收者--{} --- 主题是--{}", email.getReceiveEmail(), email.getSubject());
            return RespDTO.commonly(ResultCode.EMAIL_SEND_OK.getCode(), ResultCode.EMAIL_SEND_OK.getMessage(),"1111");
        }

        return RespDTO.commonly(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
    }
}
