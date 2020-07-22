package com.example.stock.eink.pioneer.stockadd;

import android.os.Handler;
import android.os.Message;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class SmallBoardStockAddReq extends HttpRequest {

    private static final String shanghaiurl = "http://qt.gtimg.cn/q=s_sh";
    private static final String shenzhenurl = "http://qt.gtimg.cn/q=s_sz";

    private String ResultData = ""; //请求网络返回的数据
    private int reqStockCodeId = 0;
    private boolean requestState = false;
    private StockAddDataParse parser;

    private StockAddDataSave dataSaver;

    private Handler handlerOb;

    public SmallBoardStockAddReq(int id, Handler handlerPt){
        reqStockCodeId = id;
        parser = new StockAddDataParse();
        dataSaver = new StockAddDataSave("smallboard");
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
        //System.out.println("SmallBoardStockAddReq data="+ResultData);

        if(getErrorMeg().isEmpty()){
            parser.parse(ResultData);

            String strTmp = "sz" + getFullStockCode();
            dataSaver.save(parser.getParseData(),strTmp);
        }

        ResultData = "";
    }


    public void startReq() {
        requestState = true;

        while (requestState){
            if(reqStockCodeId >= 10000) {
                break;
            }

            String url = shenzhenurl + getFullStockCode();
            System.out.println("SmallBoardStockAddReq code url=" + url);

            Message message = new Message();
            message.obj=getFullStockCode();
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


    private String getFullStockCode(){
        String tmpStr = Integer.toString(reqStockCodeId);
        if(tmpStr.length() == 1){
            tmpStr = "00000" + tmpStr;
        }else if (tmpStr.length() == 2){
            tmpStr = "0000" + tmpStr;
        }else if (tmpStr.length() == 3){
            tmpStr = "000" + tmpStr;
        }else if (tmpStr.length() == 4){
            tmpStr = "00" + tmpStr;
        }
        return tmpStr;
    }
}

