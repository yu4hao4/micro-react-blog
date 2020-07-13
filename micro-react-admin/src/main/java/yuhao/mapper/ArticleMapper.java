package yuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import yuhao.entity.Article;

@Mapper
@Component
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}
