package yuhao.annotation;

import org.springframework.stereotype.Component;
import yuhao.pub.DataSources;

import java.lang.annotation.*;

/**
 * @author yuhao5
 * @date 2020-07-27
 */
@Component
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataSource {
    String value() default DataSources.MASTER;
}
