package com.example.stock.eink.lib.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqliteStockInit {
    private Connection connecter;
    private Statement statement;
    private static String Drivde = "org.sqlite.JDBC";

    public SqliteStockInit(){
        Init();
    }

    private void Init(){
        try{
            Class.forName(Drivde);
            //连接数据库zhou.db,不存在则创建
            connecter = DriverManager.getConnection("jdbc:sqlite:");

            //创建连接对象，是Java的一个操作数据库的重要接口
            statement = connecter.createStatement();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void closeSqlite(){

        try {
            connecter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    public void startInitStockDatabase(){

        closeSqlite();
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
