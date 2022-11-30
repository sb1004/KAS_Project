package application.model;

public class Service {

    // Service kan være en service som er tilknyttet et hotelophold - fx WiFi, morgenmad etc.
    private String navn;
    private double pris;

    // Skaber et service objekt

    public Service(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    // Get og set metoder til de simple attributter

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String toString() {
        return navn + " " + pris + " kr";
    }
}
