package com.example.historygame;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        String ip = "10.0.2.2", port = "5432", db = "HistoryGameDB", username = "postgres", password = "123456";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectURL = null;

        try {

            Class.forName("org.postgresql.Driver");
            connectURL = "jdbc:postgresql://" + ip + ":" + port + "/" + db;
            con = DriverManager.getConnection(connectURL, username, password);
            System.out.println("I was here");
        } catch (Exception e) {
            Log.e("Error in connection :", e.getMessage());
        }
        return con;
    }
}
