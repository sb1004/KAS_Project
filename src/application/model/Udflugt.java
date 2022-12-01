package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {

    // Udflugt er en begivenhed som en ledsager kan deltage i

    // Simple attributter
    private String navn;

    private LocalDate dato;

    private double pris;

    //Link attribut til Ledsager
    private ArrayList<Ledsager> ledsagere = new ArrayList<>();

    // Skaber et udflugts objekt
    public Udflugt(String navn, LocalDate dato, double pris) {
        this.navn = navn;
        this.dato = dato;
        this.pris = pris;
    }

    // Get og set for de simple attributter
    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() { return navn; }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    //Medtoder der vedligeholder forbindelsen til Ledsager

    public ArrayList<Ledsager> getLedsagere() {
        return new ArrayList<>(ledsagere);
    }

    public void addLedsager(Ledsager ledsager) {
        if (!ledsagere.contains(ledsager)) {
            ledsagere.add(ledsager);
            ledsager.addUdflugt(this);
        }
    }
    
    public void removeLedsager(Ledsager ledsager) {
        if (ledsagere.contains(ledsager)) {
            ledsagere.remove(ledsager);
            ledsager.removeUdflugt(this);
        }
    }
}
