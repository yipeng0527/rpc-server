package com.yp.provider.v1;

import com.yp.api.v1.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author ex-yipeng
 * @version Id: ProcessorHandler.java, v 0.1 2020/5/13 14:34 ex-yipeng Exp $
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) objectInputStream.readObject();

            //反射调用
            Object result = invoke(request);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != objectInputStream) {
                    objectInputStream.close();
                }
                if (null != objectOutputStream) {
                    objectOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Class clazz = Class.forName(request.getClassName());
        Method method = clazz.getMethod(request.getMethodName(), types);
        Object result = method.invoke(service, args);
        return result;
    }
}
