package com.example.stock.ui.stockpick;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.stock.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class StockPickModel extends ViewModel {

    public String[] stockTypeList;

    public StockPickModel() throws IOException {
        stockTypeList = new String[]{};

        

        //stockTypeList = tmpStr.split(",");

    }

    public String[] getStockTypeList(){
        return stockTypeList;
    }

    public String getStockTypeName(String key){
        return "Swsw";
    }


}
