package Nowe_Wyp;

import DatabaseConn.DBConnect;
import Samochody.samochody;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Nowe_WypController<CB_Clients> {

@FXML
    ComboBox CB_Clients;

    @FXML
    private void btnRentPickClient(ActionEvent event) {

    }

    @FXML
    private void btnRentPickCar(ActionEvent event) {

    }

    @FXML
    private void clearTextFields(ActionEvent event) {

    }

    public ObservableList<samochody> getCarsList() {
        ObservableList<samochody> samochodyList = FXCollections.observableArrayList();
        Connection conn = DBConnect.getConnection();
        String query = "SELECT * FROM samochody";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                samochody sam = new samochody(rs.getInt("id"), rs.getString("marka"), rs.getString("model"), rs.getString("nrRej"), rs.getString("stan"));
                samochodyList.add(sam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return samochodyList;
    }


}