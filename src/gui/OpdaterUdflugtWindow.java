package gui;

import application.controller.Controller;
import application.model.Service;
import application.model.Udflugt;
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

public class OpdaterUdflugtWindow extends Stage {


    /*
     * Et pop-up vindue der giver mulighed for at opdatere et allerede oprettet udflugt objekt
     */

    public OpdaterUdflugtWindow(String title, Udflugt udflugt) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.udflugt = udflugt;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private Udflugt udflugt;


    private TextField txfUdflugtNavn, txfUdflugtPris;

    private DatePicker dpUdflugtStart;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields med informationer om udflugt objektet - textfields henter informationer fra det allerede oprettede objekt og sætter disse ind.

        Label lblUdflugtNavn = new Label("Navn på udflugt: ");
        pane.add(lblUdflugtNavn, 1, 1);

        txfUdflugtNavn = new TextField();
        pane.add(txfUdflugtNavn, 2, 1);
        txfUdflugtNavn.setEditable(true);
        txfUdflugtNavn.setText(udflugt.getNavn());

        Label lblUdflugtPris = new Label("Pris for udflugt: ");
        pane.add(lblUdflugtPris, 1, 2);

        txfUdflugtPris = new TextField();
        pane.add(txfUdflugtPris, 2, 2);
        txfUdflugtPris.setEditable(true);
        txfUdflugtPris.setText(String.valueOf(udflugt.getPris()));

        Label lblUdflugtStartDato = new Label("Dato for udflugt: ");
        pane.add(lblUdflugtStartDato, 1, 3);

        dpUdflugtStart = new DatePicker();
        pane.add(dpUdflugtStart, 2, 3);
        dpUdflugtStart.setEditable(true);
        dpUdflugtStart.setValue(udflugt.getDato());


        // Knapper der gør det muligt at gemme ændringer, slette objektet eller at lukke vinduet

        Button btnGem = new Button("Gem Ændringer");
        pane.add(btnGem, 1, 4);
        btnGem.setOnAction(event -> this.gemAction());

        Button btnSlet = new Button("Slet Service");
        pane.add(btnSlet, 2, 4);
        btnSlet.setOnAction(event -> this.sletAction());

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 3, 4);
        btnExit.setOnAction(event -> this.exitAction());
    }

    // Metoder til knapperne


    private void exitAction(){
        this.hide();
    }

    private void gemAction() {
        Udflugt udflugt = this.udflugt;
        String navn = txfUdflugtNavn.getText().trim();
        double pris = Double.parseDouble(txfUdflugtPris.getText().trim());
        LocalDate dato = dpUdflugtStart.getValue();

        Controller.updateUdflugt(udflugt, navn, dato, pris);
        this.hide();
    }

    private void sletAction() {
        Udflugt udflugt = this.udflugt;
        Controller.deleteUdflugt(udflugt);
        this.hide();
    }
}
