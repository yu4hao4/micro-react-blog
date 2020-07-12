package yuhao.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 喻浩
 * @create 2020-03-10-20:12
 */
@Slf4j
@Component
@PropertySource("classpath:mail.properties")
public class MailUtil {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.sender_email}")
    private String senderEmail;

    @Value("${mail.sender_name}")
    private String sendName;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.encoding}")
    private String encoding;

    /**
     * 发送验证码
     * @param receiverEmail
     * @param code
     * @return
     */
    public boolean sendCode(String receiverEmail,String code){
        log.info("准备发送验证码，接收人是 {} ，要发送的验证码是 {}", senderEmail,code);
        return sendEmail(receiverEmail,"验证码",code);
    }

    /**
     * 发邮件
     * @param receiverEmailAddress
     * @param content
     * @return
     */
    public boolean sendEmail(String receiverEmailAddress,String subject, String content){
        // 发送email对象
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字
            email.setHostName(host);
            // 端口号不为空时,用户自定义的端口号为SMTP发送服务器端口号
            if (!"".equals(port)) {
                email.setSSLOnConnect(true);
                email.setSslSmtpPort(port);
            }
            // 字符编码集的设置
            email.setCharset(encoding);
            // 收件人的邮箱
            email.addTo(receiverEmailAddress);
            // 发送人的邮箱
            email.setFrom(senderEmail, sendName);
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(username, password);
            // 要发送的邮件主题
            email.setSubject(subject);
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(content);
            // 发送
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
