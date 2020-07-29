package yuhao.dto.req;

import lombok.*;
import lombok.experimental.Accessors;
import yuhao.bean.PagingBean;

import java.io.Serializable;

/**
 * @author yuhao5
 * @date 2020-07-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileReqDTO extends PagingBean implements Serializable {
    // 文件名
    private String fileName;
}
