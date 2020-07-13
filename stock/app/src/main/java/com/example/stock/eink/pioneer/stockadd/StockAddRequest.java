package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;
import android.print.PrinterId;
import android.widget.TextView;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class StockAddRequest extends HttpRequest {

    private static final String shanghaiurl = "http://qt.gtimg.cn/q=s_sh";
    private static final String shenzhenurl = "http://qt.gtimg.cn/q=s_sz";

    private String ResultData; //请求网络返回的数据

    private String stockType = null;  //股票类型中文名字
    private String stockTypeEnName = null;  //股票类型英文名字

    private TextView addStockTextViewHint = null; //提示正在执行股票代码
    private int executeCodeId = 0;   //正在执行的股票编号
    private boolean isRequestState = false;
    private Context context;


    public StockAddRequest(String stockTypeCnName){
        stockType = stockTypeCnName;
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

    public int getExecuteCode(){
        return executeCodeId;
    }


    public void startRequest() {
        isRequestState = true;
    }


    public void setRequestState(boolean state){
        isRequestState = state;
    }

    private void obtainStockTypeEnName(){

    }

}
