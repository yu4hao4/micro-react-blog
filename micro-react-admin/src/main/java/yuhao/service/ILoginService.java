package yuhao.service;

import yuhao.dto.ArticleReqDTO;
import yuhao.api.RespDTO;

/**
 * @author yuhao5
 * @date 2020-07-13
 */
public interface ILoginService {
    /**
     * 获得文章
     * @param articleReqDTO
     * @return
     */
    RespDTO<Object> searchArticleByFilterConditions(ArticleReqDTO articleReqDTO);
}
