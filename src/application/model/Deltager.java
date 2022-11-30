package application.model;

import java.util.ArrayList;

public class Deltager {
    private String navn;
    private String adresse;
    private String by;
    private String land;
    private int telefonNr;

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public ArrayList<Tilmelding> getTilmeldinger() {return new ArrayList<>(tilmeldinger);}

    public Deltager(String navn, String adresse, String by, String land, int telefonNr) {
        this.navn = navn;
        this.adresse = adresse;
        this.by = by;
        this.land = land;
        this.telefonNr = telefonNr;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getBy() {
        return by;
    }

    public String getLand() {
        return land;
    }

    public int getTelefonNr() {
        return telefonNr;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setTelefonNr(int telefonNr) {
        this.telefonNr = telefonNr;
    }

    public void addTilmelding (Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void removeTilmelding (Tilmelding tilmelding) {
        tilmeldinger.remove(tilmelding);
    }
}
