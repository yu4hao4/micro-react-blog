package yuhao.annotation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自己实现的 mybatis 相关注解，整合了 mapper 和 spring 的注解
 * @author yuhao5
 * @date 2020-07-16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Mapper
@Repository
public @interface Storage {
    @AliasFor(annotation = Repository.class, attribute = "value")
    String value() default "";
}
