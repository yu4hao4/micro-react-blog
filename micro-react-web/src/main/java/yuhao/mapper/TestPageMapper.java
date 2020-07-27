package yuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import yuhao.entity.TestPage;

@Mapper
@Component
public interface TestPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestPage record);

    int insertSelective(TestPage record);

    TestPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestPage record);

    int updateByPrimaryKey(TestPage record);
}
