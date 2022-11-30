package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {

    // Tilmelding holder styr på hvilke deltagere der er tilmeldt
    // til hvilke konferencer
    // samt hvilket hotel, ledsager og udflugter der er tilvalgt

    // Simple attributter

    private LocalDate ankomstDato;

    private LocalDate afrejseDato;

    private boolean foredragsholder;

    private String navn;

    private String adresse;

    private String by;

    private String land;

    private int telefonNr;

    // Link attributter

    private Ledsager ledsager;

    private Hotel hotel;

    private Konference konference;

    private ArrayList<Service> services = new ArrayList<>();

    // Constructor

    public Tilmelding(LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder, String navn, String adresse, String by, String land, int telefonNr, Konference konference) {
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.foredragsholder = foredragsholder;
        this.navn = navn;
        this.adresse = adresse;
        this.by = by;
        this.land = land;
        this.telefonNr = telefonNr;
        this.setKonference(konference);
    }


    // Get og set for de simple attributter

    public LocalDate getAnkomstDato() {
        return ankomstDato;
    }
    @Override
    public String toString() { return navn + ", " + konference; }

    public void setAnkomstDato(LocalDate ankomstDato) {
        this.ankomstDato = ankomstDato;
    }

    public LocalDate getAfrejseDato() {
        return afrejseDato;
    }

    public void setAfrejseDato(LocalDate afrejseDato) {
        this.afrejseDato = afrejseDato;
    }

    public boolean isForedragsholder() {
        return foredragsholder;
    }

    public void setForedragsholder(boolean foredragsholder) {
        this.foredragsholder = foredragsholder;
    }
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

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public int getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(int telefonNr) {
        this.telefonNr = telefonNr;
    }



    //Metoder der vedligeholder forbindelsen til Ledsager

    public Ledsager getLedsager()
    {
        return ledsager;
    }

    public void setLedsager(Ledsager ledsager)
    {
        if (this.ledsager != ledsager) {
            this.ledsager = ledsager;
        }
    }


    //Metoder der vedligeholder forbindelsen til Hotel

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        if (this.hotel != hotel) {
            Hotel oldHotel = this.hotel;
            if (oldHotel != null) {
                oldHotel.removeTilmelding(this);
            }
            this.hotel = hotel;
            if (hotel != null) {
                hotel.addTilmelding(this);
            }
        }
    }

    //Metoder der vedligeholder forbindelsen til Konference

    public Konference getKonference() {
        return konference;
    }

    public void setKonference(Konference konference) {
        if (this.konference != konference) {
            Konference oldKonference = this.konference;
            if (oldKonference != null) {
                oldKonference.removeTilmelding(this);
            }
            this.konference = konference;
            if (konference != null) {
                konference.addTilmelding(this);
            }
        }
    }

    //Metoder der vedligeholder forbindelsen til Service

    public ArrayList<Service> getServices = new ArrayList<>(services);

    public void addService(Service service) {
        if (!services.contains(service)) {
            services.add(service);
        }
    }

    public void removeService(Service service) {
        if (services.contains(service)) {
            services.remove(service);
        }
    }

    // Beregner den samlede pris for tilmeldingen

        public double samletPris() {
            int dage = afrejseDato.compareTo(ankomstDato);

            double sum = 0;
            double ksum = 0;
            double hsum = 0;
            double ssum = 0;
            double usum = 0;

            //Pris for konference * antal dage alt efter om man er foredragsholder eller ej
            //Prisen sættes til 0, såfremt man tilvælger at være foredragsholder
            if(!this.foredragsholder) {
                ksum = konference.getPris() * (dage + 1);
            }

            //Pris for hotel * antal dage
            if (hotel != null)
                if (ledsager != null)
                    hsum = hotel.getPrisDouble() * dage;
                else {
                    hsum = hotel.getPrisEnkelt() * dage;
                }

            //Pris for services * antal dage
            if (hotel != null && !services.isEmpty())
                for (Service s : services) {
                    ssum += s.getPris() * dage;
                }

            //Pris for udflugter * antal udflugter
            if(ledsager != null && !ledsager.getUdflugter().isEmpty()) {
                for(Udflugt u : ledsager.getUdflugter()) {
                usum += u.getPris();
                }
            }

            //Summér beløbene sammen
            sum = ksum + hsum + ssum + usum;
            return sum;
        }

}
