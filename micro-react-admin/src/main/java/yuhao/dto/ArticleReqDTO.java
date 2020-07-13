package yuhao.dto;

import lombok.*;
import lombok.experimental.Accessors;
import yuhao.api.PagingBean;

/**
 * @author 喻浩
 * @create 2020-07-13-23:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReqDTO extends PagingBean {
    // 文章id
    private String id;
    // 文章标题
    private String articleTitle;
}
