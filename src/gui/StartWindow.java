package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {


    @Override
    public void init() {
        Controller.init();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Konference Administrations System");
        BorderPane pane = new BorderPane();
        this.initContent(pane);


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane (TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabTilmeld = new Tab("Tilmeld");
        tabPane.getTabs().add(tabTilmeld);

        TilmeldPane tilmeldPane = new TilmeldPane();
        tabTilmeld.setContent(tilmeldPane);
       // tabTilmeld.setOnSelectionChanged(event -> tilmeldPane.updateControls());



        Tab tabKonference = new Tab("Konference");
        tabPane.getTabs().add(tabKonference);

        KonferencePane konferencePane = new KonferencePane();
        tabKonference.setContent(konferencePane);
        tabKonference.setOnSelectionChanged(event -> konferencePane.updateControls());




        Tab tabAdmKonference = new Tab("Administrer Konference");
        tabPane.getTabs().add(tabAdmKonference);

        AdmKonferencePane admKonferencePane = new AdmKonferencePane();
        tabAdmKonference.setContent(admKonferencePane);
        tabAdmKonference.setOnSelectionChanged(event -> admKonferencePane.updateControls());


    }



}
