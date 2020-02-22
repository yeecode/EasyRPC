package com.github.yeecode.easyrpc.server.rpc;

import java.util.HashMap;
import java.util.Map;

public class ServiceGetter {
    private static Map<Class, Object> serviceMap = new HashMap<>();

    public static <T> T getServiceByClazz(Class<T> clazz) {
        try {
            if (serviceMap.containsKey(clazz)) {
                return (T) serviceMap.get(clazz);
            } else {
                T bean = clazz.newInstance();
                serviceMap.put(clazz, bean);
                return bean;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
