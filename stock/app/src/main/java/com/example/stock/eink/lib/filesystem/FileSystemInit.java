package com.example.stock.eink.lib.filesystem;

import android.os.Environment;

import java.io.File;

public class FileSystemInit {

    public FileSystemInit(){
    }

    public void startInit(){
        Init();
    }

    private void Init()
    {
        //System.out.println(Environment.getExternalStorageDirectory().getPath());
        String filepath = Environment.getExternalStorageDirectory().getPath() + "/" + "huibao";
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String sqlitePath = filepath + "/" + "sqlite";
        file = new File(sqlitePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
