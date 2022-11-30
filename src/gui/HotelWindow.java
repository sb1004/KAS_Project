package gui;

import application.controller.Controller;
import application.model.Hotel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.stage.*;


public class HotelWindow extends Stage {

    private Hotel hotel;

    private TextField txfHotelNavn, txfHotelAdresse, txfPrisFor1, txfPrisFor2;


    public HotelWindow(String title) {

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(true);



        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

    }

    private void initContent(GridPane pane) {
    Label lblHotelNavn = new Label("Hotelnavn: ");
    pane.add(lblHotelNavn, 1, 1);

    txfHotelNavn = new TextField();
    pane.add(txfHotelNavn, 1, 2);
    txfHotelNavn.setEditable(true);

    Label lblHotelAdresse = new Label("Adresse: ");
    pane.add(lblHotelAdresse, 1, 3);

    txfHotelAdresse = new TextField();
    pane.add(txfHotelAdresse, 1, 4);
    txfHotelAdresse.setEditable(true);

    Label lblPrisFor1 = new Label("Pris for enkelt værelse: ");
    pane.add(lblPrisFor1, 1, 5);

    txfPrisFor1 = new TextField();
    pane.add(txfPrisFor1, 1, 6);
    txfPrisFor1.setEditable(true);

    Label lblPrisFor2 = new Label("Pris for dobbelt værelse: ");
    pane.add(lblPrisFor2, 1, 7);

    txfPrisFor2 = new TextField();
    pane.add(txfPrisFor2, 1, 8);
    txfPrisFor2.setEditable(true);

    Button btnOpretHotel = new Button("Opret Hotel");
    pane.add(btnOpretHotel, 1, 10);
    btnOpretHotel.setOnAction(event -> this.opretHotelAction());

    Button btnOpretService = new Button("Opret Service");
    pane.add(btnOpretService, 1, 11);
    btnOpretService.setOnAction(event -> this.opretServiceAction());

    }

    private void opretServiceAction()
    {
        ServiceWindow dia = new ServiceWindow("Opret Service");
        dia.showAndWait();
    }

    private void opretHotelAction() {
        String navn = txfHotelNavn.getText().trim();
        String adresse = txfHotelAdresse.getText().trim();
        double prisEnkelt = Double.parseDouble(txfPrisFor1.getText().trim());
        double prisDouble = Double.parseDouble(txfPrisFor2.getText().trim());

        Controller.createHotel(navn, adresse, prisEnkelt, prisDouble);
    }
}
