package Klienci;

public class klienci {

   private int id_klienta;
   private String imie_klienta;
   private String nazwisko_klienta;
   private String adres_klienta;
   private int NIP_klienta;
   private String nr_tel_klienta;

   public klienci(int id_klienta, String imie_klienta, String nazwisko_klienta, String adres_klienta, int NIP_klienta, String nr_tel_klienta){
       this.id_klienta = id_klienta;
       this.imie_klienta = imie_klienta;
       this.nazwisko_klienta = nazwisko_klienta;
       this.adres_klienta = adres_klienta;
       this.NIP_klienta = NIP_klienta;
       this.nr_tel_klienta = nr_tel_klienta;
   }

    public int getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
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

    public String getAdres_klienta() {
        return adres_klienta;
    }

    public void setAdres_klienta(String adres_klienta) {
        this.adres_klienta = adres_klienta;
    }

    public int getNIP_klienta() {
        return NIP_klienta;
    }

    public void setNIP_klienta(int NIP_klienta) {
        this.NIP_klienta = NIP_klienta;
    }

    public String getNr_tel_klienta() {
        return nr_tel_klienta;
    }

    public void setNr_tel_klienta(String nr_tel_klienta) {
        this.nr_tel_klienta = nr_tel_klienta;
    }
}
