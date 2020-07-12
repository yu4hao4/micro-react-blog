package yuhao.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 喻浩
 * @create 2020-07-12-12:58
 */
@Data
public class Email implements Serializable{
    String receiveEmail;
    String subject;
    String content;
}
