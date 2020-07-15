package com.example.stock.eink.pioneer.stockadd;

public class YearMaxMinPriceDataParse {
    private int maxprice = 0;
    private int minprice = 0;

    public void parse(String data){
        maxprice = 0;
        minprice = 0;

    }

    public int getMaxprice(){
        return maxprice;
    }

    public int getMinprice(){
        return minprice;
    }
}
