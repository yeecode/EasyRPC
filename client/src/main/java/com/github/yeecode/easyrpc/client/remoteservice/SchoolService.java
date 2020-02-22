package com.github.yeecode.easyrpc.client.remoteservice;


import com.github.yeecode.easyrpc.client.rpc.RemoteClass;

@RemoteClass("com.github.yeecode.easyrpc.server.service.SchoolService")
public interface SchoolService {
    String querySchoolName(Integer id);
}
