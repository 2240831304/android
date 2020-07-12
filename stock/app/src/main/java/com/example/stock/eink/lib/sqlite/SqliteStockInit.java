package com.example.stock.eink.lib.sqlite;

import android.os.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqliteStockInit {

    private static String Drivde = "org.sqlite.JDBC";

    public SqliteStockInit(){
    }

    public void startInit(){
        Init();
    }

    private void Init(){
        startInitStockDatabase();
    }


    private void startInitStockDatabase(){
        try{
            Class.forName(Drivde);
            String stockfilepath = Environment.getExternalStorageDirectory().getPath() + "/" + "huibao";
            stockfilepath = stockfilepath + "/sqlite/stock.db";
            String srcPath = "jdbc:sqlite:" + stockfilepath;

            System.out.println(srcPath);

            //连接数据库,不存在则创建
            Connection connecter = DriverManager.getConnection(srcPath);
            //创建连接对象，是Java的一个操作数据库的重要接口
            Statement statement = connecter.createStatement();


            connecter.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}

/*
        //statement.executeUpdate(sb.toString());
        //connecter.commit();
 ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
       while (rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("name");
          int age = rs.getInt("age");
          String address = rs.getString("address");
          float salary = rs.getFloat("salary");
          System.out.println("ID = " + id);
          System.out.println("NAME = " + name);
          System.out.println("AGE = " + age);
          System.out.println("ADDRESS = " + address);
          System.out.println("SALARY = " + salary);
          System.out.println("--------");
       }
 */
