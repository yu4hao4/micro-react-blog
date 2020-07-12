package yuhao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import yuhao.interceptor.ResourceInterceptor;
import yuhao.util.FileManageUtil;

/**
 * 需要将拦截器加载到WebMvcConfigurerAdapter
 * @author 喻浩
 * @create 2020-03-01-14:42
 */
@Slf4j
@Component
class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ResourceInterceptor resourceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("执行 InterceptorRegistry ");
//        registry.addInterceptor(resourceInterceptor);
//        registry.addInterceptor(requestInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+ FileManageUtil.getFileStorePath());
        //大文件路径的匹配
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+ FileManageUtil.getBigFileStorePath());
        super.addResourceHandlers(registry);
    }

}
