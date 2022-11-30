package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilknytHotelWindow extends Stage {

    public TilknytHotelWindow(String title, Hotel hotel) {
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

    private ListView<Konference> lvwKonferencer;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblHotelNavn = new Label("Konferencer: ");
        pane.add(lblHotelNavn, 1, 1);

        lvwKonferencer = new ListView<>();
        pane.add(lvwKonferencer, 3, 1, 1, 5);
        lvwKonferencer.setPrefWidth(150);
        lvwKonferencer.setPrefHeight(150);
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());

        ChangeListener<Konference> listener2 = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener2);


        Button btnGem = new Button("Tilknyt Hotel");
        pane.add(btnGem, 1, 6);
        btnGem.setOnAction(event -> this.tilknytHotelAction());

        Button btnSlet = new Button("Fjern Hotel");
        pane.add(btnSlet, 2, 6);
        btnSlet.setOnAction(event -> this.fjernHotelAction());

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 3, 6);
        btnExit.setOnAction(event -> this.exitAction());
    }

    private void exitAction(){
        this.hide();
    }

    private void tilknytHotelAction() {

        Hotel hotel = this.hotel;
        Controller.tilfoejHotelTilKonference(lvwKonferencer.getSelectionModel().getSelectedItem(), hotel);
        this.hide();
    }

    private void fjernHotelAction() {
        Hotel hotel = this.hotel;
        Controller.fjernHotelFraKonference(lvwKonferencer.getSelectionModel().getSelectedItem(), hotel);
        this.hide();
    }

    public void updateControls() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
    }

    private void selectedKonferenceChanged() {
        this.updateControls();
    }
}
