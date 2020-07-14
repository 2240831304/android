package com.example.stock.eink.pioneer.stockadd;

import java.io.UnsupportedEncodingException;


public class StockAddDataParse {


    public void parse(String metadata) {
        try {
            byte[] bs = metadata.getBytes();
            String dede = new String(bs, "GBK");
            System.out.println("4444444444444444444444444444="+dede);
        }catch (Exception e){
            System.out.println("解析请求添加股票返回数据出错:" + e);
            e.printStackTrace();
        }

    }
    


}
