package com.yp.api.v1;

/**
 * @author ex-yipeng
 * @version Id: IHelloService.java, v 0.1 2020/5/13 14:18 ex-yipeng Exp $
 */
public interface IHelloService {

    //
    String sayHello(String content);

    /**
     * 保存用户
     * @param user
     * @return
     */
    String saveUser(User user);
}
