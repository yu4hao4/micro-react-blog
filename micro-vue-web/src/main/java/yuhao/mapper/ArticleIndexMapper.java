package yuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import yuhao.entity.ArticleIndex;

@Mapper
@Component
public interface ArticleIndexMapper {
    int insert(ArticleIndex record);

    int insertSelective(ArticleIndex record);

    ArticleIndex selectIndex();
}
