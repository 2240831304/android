package com.example.stock.eink.lib.filesystem;

import android.os.Environment;

public class FileSystemManager {
    private String rootPath = null;

    public FileSystemManager(){
        setRootFilePath();
    }


    private void setRootFilePath(){
        rootPath = Environment.getExternalStorageDirectory().getPath();
    }

    public String getStockFilePath(){
        String path = rootPath + "/huibao/sqlite/";

        return path;
    }
}
