package com.yp.api.v2;

import java.io.Serializable;

/**
 * @author ex-yipeng
 * @version Id: RpcRequest.java, v 0.1 2020/5/13 14:18 ex-yipeng Exp $
 */
public class RpcRequest implements Serializable {

    private String className;
    private String methodName;
    private Object[] parameters;

    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
