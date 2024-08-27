package com.ankush.ReflectionUtils;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@AllArgsConstructor
public class JDBCGeneric<A extends Annotation> {
    Class<?> clazz;
    JdbcTemplate template;

    protected List<Field> getFields(Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getFields()).filter(x -> x.isAnnotationPresent(annotation)).toList();
    }

    protected List<Field> getFields(Class<? extends Annotation> annotation, Predicate<A> pred) {
        return Arrays.stream(clazz.getFields()).filter(x -> x.isAnnotationPresent(annotation)).filter(x -> pred.test((A) x.getAnnotation(annotation))).toList();
    }

    protected String PresentOr(String a, String b) {
        if (a == null) return b;
        return a.isEmpty() ? b : a;
    }

    public PreparedStatementSetter getArgs(Object o, List<Field> fields) throws IllegalAccessException {
        Object[] args = new Object[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            args[i] = fields.get(i).get(o);
        }
        return new ArgumentPreparedStatementSetter(args);
    }

}
