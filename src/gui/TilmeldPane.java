package gui;

import application.controller.Controller;
import application.model.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import storage.Storage;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class TilmeldPane extends GridPane {
    private final Label lblFullName = new Label("Fulde navn:");
    private final TextField txfFullName = new TextField();

    private final Label lblLecturer = new Label("Er du foredragsholder?:");
    private final CheckBox chbLecturerYes = new CheckBox();

    private final Label lblAddress = new Label("Adresse:");
    private final TextField txfAddress = new TextField();

    private final Label lblCity = new Label("By:");
    private final TextField txfCity = new TextField();

    private final Label lblCountry = new Label("Land:");
    private final TextField txfCountry = new TextField();

    private final Label lblPhoneNumer = new Label("Telefonnummer:");
    private final TextField txfPhoneNumber = new TextField();

    private final Label lblArrival = new Label("Ankomstdato:");
    private final DatePicker dapArrival = new DatePicker();

    private final Label lblDeparture = new Label("Afrejsedato:");
    private final DatePicker dapDeparture = new DatePicker();

    private final Label lblKonferencer = new Label("Vælg konference:");
    private final ListView<Konference> lvwKonferencer = new ListView();

    private final Label lblTotalPrice = new Label("Samlet pris:");
    private final TextField txfTotalPrice = new TextField();

    private final Button btnTilmeld = new Button("Tilmeld");

    private final Label lblCompanion = new Label("Medbringer ledsager:");
    private final CheckBox chbCompanionYes = new CheckBox();
    private final Label lblNameOfCompanion = new Label("Indtast navn på ledsager");
    private final TextField txfCompanion = new TextField();

    private final Label lblExcursion = new Label("Udflugter for ledsager:");
    private final ListView<Udflugt> lvwExcursion = new ListView();
    private final Label lblExcursionNoThankYou = new Label("Fravælg udflugter:");
    private final CheckBox chbExcursionNo = new CheckBox();

    private final Button btnAddCompanion = new Button("Tilføj ledsager");

    private final Label lblHotels = new Label("Hoteller:");
    private final ListView<Hotel> lvwHotels = new ListView();

    private final Label lblChooseServices = new Label("Vælg services:");
    private final ListView<Service> lvwServices = new ListView();

    private final Label lblNoHotel = new Label("Fravælg hotel:");
    private final CheckBox chbNoHotel = new CheckBox();
    private final Button btnAdd = new Button("Tilføj hotel og services");

    private String ledsagerNavn;

    public TilmeldPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        //Tiføjer et tekstfelt og label om fulde navn
        txfFullName.setEditable(true);
        txfFullName.setPrefWidth(200);
        txfFullName.setPrefHeight(30);
        this.add(lblFullName, 0, 1);
        this.add(txfFullName, 1, 1);

        //Tiføjer en checkbox om man er foredragsholder
        this.add(lblLecturer, 0, 2);
        this.add(chbLecturerYes, 1, 2);

        //Tiføjer et tekstfelt og label om adresse
        txfAddress.setEditable(true);
        txfAddress.setPrefWidth(200);
        txfAddress.setPrefHeight(30);
        this.add(lblAddress, 0, 3);
        this.add(txfAddress, 1, 3);

        //Tiføjer et tekstfelt og label om by
        txfCity.setEditable(true);
        txfCity.setPrefWidth(200);
        txfCity.setPrefHeight(30);
        this.add(lblCity, 0, 4);
        this.add(txfCity, 1, 4);

        //Tiføjer et tekstfelt og label om land
        txfCountry.setEditable(true);
        txfCountry.setPrefWidth(200);
        txfCountry.setPrefHeight(30);
        this.add(lblCountry, 0, 5);
        this.add(txfCountry, 1, 5);

        //Tilføjer et tekstfelt og en label om telefonnummer
        txfPhoneNumber.setEditable(true);
        txfPhoneNumber.setPrefWidth(200);
        txfPhoneNumber.setPrefHeight(30);
        this.add(lblPhoneNumer, 0, 6);
        this.add(txfPhoneNumber, 1, 6);

        //Tilføjer to kalendere
        this.add(lblArrival, 0, 9);
        this.add(dapArrival, 1, 9);
        this.add(lblDeparture, 0, 10);
        this.add(dapDeparture, 1, 10);

        //Liste af konferencer
        this.add(lblKonferencer, 0, 16);
        this.add(lvwKonferencer, 1, 16, 1, 5);
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());
        lvwKonferencer.setPrefWidth(30);
        lvwKonferencer.setPrefHeight(100);

        //Tilføjer et tekstfelt og en label angående totalprisen
        txfTotalPrice.setEditable(false);
        txfTotalPrice.setPrefWidth(200);
        txfTotalPrice.setPrefHeight(29);
        this.add(lblTotalPrice, 0, 25);
        this.add(txfTotalPrice, 1, 25);

        //Tilføjer knappen 'Tilmeld' og tilføjer en aktion til knappen
        this.add(btnTilmeld, 3, 28);
        this.btnTilmeld.setOnAction(event -> this.registerActionOnTilmeld());

        //Tilføjer en checkbox til at angive, om man tager en ledsager med eller ej
        this.add(lblCompanion, 10, 1);
        this.add(chbCompanionYes, 11, 1);

        //Metoder, der umuliggør at skrive i feltet 'Navn på ledsager', hvis man ikke skal have ledsager med
        chbCompanionYes.setOnAction(event -> onCompanionSelected());

        //Tilføjer et tekstfelt og label om ledsager
        txfCompanion.setEditable(false);
        txfCompanion.setDisable(true);
        txfCompanion.setPrefWidth(200);
        txfCompanion.setPrefHeight(30);
        this.add(lblNameOfCompanion, 10, 2);
        this.add(txfCompanion, 11, 2);

        //Tilføjer et listview af udflugter
        this.add(lblExcursion, 10, 3);
        this.add(lvwExcursion, 11, 3, 1, 5);
        lvwExcursion.getItems().setAll(Controller.getUdflugter());
        lvwExcursion.setPrefWidth(30);
        lvwExcursion.setPrefHeight(100);

        //Checkbox til 'fravælg udflugter' til udflugter
        this.add(lblExcursionNoThankYou, 10, 9);
        this.add(chbExcursionNo, 11, 9);

        //Tilføjer knappen 'tilføj ledsager'
        this.add(btnAddCompanion, 12, 10);

        //Liste af hoteller
        this.add(lblHotels, 10, 14);
        this.add(lvwHotels, 11, 14, 1, 5);
        lvwHotels.getItems().setAll(Controller.getHoteller());
        lvwHotels.setPrefWidth(30);
        lvwHotels.setPrefHeight(100);

        //CheckBox til 'Fravælg hotel' til hotel
        this.add(lblNoHotel, 10, 21);
        this.add(chbNoHotel, 11, 21);

        //Liste af services til hoteller
        this.add(lblChooseServices, 10, 23);
        this.add(lvwServices, 11, 23, 1, 5);
        lvwServices.getItems().setAll(Controller.getServices());
        lvwServices.setPrefWidth(30);
        lvwServices.setPrefHeight(100);

        //Tilføjer knappen 'Tilføj'
        this.add(btnAdd, 12, 28);
    }

    private void onCompanionSelected() {
        txfCompanion.setEditable(chbCompanionYes.isSelected());
        txfCompanion.setDisable(!chbCompanionYes.isSelected());
        lvwExcursion.setEditable(chbCompanionYes.isSelected());
        lvwExcursion.setDisable(!chbCompanionYes.isSelected());
    }

    public void registerActionOnTilmeld() {
        String navn = txfFullName.getText().trim();
        boolean foredragsholder = chbLecturerYes.isSelected();
        String adresse = txfAddress.getText().trim();
        String by = txfCity.getText().trim();
        String land = txfCity.getText().trim();
        int telefonnummer = Integer.parseInt(txfPhoneNumber.getText().trim());
        LocalDate ankomst = dapArrival.getValue();
        LocalDate afrejse = dapDeparture.getValue();
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();

        Controller.createTilmelding(ankomst, afrejse, foredragsholder, navn, adresse, by, land, telefonnummer, konference);
        System.out.println(Storage.getTilmeldinger());
    }

    public void TilfoejLedsagerTilTilmeldingAction(Tilmelding tilmelding) {
        boolean ledsager = chbCompanionYes.isSelected();
        if(ledsager) {
            ledsagerNavn = txfCompanion.getText().trim();
        }
        //Tilmelding tilmelding = this.tilmelding;
        Udflugt udflugt = lvwExcursion.getSelectionModel().getSelectedItem();

        //Controller.tilfoejLedsagerToTilmelding();
    }

    public void tilfoejHotelOgServicesToTilmelding() {
        Hotel hotel = lvwHotels.getSelectionModel().getSelectedItem();
        Service service = lvwServices.getSelectionModel().getSelectedItem();

        //Controller.addHotelOgServices(hotel, service);  //Lav metode i controller
    }

}

