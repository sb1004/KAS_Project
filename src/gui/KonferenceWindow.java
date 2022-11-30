package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Ledsager;
import application.model.Udflugt;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import java.util.List;

public class KonferenceWindow extends Stage {
    private Konference konference;

    public KonferenceWindow(String title, Konference konference) {
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

    private ListView<Udflugt> lvwUdflugter;
    private ListView<Ledsager> lvwLedsagere;
    private ListView<Hotel> lvwHoteller;
    private ListView<String> lvwHotelGaester;



    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Button btnExit = new Button("Exit");
        pane.add(btnExit,2,3);
        btnExit.setOnAction(event -> this.exitAction());

        lvwUdflugter = new ListView<>();
        pane.add(lvwUdflugter,0,1);
        lvwUdflugter.getItems().setAll(konference.getUdflugter());
        lvwUdflugter.setPrefHeight(200);
        lvwUdflugter.setPrefWidth(200);

        lvwLedsagere = new ListView<>();
        pane.add(lvwLedsagere,1,1);
        lvwLedsagere.setPrefWidth(200);
        lvwLedsagere.setPrefHeight(200);

        lvwHoteller = new ListView<>();
        pane.add(lvwHoteller,0,2);
        lvwHoteller.getItems().setAll(konference.getHoteller());
        lvwHoteller.setPrefHeight(200);
        lvwHoteller.setPrefWidth(200);

        lvwHotelGaester = new ListView<>();
        pane.add(lvwHotelGaester,1,2);
        lvwHotelGaester.setPrefWidth(200);
        lvwHotelGaester.setPrefHeight(200);

        ChangeListener<Hotel> hotelListener = (ObservableValue, oldHot, newHot) -> this.selectedChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(hotelListener);

        ChangeListener<Udflugt> listener = (ObservableValue, oldUd, newUd) -> this.selectedChanged();
        lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    private void exitAction(){
        this.hide();
    }

    public void selectedChanged(){
        this.updateControls();
    }

    public void updateControls() {
        Udflugt udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
        if (udflugt != null) {
            lvwLedsagere.getItems().setAll(udflugt.getLedsagere());
        }

        Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            lvwHotelGaester.getItems().setAll(Controller.getHotelGaester(hotel));
        }
    }
}
