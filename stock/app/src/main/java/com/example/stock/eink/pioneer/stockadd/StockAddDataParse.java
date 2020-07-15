package com.example.stock.eink.pioneer.stockadd;


public class StockAddDataParse {
    private String[] strArgs = null;

    public void parse(String metadata) {
        strArgs = null;
        try {
            //System.out.println("4444444444444444444444444444="+metadata);
            strArgs = metadata.split("~");

        }catch (Exception e){
            System.out.println("解析请求添加股票返回数据出错:" + e);
            e.printStackTrace();
        }

    }

    public String[] getParseData(){
        return strArgs;
    }


}
