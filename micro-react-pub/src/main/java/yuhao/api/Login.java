package yuhao.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 喻浩
 * @create 2020-07-12-13:16
 */
@Data
public class Login implements Serializable {
    String username;
    String password;
    String code;
}
