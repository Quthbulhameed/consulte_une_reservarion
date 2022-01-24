package com.example.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


public class mysqlconnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ichi","root","root");
            
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<place> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<place> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from place");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new place(Integer.parseInt(rs.getString("id")),Integer.parseInt(rs.getString("etage")),Integer.parseInt(rs.getString("numParking")),rs.getString("disponibilite"),Integer.parseInt(rs.getString("reservation")),Integer.parseInt(rs.getString("categorie")),Integer.parseInt(rs.getString("typevoiture"))));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
