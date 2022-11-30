package application.controller;
import application.model.*;
import storage.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    //Hotel


    public static Hotel createHotel (String navn, String adresse, double prisEnkelt, double prisDouble) {
        Hotel hotel = new Hotel(navn, adresse, prisEnkelt, prisDouble);
        Storage.addHotel(hotel);
        return hotel;
    }

    public static void deleteHotel (Hotel hotel){
        Storage.removeHotel(hotel);
    }

    public static void updateHotel (Hotel hotel, String navn, String adresse, double prisEnkelt, double prisDouble) {
        hotel.setNavn(navn);
        hotel.setAdresse(adresse);
        hotel.setPrisEnkelt(prisEnkelt);
        hotel.setPrisDouble(prisDouble);
    }

    public static ArrayList<Hotel> getHoteller() {
        return Storage.getHoteller();
    }



    //--------------------------------------------------------------------------------

    //Konference


    public static Konference createKonference (String navn, String adresse, double pris, LocalDate startdato, int antalDage){
        Konference konference = new Konference(navn, adresse, pris, startdato, antalDage);
        Storage.addKonference(konference);
        return konference;
    }

    public static void deleteKonference (Konference konference){
        Storage.removeKonference(konference);
    }

    public static void updateKonference (Konference konference, String navn, String adresse, double pris, LocalDate startdato, int antalDage) {
        konference.setNavn(navn);
        konference.setAdresse(adresse);
        konference.setPris(pris);
        konference.setStartdato(startdato);
        konference.setAntalDage(antalDage);
    }

    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }

    public static void tilfoejHotelTilKonference(Konference konference, Hotel hotel) {
        konference.addHotel(hotel);
    }

    public static void fjernHotelFraKonference(Konference konference, Hotel hotel) {
        konference.removeHotel(hotel);
    }

    public static ArrayList<String> getDeltagere(Konference konference) {
        ArrayList<String> deltagere = new ArrayList<>();
        for (Tilmelding tilmelding: konference.getTilmeldinger()) {
            if (tilmelding.getLedsager() != null) {
                String l = tilmelding.getNavn() + ", " + tilmelding.getLedsager().getNavn();
                deltagere.add(l);
            }
            else {
                String s = tilmelding.getNavn() + ", " + " Der er ingen ledsager";
                deltagere.add(s);
            }

        }
        return deltagere;
    }

    public static ArrayList<String> getHotelGaester(Hotel hotel) {
        ArrayList<String> gaester = new ArrayList<>();
        for (Tilmelding tilmelding : hotel.getTilmeldinger()) {
            if (tilmelding.getHotel() != null && tilmelding.getLedsager() == null) {
                String s = tilmelding.getNavn();
                gaester.add(s);
            }
            else if (tilmelding.getHotel() != null && tilmelding.getLedsager() != null) {
                String s = tilmelding.getNavn() + ", " + tilmelding.getLedsager().getNavn();
                gaester.add(s);
            }
        }
        return gaester;
    }
    //--------------------------------------------------------------------------------

    //Ledsager

    public static Ledsager createLedsager (String navn) {
        Ledsager ledsager = new Ledsager(navn);
        Storage.addLedsager(ledsager);
        return ledsager;
    }

    public static void deleteLedsager (Ledsager ledsager) {
        Storage.removeLedsager(ledsager);
    }

    public static void updateLedsager (Ledsager ledsager, String navn){
        ledsager.setNavn(navn);
    }
    //--------------------------------------------------------------------------------

    //Service

    public static Service createService(String navn, double pris){
        Service service = new Service(navn, pris);
        Storage.addService(service);
        return service;
    }

    public static void deleteService(Service service){
        Storage.removeService(service);
    }

    public static void updateService(Service service, String navn, double pris){
        service.setNavn(navn);
        service.setPris(pris);
    }

    public static ArrayList<Service> getServices() {
        return Storage.getServices();
    }
    //--------------------------------------------------------------------------------

    //Tilmelding

    public static Tilmelding createTilmelding(LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder, String navn, String adresse, String by, String land, int telefonNr, Konference konference){
        Tilmelding tilmelding = new Tilmelding(ankomstDato, afrejseDato, foredragsholder, navn, adresse, by, land, telefonNr, konference);
        Storage.addTilmelding(tilmelding);
        return tilmelding;
    }

    public static void deleteTilmeldiing(Tilmelding tilmelding) {
        Storage.removeTilmelding(tilmelding);
    }

    public static void updateTilmelding(Tilmelding tilmelding,LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder, String navn, String adresse, String by, String land, int telefonNr, Konference konference) {
        tilmelding.setAnkomstDato(ankomstDato);
        tilmelding.setAfrejseDato(afrejseDato);
        tilmelding.setForedragsholder(foredragsholder);
        tilmelding.setNavn(navn);
        tilmelding.setAdresse(adresse);
        tilmelding.setBy(by);
        tilmelding.setLand(land);
        tilmelding.setTelefonNr(telefonNr);
        tilmelding.setKonference(konference);
    }

    public static void tilfoejHotelTilTilmelding(Tilmelding tilmelding, Hotel hotel) {
        tilmelding.setHotel(hotel);
    }

    //--------------------------------------------------------------------------------

    //Udflugt

    public static Udflugt createUdflugt(String navn, LocalDate dato, double pris){
        Udflugt udflugt = new Udflugt(navn, dato, pris);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }

    public static void deleteUdflugt(Udflugt udflugt){
        Storage.removeUdflugt(udflugt);
    }

    public static void updateUdflugt(Udflugt udflugt, String navn, LocalDate dato, double pris){
        udflugt.setNavn(navn);
        udflugt.setDato(dato);
        udflugt.setPris(pris);
    }

    public static void initStorage() {
        Konference Hav = Controller.createKonference("Hav og Himmel", "Odense Universitet", 1500, LocalDate.of(2022,05, 18), 3);
        Tilmelding Finn = Controller.createTilmelding(LocalDate.of(2022, 05, 18), LocalDate.of(2022, 05, 20), false, "Finn Madsen", "Kørvevænget 13", "Aarhus N",  "Danmark", 88888888, Hav);
        Hotel Svane = Controller.createHotel("Den Hvide Svane", "Teatergade 2", 1050, 1250);
        Service WiFi = Controller.createService("WiFi", 150);
        Udflugt Egeskov = Controller.createUdflugt("Egeskov", LocalDate.of(2022, 05, 19), 75);
        Udflugt TrapHoldt = Controller.createUdflugt("Trapholt", LocalDate.of(2022, 05, 20), 200);
        Ledsager Mie = Controller.createLedsager("Mie Sommer");
        Hav.addHotel(Svane);
        Hav.addUdflugt(Egeskov);
        Hav.addUdflugt(TrapHoldt);
        Mie.addUdflugt(Egeskov);
        Mie.addUdflugt(TrapHoldt);
        Finn.setHotel(Svane);
        Finn.setLedsager(Mie);

    }

    public static void init() {
        initStorage();
    }
}
