package com.example.stock.eink.pioneer.stockadd;

import android.os.Handler;
import android.os.Message;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class StockNewestDataReq extends HttpRequest {

    private static final String requrl = "http://qt.gtimg.cn/q=s_";

    private String ResultData;
    private int ExecuteId = 1;
    private int MaxExecuteId;
    private String reqStockCode;

    private StockNewestDataSave saver;
    private boolean RequestState = false;
    private StockAddDataParse Parser;

    private Handler handler;


    public StockNewestDataReq(Handler handlerPt){
        saver = new StockNewestDataSave();
        Parser = new StockAddDataParse();

        handler = handlerPt;
    }


    public void fillHead(HttpURLConnection connecter)
    {

    }

    public void addData(String data)
    {
        ResultData += data;
    }

    public void parseData()
    {
        if(getErrorMeg().isEmpty()){
            Parser.parse(ResultData);
            saver.save(Parser.getParseData(),reqStockCode);
        }
        ResultData = "";
    }

    public void startReq()
    {
        InitData();
        RequestState = true;

        new Thread(new Runnable(){
            @Override
            public void run() {
                executeReq();
            }
        }).start();

    }

    public void stopReq()
    {
        RequestState = false;
        saver.close();
    }

    private void InitData(){
        saver.setId();
        ExecuteId = saver.getMinId();
        MaxExecuteId = saver.getMaxId();
    }

    private void executeReq(){

        while (RequestState){
            if(ExecuteId > MaxExecuteId){
                break;
            }

            reqStockCode = saver.getStockCode(ExecuteId);
            if(reqStockCode.isEmpty()){
                ExecuteId += 1;
                continue;
            }

            //System.out.println("StockNewestDataReq executeReq() stockcode=="+reqStockCode);
            Message message = new Message();
            message.obj=reqStockCode;
            handler.sendMessage(message);

            String url = requrl + reqStockCode;
            setReqUrl(url);
            execute();

            ExecuteId += 1;
        }

        Message message = new Message();
        message.obj="finished";
        handler.sendMessage(message);

    }


}
