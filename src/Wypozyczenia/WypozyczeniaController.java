package Wypozyczenia;


import DatabaseConn.DBConnect;
import Klienci.KlienciController;
import Klienci.Klienci;
import Samochody.SamochodyController;
import Samochody.Samochody;
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

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class WypozyczeniaController implements Initializable {

    public void initialize(URL url, ResourceBundle rb) {
        this.showRents();
        DBConnect.getConnection();

        CB_Klient.setItems(KlienciController.getClientsList());
        // new AutoCompleteComboBoxListener<>(CB_Klient);

        CB_Samochod.setItems(SamochodyController.getCarsList());
        // new AutoCompleteComboBoxListener<>(CB_Samochod);

    }

    Klienci kltemp;
    @FXML
    private Button btnOpenNewWyp;

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
    private ImageView btnRentPickClient;

    @FXML
    private ImageView btnRentPickCar;

    @FXML
    private ComboBox<Samochody> CB_Samochod;

    @FXML
    private ComboBox<Klienci> CB_Klient;
    @FXML
    private DatePicker dpWyp;
    @FXML
    private DatePicker dpZwr;
    @FXML
    private TableView<Wypozyczenia> table_wypozyczenia;
    @FXML
    private TableColumn<Wypozyczenia, Integer> id_wyp;
    @FXML
    private TableColumn<Wypozyczenia, String> imie_klienta;
    @FXML
    private TableColumn<Wypozyczenia, String> nazwisko_klienta;
  //  @FXML
  //  private TableColumn<wypozyczenia, String> adres_klienta;
    @FXML
    private TableColumn<Wypozyczenia, String> marka_sam;
    @FXML
    private TableColumn<Wypozyczenia, String> model_sam;
    @FXML
    private TableColumn<Wypozyczenia, String> nrRej_sam;
    @FXML
    private TableColumn<Wypozyczenia, String> data_wyp;
    @FXML
    private TableColumn<Wypozyczenia, String> data_zwr;


    @FXML
    void clearTextFields(MouseEvent event) {
        System.out.println("");
    }

    @FXML
    void handleButtonAction(MouseEvent event) {
        System.out.println("");
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        System.out.println("");
    }

 //   ComboBox<klienci> cbkl = new ComboBox<klienci>();

    public ObservableList<Wypozyczenia> getRentsList() {
        ObservableList<Wypozyczenia> RentsList = FXCollections.observableArrayList();
        Connection conn = DBConnect.getConnection();
        String query = "SELECT wypozyczenia.id_wyp, klienci.Imie, klienci.Nazwisko, samochody.marka, samochody.Model, samochody.NrRej, wypozyczenia.DataWyp, wypozyczenia.DataZwr  FROM klienci,wypozyczenia, samochody WHERE klienci.id_klienta=wypozyczenia.id_klienta AND wypozyczenia.id_sam=samochody.id";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Wypozyczenia wyp = new Wypozyczenia(rs.getInt("id_wyp"), rs.getString("Imie"), rs.getString("Nazwisko"), rs.getString("marka"), rs.getString("Model"), rs.getString("NrRej"), rs.getDate("DataWyp"), rs.getDate("DataZwr"));
                RentsList.add(wyp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RentsList;
    }



    public void showRents() {
        ObservableList<Wypozyczenia> list = this.getRentsList();
        this.id_wyp.setCellValueFactory(new PropertyValueFactory("id_wyp"));
        this.imie_klienta.setCellValueFactory(new PropertyValueFactory("imie_klienta"));
        this.nazwisko_klienta.setCellValueFactory(new PropertyValueFactory("nazwisko_klienta"));
        this.marka_sam.setCellValueFactory(new PropertyValueFactory("marka_sam"));
        this.model_sam.setCellValueFactory(new PropertyValueFactory("model_sam"));
        this.nrRej_sam.setCellValueFactory(new PropertyValueFactory("nrRej_sam"));
        this.data_wyp.setCellValueFactory(new PropertyValueFactory("data_wyp"));
        this.data_zwr.setCellValueFactory(new PropertyValueFactory("data_zwr"));
        this.table_wypozyczenia.setItems(list);


    }



    @FXML
    private void clearTextFieldss(MouseEvent event){ //przycisk od czyszczenia
        CB_Klient.getSelectionModel().select(-1);
        CB_Samochod.getSelectionModel().select(-1);
        showRents();
    }


    @FXML
    private void btnRentPickClient(MouseEvent event) {
        try {
            Parent ClientsView = FXMLLoader.load(getClass().getResource("/Klienci/Klienci.fxml"));
            Scene ClientsScene = new Scene(ClientsView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Klienci");
            window.setScene(ClientsScene);
            window.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnRentPickCar(MouseEvent event) {
        try {
            Parent CarsView = FXMLLoader.load(getClass().getResource("/Samochody/Samochody.fxml"));
            Scene CarsScene = new Scene(CarsView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Wypożyczalnia - Samochody");
            window.setScene(CarsScene);
            window.setResizable(false);
            window.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private void executeQuery(String query) {
        Connection conn = DBConnect.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    @FXML
    private void cbClientHandle(MouseEvent e) {


}

@FXML
private void cbClienttemp(ActionEvent e){

}
@FXML
    private void cbCarHandle(MouseEvent e){

}

//    @FXML
//    private void InsertHandle() {
//        klienci kl = cbKlient.getSelectionModel().getSelectedItem();
//        System.out.println(kl.getId_klienta());
        //samochody sam = CB_Samochod.getSelectionModel().getSelectedItem();

//        LocalDate datWyp = dpWyp.getValue();
//        LocalDate datZwr = dpZwr.getValue();
//        try {
//
//
//            String query = "INSERT INTO wypozyczenia (id_klienta, id_sam, Marka, Model, nrRej, DataWyp, DataZwr ) VALUES (" + kl.getId_klienta() + "," + sam.getId() + ",'" + sam.getMarka() + "', '" + sam.getModel() + "','" + sam.getNrRej() + "', "+datWyp + ", "+ datZwr+")";
//              this.executeQuery(query);
//              this.showRents();
//                clearTextFields(null);
//               }
//        catch (Exception errr){

        //}
   // }


    @FXML
    private void InsertHandle() {
        System.out.println(CB_Klient.getValue().getImie_klienta());
        System.out.println(CB_Samochod.getValue().getMarka());

    }


}
