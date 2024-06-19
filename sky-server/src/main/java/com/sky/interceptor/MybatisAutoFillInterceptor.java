package com.sky.interceptor;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Set;

/**
 * MybatisAutoFillInterceptor
 *
 * @author Chocolate
 * @since 2024/6/20 1:22
 */
@Component
@Intercepts({@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})})
public class MybatisAutoFillInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        ParameterHandler parameterHandler= (ParameterHandler)invocation.getTarget();
//        Object parameterObject = parameterHandler.getParameterObject();
//
//        Class<?> aClass = parameterObject.getClass();
//        if (aClass == MapperMethod.ParamMap.class) {
//            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
//            Set set = paramMap.keySet();
//            for (Object key : set) {
//                MappedStatement mappedStatement = paramMap.get(key);
//            }
//        }
//        Field createUser = aClass.getDeclaredField("createUser");
//        createUser.setAccessible(true);
//        if (createUser.get(parameterObject) == null) {
//            createUser.set(parameterObject, "admin");
//        }
        // todo  反射插入crt upd
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
