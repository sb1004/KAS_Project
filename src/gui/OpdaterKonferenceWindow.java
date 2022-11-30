package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class OpdaterKonferenceWindow extends Stage {

    public OpdaterKonferenceWindow(String title, Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.konference = konference;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private Konference konference;
    private TextField txfKonferenceNavn, txfKonferenceAdresse, txfKonferencePris, txfAntalDage;

    private DatePicker dpStart;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblKonferenceNavn = new Label("Navn på konference: ");
        pane.add(lblKonferenceNavn, 1, 1);

        txfKonferenceNavn = new TextField();
        pane.add(txfKonferenceNavn, 2, 1);
        txfKonferenceNavn.setEditable(true);
        txfKonferenceNavn.setText(konference.getNavn());

        Label lblKonferenceAdresse = new Label("Adresse for konference: ");
        pane.add(lblKonferenceAdresse, 1, 2);

        txfKonferenceAdresse = new TextField();
        pane.add(txfKonferenceAdresse, 2, 2);
        txfKonferenceAdresse.setEditable(true);
        txfKonferenceAdresse.setText(konference.getAdresse());

        Label lblKonferenceDato = new Label("Dato for konference: ");
        pane.add(lblKonferenceDato, 1, 3);

        dpStart = new DatePicker();
        pane.add(dpStart, 2, 3);
        dpStart.setEditable(true);
        dpStart.setValue(konference.getStartdato());

        Label lblAntalDage = new Label("Antal dage: ");
        pane.add(lblAntalDage, 1, 4);

        txfAntalDage = new TextField();
        pane.add(txfAntalDage, 2, 4);
        txfAntalDage.setEditable(true);
        txfAntalDage.setText(String.valueOf(konference.getAntalDage()));


        Label lblKonferencePris = new Label("Pris for konference: ");
        pane.add(lblKonferencePris, 1, 5);

        txfKonferencePris = new TextField();
        pane.add(txfKonferencePris, 2, 5);
        txfKonferencePris.setEditable(true);
        txfKonferencePris.setText(String.valueOf(konference.getPris()));


        Button btnGem = new Button("Gem Ændringer");
        pane.add(btnGem, 1, 6);
        btnGem.setOnAction(event -> this.gemAction());

        Button btnSlet = new Button("Slet Konference");
        pane.add(btnSlet, 2, 6);
        btnSlet.setOnAction(event -> this.sletAction());

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 3, 6);
        btnExit.setOnAction(event -> this.exitAction());
    }

    private void exitAction(){
        this.hide();
    }

    private void gemAction() {
        Konference konference = this.konference;
        String navn = txfKonferenceNavn.getText().trim();
        String adresse = txfKonferenceAdresse.getText().trim();
        double pris = Double.parseDouble(txfKonferencePris.getText().trim());
        LocalDate startDato = dpStart.getValue();
        int antalDage = Integer.parseInt(txfAntalDage.getText().trim());

        Controller.updateKonference(konference, navn, adresse, pris, startDato, antalDage);

        this.hide();

    }

    private void sletAction() {
        Konference konference = this.konference;
        Controller.deleteKonference(konference);

        this.hide();
    }
}
