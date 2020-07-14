package com.example.stock.eink.pioneer.stockadd;

import java.io.UnsupportedEncodingException;


public class StockAddDataParse {


    public void parse(String metadata) {
        try {
            //System.out.println("4444444444444444444444444444="+metadata);
            String[] s1 = metadata.split("~");

            for(int i=0;i<s1.length;i++){

                //循环输出结果
                System.out.println(s1[i]);

            }
        }catch (Exception e){
            System.out.println("解析请求添加股票返回数据出错:" + e);
            e.printStackTrace();
        }

    }



}
