package Klienci;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import DatabaseConn.DBConnect;
import Samochody.samochody;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class KlienciController implements Initializable{



    @FXML
    private ImageView btnInsert;

    @FXML
    private ImageView btnUpdate;

    @FXML
    private ImageView btnDelete;

    @FXML
    private ImageView btnClear;

    @FXML
    private TextField tfImie;

    @FXML
    private TextField tfNazwisko;

    @FXML
    private TextField tfAdres;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNIP;

    @FXML
    private TextField tfTel;

    @FXML
    private TextField searchBox;
    @FXML
    private ImageView btnFromClientsToCars;
    @FXML
    private ImageView btnGoToRent;


    @FXML
    private TableView<klienci> table_clients;

    @FXML
    private TableColumn<klienci, Integer> colIdKlienta;

    @FXML
    private TableColumn<klienci, String> colImie;

    @FXML
    private TableColumn<klienci, String> colNazwisko;

    @FXML
    private TableColumn<klienci, String> colAdres;

    @FXML
    private TableColumn<klienci, Integer> colNIP;

    @FXML
    private TableColumn<klienci, String> colTel;


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
        this.showClients();
        DBConnect.getConnection();
    }

    public static ObservableList<klienci> getClientsList() {
        ObservableList<klienci> klienciList = FXCollections.observableArrayList();
        Connection conn = DBConnect.getConnection();
        String query = "SELECT * FROM klienci";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                klienci kl = new klienci(rs.getInt("id_klienta"), rs.getString("Imie"), rs.getString("Nazwisko"), rs.getString("Adres"), rs.getInt("NIP"), rs.getString("nr_tel"));
                klienciList.add(kl);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return klienciList;
    }




    public void showClients() {
        ObservableList<klienci> list = this.getClientsList();
        this.colIdKlienta.setCellValueFactory(new PropertyValueFactory("id_klienta"));
        this.colImie.setCellValueFactory(new PropertyValueFactory("imie_klienta"));
        this.colNazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko_klienta"));
        this.colAdres.setCellValueFactory(new PropertyValueFactory("adres_klienta"));
        this.colNIP.setCellValueFactory(new PropertyValueFactory("NIP_klienta"));
        this.colTel.setCellValueFactory(new PropertyValueFactory("nr_tel_klienta"));
        this.table_clients.setItems(list);
    }

    @FXML
    private void handleMouseAction(MouseEvent event){ //zaznaczanie w tabeli
        try {
            klienci kl = table_clients.getSelectionModel().getSelectedItem();
            tfId.setText("" + kl.getId_klienta());
            tfImie.setText("" + kl.getImie_klienta());
            tfNazwisko.setText("" + kl.getNazwisko_klienta());
            tfAdres.setText("" + kl.getAdres_klienta());
            tfNIP.setText("" + kl.getNIP_klienta());
            tfTel.setText("" + kl.getNr_tel_klienta());
        }
        catch (Exception e) {
        }
    }

    @FXML
    private void clearTextFields(MouseEvent event){
        tfId.clear();
        tfImie.clear();
        tfNazwisko.clear();
        tfAdres.clear();
        tfNIP.clear();
        tfTel.clear();

    }

    private void insertRecord() {
        String query = "INSERT INTO klienci (Imie, Nazwisko, Adres, NIP, nr_tel) VALUES ('" +  this.tfImie.getText() + "','" + this.tfNazwisko.getText() + "','" + this.tfAdres.getText() + "', " + this.tfNIP.getText() + ", '" + this.tfTel.getText() + "')";
        this.executeQuery(query);
        this.showClients();
        clearTextFields(null);
    }

    private void updateRecord() {
        String query = "UPDATE  klienci SET Imie  = '" + this.tfImie.getText() + "', Nazwisko = '" + this.tfNazwisko.getText() + "', Adres = '" + this.tfAdres.getText() + "', NIP = '" + this.tfNIP.getText() + "', nr_tel = '"+ this.tfTel.getText() + "' WHERE id_klienta = " + this.tfId.getText() + "";

        this.executeQuery(query);
        this.showClients();
        clearTextFields(null);
    }

    private void deleteButton() {
        String query = "DELETE FROM klienci WHERE id_klienta =" + this.tfId.getText() + "";
        this.executeQuery(query);
        this.showClients();
        clearTextFields(null);
    }

    private void executeQuery(String query) {
        Connection conn = DBConnect.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception var5) {
            System.out.println(var5);
            var5.printStackTrace();

        }
    }

    @FXML
    private void fromClientsToCars(MouseEvent event){
        try {
           Parent CarsView = FXMLLoader.load(getClass().getResource("/Samochody/Samochody.fxml"));
            Scene CarsScene = new Scene(CarsView);


            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Samochody");
            window.setScene(CarsScene);
            window.setResizable(false);
            window.show();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @FXML
    private void fromClientsToRent(MouseEvent event){
        try {
            Parent RentView = FXMLLoader.load(getClass().getResource("/Wypozyczenia/Wypozyczenia.fxml"));
            Scene RentScene = new Scene(RentView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Wypożyczenia");
            window.setScene(RentScene);
            window.setResizable(false);
            window.show();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void searchRecord(KeyEvent ke){

        FilteredList<klienci> klienciFilteredList = new FilteredList<>(getClientsList(), p-> true );
        searchBox.textProperty().addListener((observableValue, oldvalue, newvalue) -> {
            klienciFilteredList.setPredicate(pers -> {

                if(newvalue == null || newvalue.isEmpty()){
                    return true;
                }

                String typedText = newvalue.toLowerCase();
                if(pers.getImie_klienta().toLowerCase().contains(typedText)){
                    return true;
                }

//                if(pers.getNIP_klienta().toLowerCase().contains(typedText)){
//                    return true;
//                }

                if(pers.getAdres_klienta().toLowerCase().contains(typedText)){
                    return true;
                }

                if(pers.getNazwisko_klienta().toLowerCase().contains(typedText)){
                    return true;
                }

                if(pers.getNr_tel_klienta().toLowerCase().contains(typedText)){
                    return true;
                }

                return false;
            });
            SortedList<klienci> sortedList = new SortedList<>(klienciFilteredList);
            sortedList.comparatorProperty().bind(table_clients.comparatorProperty());
            table_clients.setItems(sortedList);
        });
    }


}
