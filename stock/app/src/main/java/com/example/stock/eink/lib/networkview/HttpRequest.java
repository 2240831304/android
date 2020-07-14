package com.example.stock.eink.lib.networkview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public abstract class HttpRequest {

    public enum RequestType{
        GET,
        POST
    }

    private String url;
    private RequestType type = RequestType.GET;
    private String errorMeg;
    private String param = null;

    public void setReqUrl(String urlData){
        url = urlData;
    }

    public void setReqType(RequestType typeData){
        type = typeData;
    }

    public void setParam(String paramData){
        param = paramData;
    }


    public void execute(){
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader br = null;
        errorMeg = null;

        try {
            String reqUrl = url + "?" + param;
            URL urlTemp = new URL(reqUrl);
            //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
            conn = (HttpURLConnection)urlTemp.openConnection();
            if (type == RequestType.GET){
                conn.setRequestMethod("GET");
            }else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
            }
            //conn.setConnectTimeout(30000);
            //conn.setReadTimeout(4);
            //conn.setRequestProperty("Accept", "application/json");
            //下面函数设置头部
            fillHead(conn);

            //发送请求
            conn.connect();
            //通过conn取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()){
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null){
                    addData(line);
                }
            }else{
                errorMeg = "请求网络出错";
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }

        }catch (Exception e) {
            errorMeg = "请求网络出错";
            System.out.println("发送请求出现异常:" + e);
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
                if(is != null){
                    is.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            conn.disconnect();
        }

        parseData();

    }

    public abstract void fillHead(HttpURLConnection connecter);
    public abstract void addData(String data);
    public abstract void parseData();
    public abstract void startReq();
    public abstract void stopReq();


    public String getErrorMeg(){
        return errorMeg;
    }


}
