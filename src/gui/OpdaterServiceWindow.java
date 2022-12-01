package gui;

import application.controller.Controller;
import application.model.Konference;
import application.model.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class OpdaterServiceWindow extends Stage {

    /*
     * Et pop-up vindue der giver mulighed for at opdatere et allerede oprettet service objekt
     */

    public OpdaterServiceWindow(String title, Service service) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.service = service;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private Service service;

    private TextField txfServiceNavn, txfServicePris;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields med informationer om service objektet - textfields henter informationer fra det allerede oprettede objekt og sætter disse ind.

        Label lblServiceNavn = new Label("Navn på service: ");
        pane.add(lblServiceNavn, 1, 1);

        txfServiceNavn = new TextField();
        pane.add(txfServiceNavn, 2, 1);
        txfServiceNavn.setEditable(true);
        txfServiceNavn.setText(service.getNavn());

        Label lblServicePris = new Label("Pris for service: ");
        pane.add(lblServicePris, 1, 2);

        txfServicePris = new TextField();
        pane.add(txfServicePris, 2, 2);
        txfServicePris.setEditable(true);
        txfServicePris.setText(String.valueOf(service.getPris()));

        // Knapper der gør det muligt at gemme ændringer, slette objektet eller at lukke vinduet

        Button btnGem = new Button("Gem Ændringer");
        pane.add(btnGem, 1, 3);
        btnGem.setOnAction(event -> this.gemAction());

        Button btnSlet = new Button("Slet Service");
        pane.add(btnSlet, 2, 3);
        btnSlet.setOnAction(event -> this.sletAction());

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 3, 3);
        btnExit.setOnAction(event -> this.exitAction());
    }

    // Metoder til knapperne


    private void exitAction(){
        this.hide();
    }

    private void gemAction() {
        Service service = this.service;
        String navn = txfServiceNavn.getText().trim();
        double pris = Double.parseDouble(txfServicePris.getText().trim());

        Controller.updateService(service, navn, pris);
        this.hide();
    }

    private void sletAction() {
        Service service = this.service;
        Controller.deleteService(service);
        this.hide();
    }

}
