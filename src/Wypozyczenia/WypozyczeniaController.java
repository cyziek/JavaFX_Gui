package Wypozyczenia;


import DatabaseConn.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WypozyczeniaController {

    @FXML
    private TextField tfId;

    @FXML
    private ImageView btnClear;

    @FXML
    private ImageView btnInsert;

    @FXML
    private ImageView btnUpdate;

    @FXML
    private ImageView btnDelete;

    @FXML
    private ComboBox CB_Cars;

    @FXML
    private TableView<?> table_clients;

    @FXML
    void clearTextFields(MouseEvent event) {

    }

    @FXML
    void handleButtonAction(MouseEvent event) {

    }

    @FXML
    void handleMouseAction(MouseEvent event) {

    }



    public void initialize(URL url, ResourceBundle rb) {
        DBConnect.getConnection();
    }


    @FXML
    private void btnRentPickClient(ActionEvent event){
        try {
            Parent ClientsView = FXMLLoader.load(getClass().getResource("/Klienci/Klienci.fxml"));
            Scene ClientsScene = new Scene(ClientsView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Klienci");
            window.setScene(ClientsScene);
            window.show();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void btnRentPickCar(ActionEvent event){
        try {
            Parent CarsView = FXMLLoader.load(getClass().getResource("/Samochody/Samochody.fxml"));
            Scene CarsScene = new Scene(CarsView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Samochody");
            window.setScene(CarsScene);
            window.show();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }





}
