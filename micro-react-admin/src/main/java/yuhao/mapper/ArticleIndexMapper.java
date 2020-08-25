package yuhao.mapper;

import yuhao.annotation.Storage;
import yuhao.dto.req.ArticleReqDTO;
import yuhao.dto.resp.ArticleRespDTO;
import yuhao.entity.ArticleIndex;

import java.util.List;

@Storage
public interface ArticleIndexMapper {
    int update(ArticleIndex record);

    ArticleRespDTO search();
}
