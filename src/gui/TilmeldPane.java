package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Service;
import application.model.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    private final Label lblKonferencer = new Label("Konferencer:");
    private final ListView lvwKonferencer = new ListView();

    private final Label lblHotels = new Label("Hoteller:");
    private final ListView lvwHotels = new ListView();

    private final Label lblChooseServices = new Label("Vælg services:");
    private final ListView lvwChooseServices = new ListView();

    private final Label lblCompanion = new Label("Medbringer ledsager:");
    private final CheckBox chbCompanionYes = new CheckBox();
    private final Label lblNameOfCompanion = new Label("Indtast navn på ledsager");
    private final TextField txfCompanion = new TextField();

    private final Label lblExcursion = new Label("Udflugter for ledsager:");
    private final ListView lvwExcursion = new ListView();
    private final Label lblExcursionNoThankYou = new Label("Fravælg udflugter:");
    private final CheckBox chbExcursionNo = new CheckBox();

    private final Label lblTotalPrice = new Label("Samlet pris:");
    private final TextField txfTotalPrice = new TextField();

    private final Button btnRegister = new Button("Tilmeld");

    public TilmeldPane() {
        //Tiføjer et tekstfelt og label om fulde navn
        txfFullName.setEditable(true);
        txfFullName.setPrefWidth(200);
        txfFullName.setPrefHeight(30);
        this.add(lblFullName, 0, 0);
        this.add(txfFullName, 1, 0);

        //Tiføjer en checkbox om man er foredragsholder
        this.add(chbLecturerYes, 1, 1);
        this.add(lblLecturer, 0, 1);

        //Tiføjer et tekstfelt og label om adresse
        txfAddress.setEditable(true);
        txfAddress.setPrefWidth(200);
        txfAddress.setPrefHeight(30);
        this.add(lblAddress, 0, 2);
        this.add(txfAddress, 1, 2);

        //Tiføjer et tekstfelt og label om by
        txfCity.setEditable(true);
        txfCity.setPrefWidth(200);
        txfCity.setPrefHeight(30);
        this.add(lblCity, 0, 3);
        this.add(txfCity, 1, 3);

        //Tiføjer et tekstfelt og label om land
        txfCountry.setEditable(true);
        txfCountry.setPrefWidth(200);
        txfCountry.setPrefHeight(30);
        this.add(lblCountry, 0, 4);
        this.add(txfCountry, 1, 4);

        //Tilføjer et tekstfelt og en label om telefonnummer
        txfPhoneNumber.setEditable(true);
        txfPhoneNumber.setPrefWidth(200);
        txfPhoneNumber.setPrefHeight(30);
        this.add(lblPhoneNumer, 0, 5);
        this.add(txfPhoneNumber, 1, 5);

        //Tilføjer to kalendere
        this.add(lblArrival, 0, 8);
        this.add(dapArrival, 1, 8);
        this.add(lblDeparture, 0, 9);
        this.add(dapDeparture, 1, 9);

        //Tilføjer en checkbox til at angive, om man tager en ledsager med eller ej
        this.add(lblCompanion, 6, 0);
        this.add(chbCompanionYes, 7, 0);

        //Metoder, der umuliggør at skrive i feltet 'Navn på ledsager', hvis man ikke skal have ledsager med
        chbCompanionYes.setOnAction(event -> onCompanionSelected());

        //Tilføjer et tekstfelt og label om ledsager
        txfCompanion.setEditable(false);
        txfCompanion.setDisable(true);
        txfCompanion.setPrefWidth(200);
        txfCompanion.setPrefHeight(30);
        this.add(lblNameOfCompanion, 6, 1);
        this.add(txfCompanion, 7, 1);

        //Tilføjer et listview af udflugter
        this.add(lblExcursion, 6, 2);
        this.add(lvwExcursion, 7, 2, 1, 5);
        lvwExcursion.setPrefWidth(30);
        lvwExcursion.setPrefHeight(100);

        //Checkbox til 'nej tak' til udflugter
        this.add(lblExcursionNoThankYou, 6, 8);
        this.add(chbExcursionNo, 7, 8);

        //Liste af konferencer
        this.add(lblKonferencer, 0, 15);
        this.add(lvwKonferencer, 1, 15, 1, 5);
        lvwKonferencer.setPrefWidth(30);
        lvwKonferencer.setPrefHeight(100);

        //Liste af hoteller
        this.add(lblHotels, 0, 20);
        this.add(lvwHotels, 1, 20, 1, 5);
        lvwHotels.setPrefWidth(30);
        lvwHotels.setPrefHeight(100);

        //Liste af services til hoteller
        this.add(lblChooseServices, 0, 25);
        this.add(lvwChooseServices, 1, 25, 1, 5);
        lvwChooseServices.setPrefWidth(30);
        lvwChooseServices.setPrefHeight(100);

        //Tilføjer et tekstfelt og en label angående totalprisen
        txfTotalPrice.setEditable(false);
        txfTotalPrice.setPrefWidth(200);
        txfTotalPrice.setPrefHeight(29);
        this.add(lblTotalPrice, 6, 29);
        this.add(txfTotalPrice, 7, 29);

        //Tilføjer en knap og tilføjer en aktion til knappen
        this.add(btnRegister, 12, 29);
        //this.btnRegister.setOnAction(event -> this.registerAction());
    }

    private void onCompanionSelected() {
        txfCompanion.setEditable(chbCompanionYes.isSelected());
        txfCompanion.setDisable(!chbCompanionYes.isSelected());
    }
/*
    public void registerAction() {
        String navn = txfFullName.getText().trim();
        boolean foredragsholder = chbLecturerYes.isSelected();
        String adresse = txfAddress.getText().trim();
        String by = txfCity.getText().trim();
        String land = txfCity.getText().trim();
        int telefonnummer = Integer.parseInt(txfPhoneNumber.getText().trim());
        LocalDate ankomst = dapArrival.getValue();
        LocalDate afrejse = dapDeparture.getValue();
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        //Hotel hotel = lvwHotels.getSelectionModel().getSelectedItem();
        //Service service = lvwChooseServices.getSelectionModel().getSelectedItems();
        //boolean ledsager = chbCompanionYes.isSelected();
        //String ledsagerNavn = txfCompanion.getText().trim();
        //Udflugt udflugt = lvwExcursion.getSelectionModel().getSelectedItem();
        //boolean udflugterFravalgt = chbExcursionNo.isSelected();

        Controller.createTilmelding(ankomst, afrejse, foredragsholder, navn, adresse, by, land, telefonnummer, konference);
        updateListView();

        Controller.tilfoejHotelTilTilmelding();

    }
*/

}

