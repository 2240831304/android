package com.example.stock.eink.pioneer.stockadd;

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

    public SmallBoardStockAddReq(int id){
        reqStockCodeId = id;
        parser = new StockAddDataParse();
        dataSaver = new StockAddDataSave("smallboard");
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
        System.out.println("SmallBoardStockAddReq data="+ResultData);

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
            if(reqStockCodeId >= 20) {
                break;
            }

            String url = shenzhenurl + getFullStockCode();
            System.out.println("SmallBoardStockAddReq code url=" + url);

            setReqUrl(url);
            execute();

            reqStockCodeId += 1;
        }

        dataSaver.close();
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

