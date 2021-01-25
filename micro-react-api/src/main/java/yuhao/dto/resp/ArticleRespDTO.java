package yuhao.dto.resp;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 喻浩
 * @create 2020-07-13-23:45
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRespDTO implements Serializable {
    private String id;

    private String publishTime;

    private String publishUser;

    private String articleTitle;

    private String articleContent;

    private Integer articleBrowse;

    private Integer updateTime;
}
