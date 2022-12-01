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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilknytServiceWindow extends Stage {

    /*
     * Et pop-up vindue, som giver mulighed for at tilknytte et service objekt til et hotel objekt
     * pre: Hotel objektet og Service objektet skal være konstrueret.
     */

    public TilknytServiceWindow(String title, Service service) {
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

    private ListView<Hotel> lvwHoteller;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Label og listview af hoteller

        Label lblHotelNavn = new Label("Hoteller: ");
        pane.add(lblHotelNavn, 1, 1);

        lvwHoteller = new ListView<>();
        pane.add(lvwHoteller, 1, 2, 1, 5);
        lvwHoteller.setPrefWidth(150);
        lvwHoteller.setPrefHeight(150);
        lvwHoteller.getItems().setAll(Controller.getHoteller());

        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener);


        // Knapper med funktionalitet

        Button btnGem = new Button("Tilknyt Service");
        pane.add(btnGem, 1, 7);
        btnGem.setOnAction(event -> this.tilknytServiceAction());

        Button btnSlet = new Button("Fjern Service");
        pane.add(btnSlet, 2, 7);
        btnSlet.setOnAction(event -> this.fjernServiceAction());

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 3, 7);
        btnExit.setOnAction(event -> this.exitAction());
    }

    // Metoder der bruges af knapperne

    private void exitAction(){
        this.hide();
    }

    private void tilknytServiceAction() {
        Service service = this.service;
        Controller.tilfoejServiceTilHotel(lvwHoteller.getSelectionModel().getSelectedItem(),service);
        this.hide();
    }

    private void fjernServiceAction() {
        Service service = this.service;
        Controller.fjernServiceFraHotel(lvwHoteller.getSelectionModel().getSelectedItem(), service);
        this.hide();
    }

    // Metoder der anvendes af changelistener

    public void updateControls() {
        Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
    }

    private void selectedHotelChanged() {
        this.updateControls();
    }
}
