package com.yp.provider.v2;

import com.yp.api.v2.IHelloService;
import com.yp.api.v2.User;

/**
 * @author ex-yipeng
 * @version Id: HelloServiceImpl.java, v 0.1 2020/5/14 11:32 ex-yipeng Exp $
 */
@RpcService(value = IHelloService.class,version = "V1.0")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("【V1.0】request in sayHello:" + content);
        return "【V1.0】Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V1.0】request in saveUser:" + user);
        return "【V1.0】SUCCESS";
    }
}
