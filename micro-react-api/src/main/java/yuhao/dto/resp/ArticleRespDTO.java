package yuhao.dto.resp;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author 喻浩
 * @create 2020-07-13-23:45
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRespDTO {
    private String id;
}
