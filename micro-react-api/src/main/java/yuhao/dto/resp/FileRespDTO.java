package yuhao.dto.resp;

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
public class FileRespDTO extends PagingBean implements Serializable {
    // 文件id
    private String id;
    // 文件展示的url
    private String url;
}
