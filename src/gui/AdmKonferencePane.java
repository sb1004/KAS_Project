package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import storage.Storage;

import java.awt.*;
import java.time.LocalDate;

public class AdmKonferencePane extends GridPane {

    /*
     * Et pane som indeholder konferencer, hoteller og services og giver mulighed for at oprette, opdatere og tilknytte selvsamme objekter til hinanden
     */


    private TextField txfKonferenceNavn, txfKonferenceAdresse, txfKonferencePris, txfAntalDage, txfHotelNavn, txfHotelAdresse, txfPrisFor1, txfPrisFor2, txfServiceNavn, txfServicePris;

    private ListView<Hotel> lvwHoteller;
    private ListView<Service> lvwServices;
    private ListView<Konference> lvwKonferencer;

    private DatePicker dpStart;


    public AdmKonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //--------------------------------------------------------------------------------
        // Konference del af pane

        // Textfields med informationer

        Label lblKonferenceNavn = new Label("Navn på konference: ");
        this.add(lblKonferenceNavn, 1, 1);

        txfKonferenceNavn = new TextField();
        this.add(txfKonferenceNavn, 2, 1);
        txfKonferenceNavn.setEditable(true);

        Label lblKonferenceAdresse = new Label("Adresse for konference: ");
        this.add(lblKonferenceAdresse, 1, 2);

        txfKonferenceAdresse = new TextField();
        this.add(txfKonferenceAdresse, 2, 2);
        txfKonferenceAdresse.setEditable(true);

        Label lblKonferenceDato = new Label("Dato for konference: ");
        this.add(lblKonferenceDato, 1, 3);

        dpStart = new DatePicker();
        this.add(dpStart, 2, 3);
        dpStart.setEditable(true);

        Label lblAntalDage = new Label("Antal dage: ");
        this.add(lblAntalDage, 1, 4);

        txfAntalDage = new TextField();
        this.add(txfAntalDage, 2, 4);
        txfAntalDage.setEditable(true);

        Label lblKonferencePris = new Label("Pris for konference: ");
        this.add(lblKonferencePris, 1, 5);

        txfKonferencePris = new TextField();
        this.add(txfKonferencePris, 2, 5);

        // Knapper med funktionalitet relaterende til konference

        Button btnOpretKonference = new Button("Opret Konference");
        this.add(btnOpretKonference, 1, 6);
        btnOpretKonference.setOnAction(event -> this.opretKonferenceAction());

        Button btnOpdaterKonference = new Button("Opdater Konference");
        this.add(btnOpdaterKonference, 2, 6);
        btnOpdaterKonference.setOnAction(event -> this.opdaterKonferenceAction());


        // Et listview af oprettede konferencer

