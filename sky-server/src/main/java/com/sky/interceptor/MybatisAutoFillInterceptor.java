package com.sky.interceptor;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enums.FieldFillType;
import com.sky.enums.FieldType;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Set;

/**
 * MybatisAutoFillInterceptor
 *
 * @author Chocolate
 * @since 2024/6/20 1:22
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisAutoFillInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        Object param = invocation.getArgs()[1];
        Class<?> paramClass = param.getClass();

        Field[] declaredFields = paramClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            AutoFill annotation = declaredField.getAnnotation(AutoFill.class);
            if (annotation != null) {
                FieldType type = annotation.type();
                FieldFillType fieldFillType = annotation.value();
                if (FieldFillType.INSERT.equals(fieldFillType) && SqlCommandType.INSERT.equals(sqlCommandType)) {
                    extracted(declaredField, type, param);
                }else if (FieldFillType.INSERT_UPDATE.equals(fieldFillType) && (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType))) {
                    extracted(declaredField, type, param);
                }

            }
        }
        return invocation.proceed();
    }

    private static void extracted(Field declaredField, FieldType type, Object param) throws IllegalAccessException {
        Long currentId = BaseContext.getCurrentId();
        LocalDateTime now = LocalDateTime.now();
        declaredField.setAccessible(true);

        if (FieldType.CURRENT_USER.equals(type)) {
            declaredField.set(param, currentId);
        } else if (FieldType.LOCAL_DATE_TIME.equals(type)) {
            declaredField.set(param, now);
        }
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
