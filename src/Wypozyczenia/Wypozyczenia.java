package Wypozyczenia;


import java.util.Date;

public class Wypozyczenia {
    private int id_wyp;
    private String imie_klienta;
    private String nazwisko_klienta;
    private String marka_sam;
    private String model_sam;
    private String nrRej_sam;
    private Date data_wyp;
    private Date data_zwr;

    public Wypozyczenia(int id_wyp, String imie_klienta, String nazwisko_klienta, String marka_sam, String model_sam, String nrRej_sam, Date data_wyp, Date data_zwr) {
        this.id_wyp = id_wyp;
        this.imie_klienta = imie_klienta;
        this.nazwisko_klienta = nazwisko_klienta;
        this.marka_sam = marka_sam;
        this.model_sam = model_sam;
        this.nrRej_sam = nrRej_sam;
        this.data_wyp = data_wyp;
        this.data_zwr = data_zwr;
    }

    public int getId_wyp() {
        return id_wyp;
    }

    public void setId_wyp(int id_wyp) {
        this.id_wyp = id_wyp;
    }

    public String getImie_klienta() {
        return imie_klienta;
    }

    public void setImie_klienta(String imie_klienta) {
        this.imie_klienta = imie_klienta;
    }

    public String getNazwisko_klienta() {
        return nazwisko_klienta;
    }

    public void setNazwisko_klienta(String nazwisko_klienta) {
        this.nazwisko_klienta = nazwisko_klienta;
    }

    public String getMarka_sam() {
        return marka_sam;
    }

    public void setMarka_sam(String marka_sam) {
        this.marka_sam = marka_sam;
    }

    public String getModel_sam() {
        return model_sam;
    }

    public void setModel_sam(String model_sam) {
        this.model_sam = model_sam;
    }

    public String getNrRej_sam() {
        return nrRej_sam;
    }

    public void setNrRej_sam(String nrRej_sam) {
        this.nrRej_sam = nrRej_sam;
    }

    public Date getData_wyp() {
        return data_wyp;
    }

    public void setData_wyp(Date data_wyp) {
        this.data_wyp = data_wyp;
    }

    public Date getData_zwr() {
        return data_zwr;
    }

    public void setData_zwr(Date data_zwr) {
        this.data_zwr = data_zwr;
    }
}