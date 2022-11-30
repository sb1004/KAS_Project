package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class OpdaterHotelWindow extends Stage {


    public OpdaterHotelWindow(String title, Hotel hotel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.hotel = hotel;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
        }

        private Hotel hotel;

        private TextField txfHotelNavn, txfHotelAdresse, txfPrisFor1, txfPrisFor2;


        private void initContent(GridPane pane) {
            pane.setPadding(new Insets(20));
            pane.setHgap(20);
            pane.setVgap(10);
            pane.setGridLinesVisible(false);

            Label lblHotelNavn = new Label("Hotelnavn: ");
            pane.add(lblHotelNavn, 1, 1);

            txfHotelNavn = new TextField();
            pane.add(txfHotelNavn, 2, 1);
            txfHotelNavn.setEditable(true);
            txfHotelNavn.setText(hotel.getNavn());

            Label lblHotelAdresse = new Label("Adresse: ");
            pane.add(lblHotelAdresse, 1, 2);

            txfHotelAdresse = new TextField();
            pane.add(txfHotelAdresse, 2, 2);
            txfHotelAdresse.setEditable(true);
            txfHotelAdresse.setText(hotel.getAdresse());

            Label lblPrisFor1 = new Label("Pris for enkelt værelse: ");
            pane.add(lblPrisFor1, 1, 3);

            txfPrisFor1 = new TextField();
            pane.add(txfPrisFor1, 2, 3);
            txfPrisFor1.setEditable(true);
            txfPrisFor1.setText(String.valueOf(hotel.getPrisEnkelt()));

            Label lblPrisFor2 = new Label("Pris for dobbelt værelse: ");
            pane.add(lblPrisFor2, 1, 4);

            txfPrisFor2 = new TextField();
            pane.add(txfPrisFor2, 2, 4);
            txfPrisFor2.setEditable(true);
            txfPrisFor2.setText(String.valueOf(hotel.getPrisDouble()));


            Button btnGem = new Button("Gem Ændringer");
            pane.add(btnGem, 1, 6);
            btnGem.setOnAction(event -> this.gemAction());

            Button btnSlet = new Button("Slet Hotel");
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
            Hotel hotel = this.hotel;
            String navn = txfHotelNavn.getText().trim();
            String adresse = txfHotelAdresse.getText().trim();
            double prisEnkelt = Double.parseDouble(txfPrisFor1.getText().trim());
            double prisDouble = Double.parseDouble(txfPrisFor2.getText().trim());

            Controller.updateHotel(hotel, navn, adresse, prisEnkelt, prisDouble);
            this.hide();
        }

        private void sletAction() {
            Hotel hotel = this.hotel;
            Controller.deleteHotel(hotel);
            this.hide();
        }
}

