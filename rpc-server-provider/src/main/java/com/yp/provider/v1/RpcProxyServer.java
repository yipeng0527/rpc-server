package com.yp.provider.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ex-yipeng
 * @version Id: RpcProxyServer.java, v 0.1 2020/5/13 14:34 ex-yipeng Exp $
 */
public class RpcProxyServer {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service, int port) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket, service));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
