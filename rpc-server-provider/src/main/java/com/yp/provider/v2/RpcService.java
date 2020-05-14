package com.yp.provider.v2;

import org.springframework.stereotype.Component;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ex-yipeng
 * @version Id: RpcService.java, v 0.1 2020/5/14 11:24 ex-yipeng Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component  //被spring进行扫描
public @interface RpcService {

    Class<?> value();  //拿到服务的接口

    String version() default "";  //版本号
}
