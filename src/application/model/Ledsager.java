package application.model;
import java.util.ArrayList;

public class Ledsager {

    // Simple attributter
    private String navn;

    // Link attributter
    private ArrayList<Udflugt> udflugter = new ArrayList<Udflugt>();

    // Constructor
    public Ledsager(String navn){
    this.navn = navn;
    }

    //Metoder for de simple typer

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() { return navn; }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    // Metoder der vedligeholder forbindelsen til Udflugt

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public void addUdflugt(Udflugt udflugt){
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
            udflugt.addLedsager(this);
        }
    }

    public void removeUdflugt(Udflugt udflugt) {
        if (udflugter.contains(udflugt)) {
            udflugter.remove(udflugt);
            udflugt.removeLedsager(this);
        }
    }
}