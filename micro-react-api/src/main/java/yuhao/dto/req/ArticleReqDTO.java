package yuhao.dto.req;

import lombok.*;
import lombok.experimental.Accessors;
import yuhao.bean.PagingBean;

import java.io.Serializable;

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
public class ArticleReqDTO extends PagingBean implements Serializable {
    // 文章id
    private String id;
    // 文章标题
    private String articleTitle;
}
