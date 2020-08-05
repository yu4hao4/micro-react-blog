package yuhao.mapper;

import yuhao.annotation.Storage;
import yuhao.entity.Image;

@Storage
public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}
