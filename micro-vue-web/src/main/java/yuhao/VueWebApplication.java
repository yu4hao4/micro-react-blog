package yuhao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.ArticleIndex;
import yuhao.mapper.ArticleIndexMapper;

/**
 * @author 喻浩
 * @create 2020-08-10-11:20
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class VueWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueWebApplication.class, args);
    }

    @Autowired
    ArticleIndexMapper articleIndexMapper;
    /**
     * 获得首页文章
     * @return
     */
    @GetMapping("/getIndexArticle")
    public RespDTO<ArticleIndex> getIndexArticle(){
        ArticleIndex articleIndex = articleIndexMapper.selectIndex();
        if (articleIndex != null){
            return RespDTO.commonly(201, "ok",articleIndex);
        }
        return RespDTO.error("请求错误，请稍后再试");
    }
}
