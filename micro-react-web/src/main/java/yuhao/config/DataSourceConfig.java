package yuhao.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置类
 * @author yuhao5
 * @date 2020-07-27
 */
@Configuration
@PropertySource("classpath:dynamic-db.properties")
public class DataSourceConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.datasource.master.driver-class-name}")
    String masterDriverClassName;
    @Value("${spring.datasource.master.jdbc-url}")
    String masterUrl;
    @Value("${spring.datasource.master.username}")
    String masterUsername;
    @Value("${spring.datasource.master.password}")
    String masterPassword;
    @Value("${spring.datasource.slave.driver-class-name}")
    String slaveDriverClassName;
    @Value("${spring.datasource.slave.jdbc-url}")
    String slaveUrl;
    @Value("${spring.datasource.slave.username}")
    String slaveUsername;
    @Value("${spring.datasource.slave.password}")
    String slavePassword;
    //数据源1
    @Bean(name = "master")
    public DataSource master() {
        DataSource dataSource = createDataSource(masterDriverClassName, masterUrl, masterUsername, masterPassword);
        logger.info("数据源注册成功------主库");
        return dataSource;
    }

    //数据源2
    @Bean(name = "slave")
    public DataSource slave() {
        DataSource dataSource = createDataSource(slaveDriverClassName, slaveUrl, slaveUsername, slavePassword);
        logger.info("数据源注册成功------从库");
        return dataSource;
    }

    public DataSource createDataSource(String driverClassName, String url, String username, String password){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //<!-- 配置初始化大小、最小、最大 -->
        dataSource.setInitialSize(20);
        dataSource.setMinIdle(20);
        dataSource.setMaxActive(200);
        //<!-- 配置从连接池获取连接等待超时的时间 -->
        dataSource.setMaxWait(20000);
        //<!-- 配置间隔多久启动一次DestroyThread，对连接池内的连接才进行一次检测，单位是毫秒。
        // 检测时:1.如果连接空闲并且超过minIdle以外的连接，如果空闲时间超过minEvictableIdleTimeMillis设置的值则直接物理关闭。2.在minIdle以内的不处理。-->
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //<!-- 配置一个连接在池中最大空闲时间，单位是毫秒 -->
        dataSource.setMinEvictableIdleTimeMillis(30000);
        //<!-- 设置从连接池获取连接时是否检查连接有效性，true时，每次都检查;false时，不检查 -->
        dataSource.setTestOnBorrow(false);
        //<!-- 设置往连接池归还连接时是否检查连接有效性，true时，每次都检查;false时，不检查 -->
        dataSource.setTestOnReturn(false);
        //<!-- 设置从连接池获取连接时是否检查连接有效性，true时，如果连接空闲时间超过minEvictableIdleTimeMillis进行检查，否则不检查;false时，不检查 -->
        dataSource.setTestWhileIdle(true);
        //<!-- 连接泄露检查，打开removeAbandoned功能 , 连接从连接池借出后，长时间不归还，将触发强制回连接。回收周期随timeBetweenEvictionRunsMillis进行，
        //如果连接为从连接池借出状态，并且未执行任何sql，并且从借出时间起已超过removeAbandonedTimeout时间，则强制归还连接到连接池中。 -->
        dataSource.setRemoveAbandoned(true);
        //<!-- 超时时间，秒 -->
        dataSource.setRemoveAbandonedTimeout(80);
        // <!-- 打开PSCache，并且指定每个连接上PSCache的大小，Oracle等支持游标的数据库，打开此开关，会以数量级提升性能,缓存sql数 -->
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(100);
        //<!-- 根据自身业务及事务大小来设置 -->
//        dataSource.setConnectionProperties(ConnectionProperties);
        return dataSource;
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("master", master());
        dsMap.put("slave", slave());

        dynamicDataSource.setTargetDataSources(dsMap);
        logger.info("动态数据源配置成功");
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactory")//SqlSessionFactory配置
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:transconf/sql-map-config.xml"));//po对象别名的xml文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));//mapper的xml文件
        return bean.getObject();
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
