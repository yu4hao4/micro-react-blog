package yuhao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuhao.dto.ArticleReqDTO;
import yuhao.dto.ArticleRespDTO;
import yuhao.api.RespDTO;
import yuhao.mapper.ArticleMapper;
import yuhao.mapper.MyArticleMapper;
import yuhao.service.ILoginService;

import java.util.List;

/**
 * @author yuhao5
 * @date 2020-07-13
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    MyArticleMapper myArticleMapper;

    /**
     * 获得文章
     *
     * @param articleReqDTO
     * @return
     */
    @Override
    public RespDTO<Object> searchArticleByFilterConditions(ArticleReqDTO articleReqDTO) {
        List<ArticleRespDTO> list = myArticleMapper.searchArticleByFilterConditions(articleReqDTO);
        return RespDTO.commonly(201, list);
    }
}
