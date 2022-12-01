package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import storage.Storage;

public class KonferencePane extends GridPane {
    private ListView<Konference> lvwKonferencer;
    private ListView<String> lvwDeltagere;

    private TextField txfKonferenceNavn, txfKonferenceAdresse, txfKonferencePris, txfAntalDage, txfstartDato;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblKonf = new Label("Konferencer");
        this.add(lblKonf, 1, 0);

        lvwKonferencer = new ListView<>();
        this.add(lvwKonferencer, 1, 1);
        lvwKonferencer.setPrefHeight(200);
        lvwKonferencer.setPrefWidth(200);
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());

        Label lblDeltager = new Label("Deltagere på konferencen");
        this.add(lblDeltager, 2, 0);

        lvwDeltagere = new ListView<>();
        this.add(lvwDeltagere,2 ,1);
        lvwDeltagere.setPrefHeight(200);
        lvwDeltagere.setPrefWidth(200);

        ChangeListener<Konference> listener = (observableValue, oldKonf, newKonf) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        Button btnInfo = new Button("Mere info");
        this.add(btnInfo,1,2);
        btnInfo.setOnAction(event -> this.infoAction());

        Button btnTest = new Button("Update List");
        this.add(btnTest,1,3);
        btnTest.setOnAction(event -> this.updateList());

        Label lblKonferenceNavn = new Label("Konferencens navn:");
        this.add(lblKonferenceNavn, 2, 2);

        txfKonferenceNavn = new TextField();
        this.add(txfKonferenceNavn, 2, 3);
        txfKonferenceNavn.setEditable(false);

        Label lblKonferenceAdresse = new Label("Konferencens adresse:");
        this.add(lblKonferenceAdresse, 2, 4);

        txfKonferenceAdresse = new TextField();
        this.add(txfKonferenceAdresse, 2, 5);
        txfKonferenceAdresse.setEditable(false);

        Label lblKonferenceStartDato = new Label("Konferencens Startdato:");
        this.add(lblKonferenceStartDato, 2, 6);

        txfstartDato = new TextField();
        this.add(txfstartDato, 2, 7);
        txfstartDato.setEditable(false);

        Label lblKonferenceAntalDage = new Label("Antal dage konferencen løber over:");
        this.add(lblKonferenceAntalDage, 2, 8);

        txfAntalDage = new TextField();
        this.add(txfAntalDage, 2, 9);
        txfAntalDage.setEditable(false);

        Label lblKonferencePris = new Label("Konferencens pris:");
        this.add(lblKonferencePris, 2, 10);

        txfKonferencePris = new TextField();
        this.add(txfKonferencePris, 2, 11);
        txfKonferencePris.setEditable(false);

    }


    public void selectedKonferenceChanged() {
        this.updateControls();
    }

    public void updateList(){
        lvwKonferencer.getItems().setAll(Storage.getKonferencer());
    }

    public void updateControls() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            lvwDeltagere.getItems().setAll(Controller.getDeltagere(konference));
            txfKonferenceNavn.setText(konference.getNavn());
            txfKonferenceAdresse.setText(konference.getAdresse());
            txfstartDato.setText(String.valueOf(konference.getStartdato()));
            txfAntalDage.setText(String.valueOf(konference.getAntalDage()));
            txfKonferencePris.setText(String.valueOf(konference.getPris()));
        }
    }

    public void infoAction(){
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        KonferenceWindow konferenceWindow = new KonferenceWindow("Mere info", konference);
        konferenceWindow.showAndWait();
    }
}
