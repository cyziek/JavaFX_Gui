package niewazne;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlconnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/market","root","" );
            return conn;

        }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        return null;
        }
    }

    public static ObservableList<users> getDatausers() {
        Connection conn = ConnectDb();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new users(Integer.parseInt(rs .getString("user_id")), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("type")));
            }
            } catch(Exception e){
            }
            return list;

    }
}
