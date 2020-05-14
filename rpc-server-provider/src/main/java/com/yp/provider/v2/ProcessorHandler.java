package com.yp.provider.v2;

import com.yp.api.v2.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author ex-yipeng
 * @version Id: ProcessorHandler.java, v 0.1 2020/5/13 14:34 ex-yipeng Exp $
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Map<String, Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
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
        //反射调用
        String serviceName = request.getClassName();
        String version = request.getVersion();
        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }

        Object service = handlerMap.get(serviceName);
        if (null == service) {
            throw new RuntimeException("service not found:" + serviceName);
        }

        Object[] args = request.getParameters();  //拿到客户端请求的参数
        Method method = null;
        if (null != args) {
            Class<?>[] types = new Class[args.length];  //获得每个参数的类型
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            Class clazz = Class.forName(request.getClassName()); //跟去请求的类进行加载
            method = clazz.getMethod(request.getMethodName(), types); //sayHello, saveUser找到这个类中的方法
        } else {
            Class clazz = Class.forName(request.getClassName()); //跟去请求的类进行加载
            method = clazz.getMethod(request.getMethodName());  //sayHello, saveUser找到这个类中的方法
        }
        Object result = method.invoke(service, args); //HelloServiceImpl 进行反射调用
        return result;
    }
}
