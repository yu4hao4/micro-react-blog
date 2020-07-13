package yuhao.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 喻浩
 * @create 2020-07-12-12:58
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmailReqDTO implements Serializable{
    String receiveEmail;
    String subject;
    String content;
}
