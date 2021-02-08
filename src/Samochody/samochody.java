package Samochody;

public class samochody {
    private int id;
    private String marka;
    private String model;
    private String nrRej;
    private String stan;

    public samochody(int id, String marka, String model, String nrRej, String stan) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.nrRej = nrRej;
        this.stan = stan;
    }

    public int getId() {
        return this.id;
    }

    public String getMarka() {
        return this.marka;
    }

    public String getModel() {
        return this.model;
    }

    public String getNrRej() {
        return this.nrRej;
    }

    public String getStan() {
        return this.stan;
    }


}
