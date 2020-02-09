package com.cloud.gateway.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient2Util {

    private static final Integer timeout = 20000;

    /**
     * get请求没有参数
     */
    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> param) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        StringBuilder urlBuilder = new StringBuilder(url);
        if (param != null) {
            StringBuilder paramBuilder = new StringBuilder();
            for (Map.Entry entry : param.entrySet()) {
                paramBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            urlBuilder.append("?").append(paramBuilder.substring(1));
        }
        HttpGet httpGet = new HttpGet(urlBuilder.toString());

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();
        // 设置请求超时时间
        httpGet.setConfig(requestConfig);
        try {
            response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(String url) {
        return post(url, null);
    }

    public static String post(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;

        try {
            HttpPost httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            // 设置请求超时时间
            httpPost.setConfig(requestConfig);
            // 添加头信息
            // httpPost.addHeader("Content-Type","application/json");
            if (param != null) {
                List<NameValuePair> params = new ArrayList<>();
                param.forEach((k, v) -> {
                    params.add(new BasicNameValuePair(k, v));
                });

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
                httpPost.setEntity(formEntity);
            }

            response = httpClient.execute(httpPost);

            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static String postForJson(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;

        try {
            HttpPost httpPost = new HttpPost(url);


            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                                                                .setSocketTimeout(timeout)
                                                                .setConnectionRequestTimeout(timeout)
                                                                .build();
            // 设置请求超时时间
            httpPost.setConfig(requestConfig);

            // 添加头信息
            // httpPost.addHeader("Content-Type","application/json");
            if (param != null) {

                String jsonParam = JSON.toJSONString(param);
                StringEntity jsonEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);

                httpPost.setEntity(jsonEntity);
            }

            response = httpClient.execute(httpPost);

            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        Map<String, String> param = new HashMap<>();
        param.put("userName", "admin");
        param.put("pwd", "123qwe");

        System.out.println(postForJson("http://192.168.103.195:8080/app/cloud/login", param));
        // System.out.println(post("http://192.168.103.195:8080/app/cloud/login", param));
        // System.out.print(get("http://192.168.103.195:8080/app/cloud/userInfo", param));
    }

}
