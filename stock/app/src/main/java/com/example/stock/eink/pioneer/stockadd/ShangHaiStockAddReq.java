package com.example.stock.eink.pioneer.stockadd;

import android.widget.TextView;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class ShangHaiStockAddReq extends HttpRequest {

    private static final String shanghaiurl = "http://qt.gtimg.cn/q=s_sh";
    private static final String shenzhenurl = "http://qt.gtimg.cn/q=s_sz";

    private String ResultData; //请求网络返回的数据
    private TextView reqStockCodeHint;
    private int reqStockCodeId = 0;
    private boolean requestState = false;

    public ShangHaiStockAddReq(TextView view ,int id){
        reqStockCodeHint =  view;
        reqStockCodeId = id;
    }

    public void fillHead(HttpURLConnection connecter)
    {
        //connecter.setRequestProperty("Accept", "application/json");
    }

    public void addData(String data)
    {
        ResultData += data;
    }

    public void parseData()
    {
        System.out.println(ResultData);
    }


    public void startReq() {
        requestState = true;

        while (requestState){
            if(reqStockCodeId >= 610000) {
                reqStockCodeHint.setText("请求完成");
                break;
            }

            String url = shanghaiurl + Integer.toString(reqStockCodeId);
            System.out.println("ShangHaiStockAddReq code id=" + url);
            reqStockCodeHint.setText(Integer.toString(reqStockCodeId));

            setReqUrl(url);
            execute();

            reqStockCodeId += 1;
        }
    }

    public  void stopReq() {
        requestState = false;
        reqStockCodeHint.setText("暂停请求");
    }


}
