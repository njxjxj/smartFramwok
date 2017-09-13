package org.smartXj.framwork.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Request {
    /**
     * 请求的方法
     */
    private String requestMethod;

    /**
     * 请求的路径
     */
    private String requestPath;


    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    @Override
    //如果hashCode取决于该class的所有filed时需要使用反射机制来产生一个hashCode。
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    //如果两个对象相等当且仅当每个属性值都相等
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }
}
