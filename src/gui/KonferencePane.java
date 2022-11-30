package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import storage.Storage;

public class KonferencePane extends GridPane {
    private ListView<Konference> lvwKonferencer;
    private ListView<String> lvwDeltagere;

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

        Label lblDeltager = new Label("Deltagere");
        this.add(lblDeltager, 2, 0);

        lvwDeltagere = new ListView<>();
        this.add(lvwDeltagere,2 ,1);
        lvwDeltagere.setPrefHeight(200);
        lvwDeltagere.setPrefWidth(200);

        ChangeListener<Konference> listener = (observableValue, oldKonf, newKonf) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        Button btnInfo = new Button("Mere info");
        this.add(btnInfo,3,1);
        btnInfo.setOnAction(event -> this.infoAction());

        Button btnTest = new Button("Update List");
        this.add(btnTest,3,2);
        btnTest.setOnAction(event -> this.updateList());
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
        }
    }

    public void infoAction(){
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        KonferenceWindow dia = new KonferenceWindow("Mere info", konference);
        dia.showAndWait();
    }
}
