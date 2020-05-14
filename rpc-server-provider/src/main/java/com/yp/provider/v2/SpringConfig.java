package com.yp.provider.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ex-yipeng
 * @version Id: SpringConfig.java, v 0.1 2020/5/14 11:15 ex-yipeng Exp $
 */
@Configuration
@ComponentScan(basePackages = "com.yp")
public class SpringConfig {

    @Bean
    public RpcServer gpRpcServer(){
        return new RpcServer(8080);
    }
}
