package com.example.stock;

import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;

import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemInit;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        FileSystemInit fileInit = new FileSystemInit();
        fileInit.startInit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);

        String currentapiVersion = android.os.Build.VERSION.RELEASE;
        System.out.println("current system version######======"+currentapiVersion);
        boolean stateIS = compareVersion(currentapiVersion,"7");
        System.out.println(stateIS);
        if(stateIS ){
            System.out.println("cur androoid version > 7");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android
                        .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            String rootFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + "huibao";
            String stockfilepath = rootFilePath + "/sqlite/stock.db";
            int stockVersion = ConfigParameter.StockDatabaseVersion;

            try {
                MySqliteOpenHelper oh = new MySqliteOpenHelper(this, stockfilepath,
                        null, stockVersion);
                SQLiteDatabase db = oh.getWritableDatabase();
                db.close();
            }catch (Exception e){
                System.out.println("We got unexpected:" + e.getMessage());
            }
        }


        //bar and menu
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.bottom_menu_news,R.id.bottom_menu_quotation,
                R.id.bottom_menu_stockpick).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1:
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        System.out.println("333333333333333333333333");
                        System.out.println(Environment.getExternalStorageDirectory() + "/huibao/sqlite/");
                        File file = new File(Environment.getExternalStorageDirectory() + "/huibao/sqlite/");
                        if (!file.exists()) {
                            Log.d("jim", "path1 create9999:" + file.mkdirs());
                        }

                        String rootFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + "huibao";
                        String stockfilepath = rootFilePath + "/sqlite/stock.db";

                        int stockVersion = ConfigParameter.StockDatabaseVersion;

                        try {
                            MySqliteOpenHelper oh = new MySqliteOpenHelper(this, stockfilepath,
                                    null, stockVersion);
                            SQLiteDatabase db = oh.getWritableDatabase();
                            db.close();
                        }catch (Exception e){
                            System.out.println("We got unexpected:" + e.getMessage());
                        }
                    }
                }
                break;
        }

    }


    private boolean compareVersion(String newversion,String oldversion){
        boolean state = false;
        String[] news;
        if(newversion.contains(".")){
            news = newversion.split(".");
        }else {
            news = new String[]{newversion};
        }

        String[] olds;
        if(oldversion.contains(".")){
            olds = oldversion.split(".");
        }else {
            olds = new String[]{oldversion};
        }

        int index = 0;
        if(news.length > olds.length){
            index = olds.length;
        }else {
            index = news.length;
        }

        for(int i = 0;i < index;i++){
            int newValue = Integer.parseInt(news[i]);
            int oldValue = Integer.parseInt(olds[i]);

            if(newValue > oldValue){
                state = true;
                break;
            }
        }

        return state;
    }

}