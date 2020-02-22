package com.github.yeecode.easyrpc.client.rpc;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

    public static synchronized Result callRemoteService(String identifier, String methodName, String argTypes, String argValues) {
        try {
            List<NameValuePair> paramsList = new ArrayList<>();
            paramsList.add(new BasicNameValuePair("identifier", identifier));
            paramsList.add(new BasicNameValuePair("methodName", methodName));
            paramsList.add(new BasicNameValuePair("argTypes", argTypes));
            paramsList.add(new BasicNameValuePair("argValues", argValues));
            String result = sendPost("http://127.0.0.1:12311/", paramsList);
            return JSON.parseObject(result, Result.class);
        } catch (Exception ex) {
            return Result.getFailResult("触发远程调用失败");
        }
    }

    private static synchronized String sendPost(String url, List<NameValuePair> nameValuePairList) throws Exception {
        CloseableHttpResponse response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            post.setEntity(entity);
            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}
