package Samochody;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import DatabaseConn.DBConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SamochodyController implements Initializable {
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfMarka;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfNrRej;


    @FXML
    private ComboBox<String> cbSTan;

    @FXML
    private TableView<samochody> table_cars;
    @FXML
    private TableColumn<samochody, Integer> colId;
    @FXML
    private TableColumn<samochody, String> colMarka;
    @FXML
    private TableColumn<samochody, String> colModel;
    @FXML
    private TableColumn<samochody, String> colNrRej;
    @FXML
    private TableColumn<samochody, String> colStan;
    @FXML
    private ImageView btnInsert;
    @FXML
    private ImageView btnUpdate;
    @FXML
    private ImageView btnDelete;
    @FXML
    private ImageView btnClear;



   // public MainController() {
   // }


    @FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == this.btnInsert) {
            this.insertRecord();
        } else if (event.getSource() == this.btnUpdate) {
            this.updateRecord();
        } else if (event.getSource() == this.btnDelete) {
            this.deleteButton();
        }

    }

    public void initialize(URL url, ResourceBundle rb) {
        this.showCars();
        DBConn.getConnection();
    }



    public ObservableList<samochody> getCarsList() {
        ObservableList<samochody> samochodyList = FXCollections.observableArrayList();
        Connection conn = DBConn.getConnection();
        String query = "SELECT * FROM samochody";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                samochody sam = new samochody(rs.getInt("id"), rs.getString("marka"), rs.getString("model"), rs.getString("nrRej"), rs.getString("stan"));
                samochodyList.add(sam);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return samochodyList;
    }




    public void showCars() {
        ObservableList<samochody> list = this.getCarsList();
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colMarka.setCellValueFactory(new PropertyValueFactory("marka"));
        this.colModel.setCellValueFactory(new PropertyValueFactory("model"));
        this.colNrRej.setCellValueFactory(new PropertyValueFactory("nrRej"));
        this.colStan.setCellValueFactory(new PropertyValueFactory("stan"));
        this.table_cars.setItems(list);

        ObservableList<String> stany = FXCollections.observableArrayList("wypożyczony", "wolny","serwis", "niezarejestrowany");
        cbSTan.setItems(stany);


    }

    private void insertRecord() {
        try {
            String query = "INSERT INTO samochody VALUES (" + this.tfId.getText() + ",'" + this.tfMarka.getText() + "','" + this.tfModel.getText() + "','" + this.tfNrRej.getText() + "', '" + this.cbSTan.getValue() + "')";
            this.executeQuery(query);
            this.showCars();
            clearTextFields(null);
        }
        catch (Exception e){

        }
    }

    private void updateRecord() {
        String query = "UPDATE  samochody SET marka  = '" + this.tfMarka.getText() + "', model = '" + this.tfModel.getText() + "', nrRej = '" + this.tfNrRej.getText() + "', stan = '" + this.cbSTan.getValue() + "' WHERE id = " + this.tfId.getText() + "";

        this.executeQuery(query);
        this.showCars();
        clearTextFields(null);
    }

    private void deleteButton() {
        String query = "DELETE FROM samochody WHERE id =" + this.tfId.getText() + "";
        this.executeQuery(query);
        this.showCars();
        clearTextFields(null);
    }

    private void executeQuery(String query) {
        Connection conn = DBConn.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    @FXML
    public void handleMouseAction(MouseEvent event){ //zaznaczanie w tabeli
        try {
            samochody sam = table_cars.getSelectionModel().getSelectedItem();
            tfId.setText("" + sam.getId());
            tfMarka.setText("" + sam.getMarka());
            tfModel.setText("" + sam.getModel());
            tfNrRej.setText("" + sam.getNrRej());
            cbSTan.setValue(sam.getStan());

        }
        catch (Exception e) {
        }
    }

    @FXML
    private void clearTextFields(MouseEvent event){ //przycisk od czyszczenia
        tfId.clear();
        tfMarka.clear();
        tfModel.clear();
        tfNrRej.clear();
        cbSTan.getSelectionModel().select(-1);
    }

    @FXML
    private void GoToClients(ActionEvent event){
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
    private void btnCarToRent(ActionEvent event){
        try {
            Parent RentView = FXMLLoader.load(getClass().getResource("/Wypozyczenia/Wypozyczenia.fxml"));
            Scene RentScene = new Scene(RentView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Klienci");
            window.setScene(RentScene);
            window.show();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


}
