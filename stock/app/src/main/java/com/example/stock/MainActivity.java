package com.example.stock;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;

import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemInit;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        FileSystemInit fileInit = new FileSystemInit();
        fileInit.startInit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        String rootFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + "huibao";
        String stockfilepath = rootFilePath + "/sqlite/stock.db";
        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(this, stockfilepath,
                null, stockVersion);
        SQLiteDatabase db = oh.getWritableDatabase();
        db.close();


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
}