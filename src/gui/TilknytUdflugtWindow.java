package gui;

import application.controller.Controller;
import application.model.*;
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

public class TilknytUdflugtWindow extends Stage {

    /*
     * Et pop-up vindue, som giver mulighed for at tilknytte et service objekt til et hotel objekt
     * pre: Hotel objektet og Service objektet skal være konstrueret.
     */

    public TilknytUdflugtWindow(String title, Udflugt udflugt) {
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

    private ListView<Konference> lvwKonferencer;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Label og listview af konferencer

        Label lblKonferenceNavn = new Label("Konferencer: ");
        pane.add(lblKonferenceNavn, 1, 1);

        lvwKonferencer = new ListView<>();
        pane.add(lvwKonferencer, 1, 2, 1, 5);
        lvwKonferencer.setPrefWidth(150);
        lvwKonferencer.setPrefHeight(150);
        lvwKonferencer.getItems().setAll(Controller.getKonferencer());

        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);


        // Knapper med funktionalitet

        Button btnGem = new Button("Tilknyt Udflugt");
        pane.add(btnGem, 1, 7);
        btnGem.setOnAction(event -> this.tilknytUdflugtAction());

        Button btnSlet = new Button("Fjern Udflugt");
        pane.add(btnSlet, 2, 7);
        btnSlet.setOnAction(event -> this.fjernUdflugtAction());

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 3, 7);
        btnExit.setOnAction(event -> this.exitAction());
    }

    // Metoder der bruges af knapperne

    private void exitAction(){
        this.hide();
    }

    private void tilknytUdflugtAction() {
        Udflugt udflugt = this.udflugt;
        Controller.tilknytUdflugtTilKonference(lvwKonferencer.getSelectionModel().getSelectedItem(), udflugt);
        this.hide();
    }

    private void fjernUdflugtAction() {
        Udflugt udflugt = this.udflugt;
        Controller.fjernUdflugtFraKonference(lvwKonferencer.getSelectionModel().getSelectedItem(), udflugt);
        this.hide();
    }

    // Metoder der anvendes af changelistener

    public void updateControls() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
    }

    private void selectedKonferenceChanged() {
        this.updateControls();
    }
}
