package com.yp.provider.v1;

import com.yp.api.v1.IHelloService;
import com.yp.api.v1.User;

/**
 * @author ex-yipeng
 * @version Id: HelloServiceImpl.java, v 0.1 2020/5/13 14:34 ex-yipeng Exp $
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("request in sayHello:" + content);
        return "Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("request in saveUser:" + user);
        return "SUCCESS";
    }
}
