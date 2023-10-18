package com.bdp.ap.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * SecondDataSource의 디비를 사용하기 위한 annotation
 *
 * Mybatis의 Mapper interface생성시에 아래 annotation을 사용하면 SecondDataSource의 디비를 사용함.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ConnMapperSecond {

    String value() default "";
}
