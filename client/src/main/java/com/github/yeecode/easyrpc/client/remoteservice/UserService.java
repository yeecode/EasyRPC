package com.github.yeecode.easyrpc.client.remoteservice;


import com.github.yeecode.easyrpc.client.rpc.RemoteClass;

@RemoteClass("com.github.yeecode.easyrpc.server.service.UserService")
public interface UserService {
    Integer getUserCount();

    String getUserInfo(Integer id);

    Integer addUser(String name, String email, Integer age, Integer sex, String schoolName);
}