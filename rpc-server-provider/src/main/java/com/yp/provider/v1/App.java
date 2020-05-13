package com.yp.provider.v1;

import com.yp.api.v1.IHelloService;

/**
 * @author ex-yipeng
 * @version Id: App.java, v 0.1 2020/5/13 14:34 ex-yipeng Exp $
 */
public class App {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();

        RpcProxyServer rpcProxyServer = new RpcProxyServer();

        rpcProxyServer.publisher(helloService, 8080);
    }
}
