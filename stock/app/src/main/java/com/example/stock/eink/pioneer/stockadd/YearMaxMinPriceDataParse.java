package com.example.stock.eink.pioneer.stockadd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class YearMaxMinPriceDataParse {
    private int maxprice = 0;
    private int minprice = 0;

    public void parse(String data){
        minprice = 0;
        maxprice = 0;

        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray dataArrayList = jsonObject.getJSONArray("hq");
            for(int i = 0;i < dataArrayList.length();i++){
                JSONArray dataArray = dataArrayList.getJSONArray(i);
                if(i == 0){
                    minprice = dataArray.getInt(5);
                    maxprice = dataArray.getInt(6);
                }else {
                    int tmp = dataArray.getInt(5);
                    if(minprice > tmp){
                        minprice = tmp;
                    }

                    tmp = dataArray.getInt(6);
                    if(maxprice < tmp){
                        maxprice = tmp;
                    }
                }
                //System.out.println(dataArray.get(0));
                //System.out.println(dataArrayList.get(i));
             }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public int getMaxprice(){
        return maxprice;
    }

    public int getMinprice(){
        return minprice;
    }
}
