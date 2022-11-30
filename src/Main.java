import application.model.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Konference Hav = new Konference("Hav og Himmel", "Odense Universitet", 1500, LocalDate.of(2022,05, 18), 3);
        Tilmelding Finn = new Tilmelding(LocalDate.of(2022, 05, 18), LocalDate.of(2022, 05, 20), false, "Finn Madsen", "Kørvevænget 13", "Aarhus N",  "Danmark", 88888888, Hav);

        System.out.println("Samlet pris: " + Finn.samletPris());

        Hotel Svane = new Hotel("Den Hvide Svane", "Teatergade 2", 1050, 1250);
        Tilmelding Niels  = new Tilmelding(LocalDate.of(2022, 05, 18), LocalDate.of(2022, 05, 20), false, "Niels Petersen", "Kørvevænget 15", "Aarhus N",  "Danmark", 88888867, Hav);

        //Svane.addTilmelding(Niels);
        Niels.setHotel(Svane);
        System.out.println("Samlet pris: " + Niels.samletPris());

        Tilmelding Peter  = new Tilmelding(LocalDate.of(2022, 05, 18), LocalDate.of(2022, 05, 20), false, "Peter Sommer", "Kørvevænget 33", "Aarhus N",  "Danmark", 88888837, Hav);
        Ledsager Mie = new Ledsager("Mie Sommer");
        Peter.setLedsager(Mie);
        Peter.setHotel(Svane);
        Udflugt Egeskov = new Udflugt("Egeskov", LocalDate.of(2022, 05, 19), 75);
        Udflugt Trapholt = new Udflugt("Trapholt", LocalDate.of(2022, 05, 20), 200);
        Hav.addUdflugt(Egeskov);
        Hav.addUdflugt(Trapholt);
        Mie.addUdflugt(Egeskov);
        Mie.addUdflugt(Trapholt);
        Service WiFi = new Service("WiFi", 50);
        Svane.addService(WiFi);
        Peter.addService(WiFi);
        System.out.println("Samlet pris: " + Peter.samletPris());

        Tilmelding Lone  = new Tilmelding(LocalDate.of(2022, 05, 18), LocalDate.of(2022, 05, 20), true, "Lone Jensen", "Kørvevænget 563", "Aarhus N",  "Danmark", 88838837, Hav);
        Ledsager Jan = new Ledsager("Jan Madsen");
        Lone.setLedsager(Jan);
        Lone.setHotel(Svane);
        Lone.addService(WiFi);
        Udflugt Odense = new Udflugt("Byrundtur i Odense", LocalDate.of(2022, 05,18), 125);
        Hav.addUdflugt(Odense);
        Jan.addUdflugt(Egeskov);
        Jan.addUdflugt(Odense);
        System.out.println("Samlet pris: " + Lone.samletPris());



    }
}
