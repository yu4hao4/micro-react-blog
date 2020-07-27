package yuhao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuhao.annotation.DataSource;
import yuhao.entity.TestPage;
import yuhao.mapper.TestPageMapper;
import yuhao.pub.DataSources;

/**
 * @author yuhao5
 * @date 2020-07-27
 */
@Service
public class TestServiceImpl {
    @Autowired
    TestPageMapper testPageMapper;

    @DataSource(DataSources.SLAVE)
    public String test1(){
        TestPage testPage = testPageMapper.selectByPrimaryKey(1);
        System.out.println(testPage);
        return testPage.toString();
    }


    public void test2() {
        TestPage testPage = testPageMapper.selectByPrimaryKey(2);
        System.out.println(testPage.toString());
    }
}
