package application.model;
import java.util.ArrayList;
import java.time.LocalDate;

public class Konference {

    // Simple attributter
    private String navn;
    private String adresse;
    private double pris;
    private LocalDate startdato;
    private int antalDage;

    // Link attributter
    private ArrayList<Hotel> hoteller = new ArrayList<Hotel>();
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<Tilmelding>();
    private ArrayList<Udflugt> udflugter = new ArrayList<Udflugt>();

    // Constructor
    public Konference(String navn, String adresse, double pris, LocalDate startdato, int antalDage) {
        this.navn = navn;
        this.adresse = adresse;
        this.pris = pris;
        this.startdato = startdato;
        this.antalDage = antalDage;
    }

    // Metoder for de simple typer

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() { return navn; }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public void setStartdato(LocalDate startdato) {
        this.startdato = startdato;
    }

    public int getAntalDage() {
        return antalDage;
    }

    public void setAntalDage(int antalDage) {
        this.antalDage = antalDage;
    }

    // Metoder der vedligeholder forbindelsen til Udflugter

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public void addUdflugt(Udflugt udflugt) {
        if(!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
        }
    }

    public void removeUdflugt(Udflugt udflugt) {
        if(udflugter.contains(udflugt)) {
            udflugter.remove(udflugt);
        }
    }

    // Metoder der vedligeholder forbindelsen til Hotel

    public ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public void addHotel(Hotel hotel) {
        if(!hoteller.contains(hotel)) {
            hoteller.add(hotel);
        }
    }

    public void removeHotel(Hotel hotel){
        if(hoteller.contains(hotel)) {
            hoteller.remove(hotel);
        }
    }

    // Metoder der vedligeholder forbindelsen til Tilmelding

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setKonference(this);
        }
    }

    public void removeTilmelding(Tilmelding tilmelding){
        if(tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
            tilmelding.setKonference(null);
        }
    }

}
