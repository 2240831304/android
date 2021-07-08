package com.example.stock.eink.pioneer.stockadd;

import android.os.Handler;
import android.os.Message;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class GemStockAddReq extends HttpRequest{
    private static final String shanghaiurl = "http://qt.gtimg.cn/q=s_sh";
    private static final String shenzhenurl = "http://qt.gtimg.cn/q=s_sz";

    private String ResultData = ""; //请求网络返回的数据
    private int reqStockCodeId = 0;
    private boolean requestState = false;
    private StockAddDataParse parser;

    private StockAddDataSave dataSaver;

    private Handler handlerOb;

    public GemStockAddReq(int id, Handler handlerPt){
        reqStockCodeId = id;
        parser = new StockAddDataParse();
        dataSaver = new StockAddDataSave("chinext");
        handlerOb = handlerPt;
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

        //System.out.println("ShangHaiStockAddReq start  parse data!!!");
        System.out.println("GemStockAddReq data="+ResultData);

        if(getErrorMeg().isEmpty()){
            parser.parse(ResultData);

            String strTmp = "sz" + Integer.toString(reqStockCodeId);
            dataSaver.save(parser.getParseData(),strTmp);
        }

        ResultData = "";
    }


    public void startReq() {
        requestState = true;

        while (requestState){
            if(reqStockCodeId >= 400000) {
                break;
            }

            String url = shenzhenurl + Integer.toString(reqStockCodeId);
            System.out.println("ShangHaiStockAddReq code id=" + url);

            Message message = new Message();
            message.obj=Integer.toString(reqStockCodeId);
            handlerOb.sendMessage(message);

            setReqUrl(url);
            execute();

            reqStockCodeId += 1;
        }

        dataSaver.close();

        Message message = new Message();
        message.obj="finished";
        handlerOb.sendMessage(message);
    }

    public void stopReq() {
        requestState = false;
    }

}
