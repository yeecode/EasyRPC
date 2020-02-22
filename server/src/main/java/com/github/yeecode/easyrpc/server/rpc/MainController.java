package com.github.yeecode.easyrpc.server.rpc;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    @RequestMapping("/")
    public Result rpcMain(String identifier, String methodName, String argTypes, String argValues) {
        try {
            Class clazz = Class.forName(identifier);
            if (clazz == null) {
                return Result.getFailResult("目标类不存在");
            }

            List<String> argTypeList = JSON.parseArray(argTypes, String.class);
            List<Class> argClassList = new ArrayList<>();
            for (String argType : argTypeList) {
                argClassList.add(Class.forName(argType));
            }
            Class[] argClassArray = new Class[argClassList.size()];
            argClassList.toArray(argClassArray);

            List<String> argValueStringList = JSON.parseArray(argValues, String.class);
            List<Object> argValueList = new ArrayList<>();
            for (int i = 0; i < argTypeList.size(); i++) {
                if (argClassList.get(i).equals(String.class)) {
                    argValueList.add(argValueStringList.get(i));
                } else {
                    argValueList.add(JSON.parseObject(argValueStringList.get(i), argClassList.get(i)));
                }
            }
            Object[] args = new Object[argValueList.size()];
            argValueList.toArray(args);

            Method method = clazz.getMethod(methodName, argClassArray);
            if (method == null) {
                return Result.getFailResult("目标方法不存在");
            }

            Object obj = ServiceGetter.getServiceByClazz(clazz);
            if (obj == null) {
                return Result.getFailResult("目标类的实例无法生成");
            }
            Object result = method.invoke(obj, args);
            return Result.getSuccessResult(method.getReturnType().getName(), JSON.toJSONString(result));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Result.getFailResult("服务端解析异常");
        }
    }
}