        lvwKonferencer = new ListView<>();
        this.add(lvwKonferencer, 3, 1, 1, 5);
        lvwKonferencer.setPrefWidth(150);
        lvwKonferencer.setPrefHeight(150);
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());

        ChangeListener<Konference> listener2 = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener2);

        //--------------------------------------------------------------------------------
        // Hotel del af pane

        // Textfields med information om hotellet

        Label lblHotelNavn = new Label("Hotelnavn: ");
        this.add(lblHotelNavn, 1, 8);

        txfHotelNavn = new TextField();
        this.add(txfHotelNavn, 2, 8);
        txfHotelNavn.setEditable(true);

        Label lblHotelAdresse = new Label("Adresse: ");
        this.add(lblHotelAdresse, 1, 9);

        txfHotelAdresse = new TextField();
        this.add(txfHotelAdresse, 2, 9);
        txfHotelAdresse.setEditable(true);

        Label lblPrisFor1 = new Label("Pris for enkelt værelse: ");
        this.add(lblPrisFor1, 1, 10);

        txfPrisFor1 = new TextField();
        this.add(txfPrisFor1, 2, 10);
        txfPrisFor1.setEditable(true);

        Label lblPrisFor2 = new Label("Pris for dobbelt værelse: ");
        this.add(lblPrisFor2, 1, 11);

        txfPrisFor2 = new TextField();
        this.add(txfPrisFor2, 2, 11);
        txfPrisFor2.setEditable(true);

        // Listview af oprettede hoteller

        lvwHoteller = new ListView<>();
        this.add(lvwHoteller, 3, 8, 1, 5);
        lvwHoteller.setPrefWidth(150);
        lvwHoteller.setPrefHeight(150);
        lvwHoteller.getItems().setAll(Controller.getHoteller());

        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener);

        // Knapper med funktionalitet relaterende til hotel

        Button btnOpretHotel = new Button("Opret Hotel");
        this.add(btnOpretHotel, 1, 13);
        btnOpretHotel.setOnAction(event -> this.opretHotelAction());

        Button btnOpdaterHotel = new Button("Opdater Hotel");
        this.add(btnOpdaterHotel, 2, 13);
        btnOpdaterHotel.setOnAction(event -> this.opdaterHotelAction());

        Button btnTilknytHotel = new Button("Tilknyt Hotel");
        this.add(btnTilknytHotel, 3, 13);
        btnTilknytHotel.setOnAction(event -> this.tilknytHotelAction());

        //--------------------------------------------------------------------------------
        // Service del af pane

        // Textfields med information om service

        Label lblServiceNavn = new Label("Navn på service: ");
        this.add(lblServiceNavn, 1, 14);

        txfServiceNavn = new TextField();
        this.add(txfServiceNavn, 2, 14);
        txfServiceNavn.setEditable(true);

        Label lblServicePris = new Label("Pris for service: ");
        this.add(lblServicePris, 1, 15);

        txfServicePris = new TextField();
        this.add(txfServicePris, 2, 15);
        txfServicePris.setEditable(true);

        // Listview af oprettede services

        lvwServices = new ListView<>();
        this.add(lvwServices, 3, 14, 1, 4);
        lvwServices.setPrefWidth(150);
        lvwServices.setPrefHeight(150);
        lvwServices.getItems().setAll(Controller.getServices());

        ChangeListener<Service> listener3 = (ov, oldHotel, newHotel) -> this.selectedServiceChanged();
        lvwServices.getSelectionModel().selectedItemProperty().addListener(listener3);

        // Knapper med funktionalitet relaterende til service

        Button btnOpretService = new Button("Opret Service");
        this.add(btnOpretService, 1, 18);
        btnOpretService.setOnAction(event -> this.opretServiceAction());

        Button btnOpdaterService = new Button("Opdater Service");
        this.add(btnOpdaterService, 2, 18);
        btnOpdaterService.setOnAction(event -> this.opdaterServiceAction());

        Button btnTilknytService = new Button("Tilknyt Service");
        this.add(btnTilknytService, 3, 18);
        btnTilknytService.setOnAction(event -> this.tilknytServiceAction());
    }

    //--------------------------------------------------------------------------------

    // Metoder til konference

    private void opretKonferenceAction() {
        String navn = txfKonferenceNavn.getText().trim();
        String adresse = txfKonferenceAdresse.getText().trim();
        double pris = Double.parseDouble(txfKonferencePris.getText().trim());
        LocalDate startDato = dpStart.getValue();
        int antalDage = Integer.parseInt(txfAntalDage.getText().trim());

        Controller.createKonference(navn, adresse, pris, startDato, antalDage);
        updateListView();
        rydFelter();
    }

    private void opdaterKonferenceAction() {
        OpdaterKonferenceWindow dia = new OpdaterKonferenceWindow("Opdater Konference", this.lvwKonferencer.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateListView();
    }

    //--------------------------------------------------------------------------------

    // Metoder til hotel

    private void opretHotelAction() {
        String navn = txfHotelNavn.getText().trim();
        String adresse = txfHotelAdresse.getText().trim();
        double prisEnkelt = Double.parseDouble(txfPrisFor1.getText().trim());
        double prisDouble = Double.parseDouble(txfPrisFor2.getText().trim());

        Controller.createHotel(navn, adresse, prisEnkelt, prisDouble);
        updateListView();
        rydFelter();
    }

    public void tilknytHotelAction() {
        TilknytHotelWindow dia = new TilknytHotelWindow("Tilknyt Hotel", this.lvwHoteller.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateListView();
    }

    private void opdaterHotelAction() {
        OpdaterHotelWindow dia = new OpdaterHotelWindow("Opdater Hotel", this.lvwHoteller.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateListView();
    }

    //--------------------------------------------------------------------------------

    // Metoder til service

    private void opretServiceAction() {
        String navn = txfServiceNavn.getText().trim();
        double pris = Double.parseDouble(txfServicePris.getText().trim());

        Controller.createService(navn, pris);
        updateListView();
        rydFelter();
    }

    public void tilknytServiceAction() {
        TilknytServiceWindow dia = new TilknytServiceWindow("Tilknyt Service", this.lvwServices.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateListView();
    }

    private void opdaterServiceAction() {
        OpdaterServiceWindow dia = new OpdaterServiceWindow("Opdater Service", this.lvwServices.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateListView();

    }
    //--------------------------------------------------------------------------------

    // Metoder til changelistener

    private void selectedHotelChanged() {
        this.updateControls();
    }

    private void selectedKonferenceChanged() {
        this.updateControls();
    }

    private void selectedServiceChanged() {
        this.updateControls();
    }


    public void updateListView() {
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());
        lvwHoteller.getItems().setAll(Controller.getHoteller());
        lvwServices.getItems().setAll(Controller.getServices());
    }

    public void updateControls() {
        Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        Service service = lvwServices.getSelectionModel().getSelectedItem();
    }


    //--------------------------------------------------------------------------------

    // Hjælpemetode der rydder alle felter på pane

    private void rydFelter() {
        txfKonferenceNavn.clear();
        txfKonferenceAdresse.clear();
        txfKonferencePris.clear();
        txfAntalDage.clear();
        txfHotelNavn.clear();
        txfHotelAdresse.clear();
        txfPrisFor1.clear();
        txfPrisFor2.clear();
        txfServiceNavn.clear();
        txfServicePris.clear();
        dpStart.setValue(null);
    }
}
