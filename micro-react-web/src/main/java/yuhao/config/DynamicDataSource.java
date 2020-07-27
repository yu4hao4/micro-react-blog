package yuhao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import yuhao.aspect.DynamicDataSourceHolder;

/**
 * @Description: 动态数据源 Dynamic data source
 * @author yuhao5
 * @date 2020-07-27
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //数据源路由器，获取最终被执行的数据源
    //The data source router, which gets the data source that is finally executed
    @Override
    protected Object determineCurrentLookupKey() {
        //从本地线程中获取最终被执行的数据源名称
        //Gets the name of the data source to be executed from the local thread
        String dataSource = DynamicDataSourceHolder.getDataSource();
        logger.error("------------------当前数据源:{}---------------------",dataSource);
        return dataSource;
    }
}
