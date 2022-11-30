package storage;
import application.model.*;
import java.util.ArrayList;

public class Storage {
    private static ArrayList<Hotel> hoteller = new ArrayList<>();
    private static ArrayList<Konference> konferencer = new ArrayList<>();
    private static ArrayList<Ledsager> ledsagere = new ArrayList<>();
    private static ArrayList<Service> services = new ArrayList<>();
    private static ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private static ArrayList<Udflugt> udflugter = new ArrayList<>();

    //--------------------------------------------------------------------------------

    public static ArrayList<Hotel> getHoteller() {return new ArrayList<Hotel>(hoteller);}

    public static void addHotel(Hotel hotel) {hoteller.add(hotel);}

    public static void removeHotel(Hotel hotel) {hoteller.remove(hotel);}


    //--------------------------------------------------------------------------------

    public static ArrayList<Konference> getKonferencer() {return new ArrayList<Konference>(konferencer);}

    public static void addKonference(Konference konference) {konferencer.add(konference);}

    public static void removeKonference(Konference konference) {konferencer.remove(konference);}

    //--------------------------------------------------------------------------------

    public static ArrayList<Ledsager> getLedsagere() {return new ArrayList<Ledsager>(ledsagere);}

    public static void addLedsager(Ledsager ledsager) {ledsagere.add(ledsager);}

    public static void removeLedsager(Ledsager ledsager) {ledsagere.remove(ledsager);}

    //--------------------------------------------------------------------------------

    public static ArrayList<Service> getServices() {return new ArrayList<>(services);}

    public static void addService(Service service) {services.add(service);}

    public static void removeService(Service service) {services.remove(service);}

    //--------------------------------------------------------------------------------

    public static ArrayList<Tilmelding> getTilmeldinger() {return new ArrayList<>(tilmeldinger);}

    public static void addTilmelding(Tilmelding tilmelding) {tilmeldinger.add(tilmelding);}

    public static void removeTilmelding(Tilmelding tilmelding) {tilmeldinger.remove(tilmelding);}

    //--------------------------------------------------------------------------------

    public static ArrayList<Udflugt> getUdflugter() {return new ArrayList<>(udflugter);}

    public static void addUdflugt(Udflugt udflugt) {udflugter.add(udflugt);}

    public static void removeUdflugt(Udflugt udflugt) {udflugter.remove(udflugt);}

    //--------------------------------------------------------------------------------


}
