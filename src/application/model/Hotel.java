package application.model;

import java.util.ArrayList;

public class Hotel {

    // Simple attributter
    private String navn;
    private String adresse;
    private double prisEnkelt;
    private double prisDouble;

    // Link attributter
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private final ArrayList<Service> services = new ArrayList<>();

    // Constructor
    public Hotel(String navn, String adresse, double prisEnkelt, double prisDouble) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisEnkelt = prisEnkelt;
        this.prisDouble = prisDouble;
    }

    // Metoder for de simple typer

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrisEnkelt() {
        return prisEnkelt;
    }

    public void setPrisEnkelt(double pris) {
        this.prisEnkelt = pris;
    }

    public double getPrisDouble() {
    return prisDouble;
    }

    public void setPrisDouble(double pris) {
        this.prisDouble = pris;
    }

    // Metoder der vedligeholder forbindelsen til Tilmelding
    public void addTilmelding (Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setHotel(this);
        }
    }

    public void removeTilmelding (Tilmelding tilmelding) {
        if (tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
            tilmelding.setHotel(null);
        }
    }

    public ArrayList<Tilmelding> getTilmeldinger () {
        return new ArrayList<>(tilmeldinger);
    }

    // Metoder der vedligeholder forbindelsen til Service
    public void addService (Service service) {
        if (!services.contains(service)) {
            services.add(service);
        }
    }

    public void removeService (Service service) {
        services.remove(service);
    }

    public ArrayList<Service> getServices () {
        return new ArrayList<>(services);
    }

    public String toString() {
        return navn;
    }
}
