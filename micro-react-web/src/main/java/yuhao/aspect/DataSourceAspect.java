package yuhao.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import yuhao.annotation.DataSource;

import java.lang.reflect.Method;


/**
 * 数据源AOP切入点定义
 *
 * @author: 郑国勇
 * @date: 2019-12-3 10:41
 */
@Component
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
public class DataSourceAspect {
    //切入点在dao层的方法上，配置aop的切入点
    @Pointcut("execution( * yuhao.service.impl..*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Class<?> target1 = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target1.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target1, signature.getMethod());
    }
    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解-也就是使用定义类上面的注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSouce(source.value());
            }
            // 方法注解可以覆盖类型注解-也就是使用方法上的注解去替换类上面的注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSouce(source.value());
                System.out.println("方法上的注解"+source.value());
            }
        } catch (Exception e) {
            System.out.println(clazz + ":" + e.getMessage());
        }
    }


    //执行完切面后，清空设置的数据源名称
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.removeDataSource();
    }
}
