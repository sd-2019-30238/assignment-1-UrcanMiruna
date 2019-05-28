package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection conn = null;
    public static void connect(){

        try{
            String url = "jdbc:sqlite:C:/Users/Miruna Urcan/Desktop/SQLite/deals.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }/*finally {
            try{
                if(conn != null){
                    conn.close();
                    System.out.println("1");
                }

            }catch (SQLException d){
                System.out.println(d.getMessage());
            }
        }*/

    }
    public static void disconnect(){
        //connect();
        try{
            if (conn!=null){
                conn.close();
                System.out.println("Disconnected");
            }
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }/*
    public static void main(String[] args){
        connect();
        disconnect();
    }*/

}
