package yuhao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分页基础bean
 * @author 喻浩
 * @create 2020-07-13-23:39
 */
@Data
public class PagingBean {
    //第几页
    private String current = "1";
    //每页展示的对象数量
    private String pageSize = "10";
}
