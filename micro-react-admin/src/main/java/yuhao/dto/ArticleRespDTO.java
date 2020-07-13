package yuhao.dto;

import lombok.*;
import lombok.experimental.Accessors;
import yuhao.entity.Article;

/**
 * @author 喻浩
 * @create 2020-07-13-23:45
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRespDTO extends Article {
}
