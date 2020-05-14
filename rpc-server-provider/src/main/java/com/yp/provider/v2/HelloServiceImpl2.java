package com.yp.provider.v2;

import com.yp.api.v2.IHelloService;
import com.yp.api.v2.User;

/**
 * @author ex-yipeng
 * @version Id: HelloServiceImpl2.java, v 0.1 2020/5/14 11:32 ex-yipeng Exp $
 */
public class HelloServiceImpl2 implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("【v2.0】request in sayHello:" + content);
        return "【v2.0】Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V1.0】request in saveUser:" + user);
        return "【v2.0】SUCCESS";
    }
}
