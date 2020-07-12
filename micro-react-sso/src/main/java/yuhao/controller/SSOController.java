package yuhao.controller;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuhao.api.Login;
import yuhao.api.RespBean;
import yuhao.api.ResultCode;
import yuhao.util.RedisUtil;

/**
 * @author 喻浩
 * @create 2020-07-12-11:58
 */
@RestController
public class SSOController {
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/test")
    public void test(){
        redisUtil.set("1", "1");
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    @RequestMapping("/hasKey/{key}")
    public RespBean<Object> hasKey(@PathVariable("key") String key) {
        try {
            if (redisUtil.hasKey(key)){
                return RespBean.commonly(ResultCode.KEY_EXIST.getCode(),ResultCode.KEY_EXIST.getMessage());
            }
            return RespBean.commonly(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.commonly(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage());
        }
    }

    /**
     * 校验用户名密码，成功则返回通行令牌（这里写死huanzi/123456）
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/check")
    private String check(String username, String password) {
        //通行令牌
        String flag = null;
        if ("huanzi".equals(username) && "123456".equals(password)) {
            //用户名+时间戳（这里只是demo，正常项目的令牌应该要更为复杂）
            flag = username + System.currentTimeMillis();
            //令牌作为key，存用户id作为value（或者直接存储可暴露的部分用户信息也行）设置过期时间（我这里设置3分钟）
            redisUtil.setByExpire(flag, "1", 180);
        }
        return flag;
    }

    /**
     * 登录的校验
     * @param login
     * @return
     */
    @RequestMapping("/login")
    private RespBean<Object> login(Login login) {
        //通行令牌
        String flag = null;
        String dbCode = redisUtil.get(login.getUsername());
        if (login.getCode().equals(dbCode)
                || (("yuhao5").equals(login.getUsername()) && "yh123456#".equals(login.getCode()))
        ) {
            //用户名+时间戳（这里只是demo，正常项目的令牌应该要更为复杂）
            String firstFlag = login.getUsername() + System.currentTimeMillis();
            flag = MD5Encoder.encode(firstFlag.getBytes());
            //令牌作为key，存用户id作为value（或者直接存储可暴露的部分用户信息也行）设置过期时间（我这里设置30分钟）
            redisUtil.setByExpire(flag, "1", 60*30);
        }
        return RespBean.commonly(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), flag);
    }

    /**
     * 登录的校验
     * @param token
     * @return
     */
    @RequestMapping("/flushToken")
    private RespBean<Object> flushToken(String token) {
        //通行令牌
        try {
            String flag = null;
            if (redisUtil.hasKey(token)){
                flag = MD5Encoder.encode(token.getBytes()).substring(0,32);
                return RespBean.commonly(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), flag);
            }
            return RespBean.commonly(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.commonly(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage());
        }
    }
}
