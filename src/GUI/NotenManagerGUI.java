package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotenManagerGUI extends Application {

    Stage fenster;
    Scene login, dozentScene;
    TableView<StudentenID> studentenTable = new TableView<>();

    private String sNachname = "Nachname";
    private String sVorname = "Vorname";
    private Double sNote;

    private double[] validNote = {1.0,1.3,1.7,2.0,2.3,2.7,3.0,3.3,3.7,4.0,5.0};
    private List valid = toList(validNote);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        fenster = primaryStage;
        fenster.setTitle("Noten Manager Alpha 0.1");
        fenster.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //--- Login Fenster ---

        //TODO Nutzername und Passwort aus der Datenbank abgreifen und validieren.
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 10, 15, 10));
        gridPane.setHgap(8);
        gridPane.setVgap(10);

        Label loginLabel = new Label ("Bitte Logindaten eingeben");
        GridPane.setConstraints(loginLabel, 1, 0);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Benutzername");
        GridPane.setConstraints(nameInput, 1,1);

        TextField pwInput = new TextField();
        pwInput.setPromptText("Passwort");
        GridPane.setConstraints(pwInput, 1, 2);

        Button anmButton = new Button("Anmelden");
        anmButton.setOnAction(e-> {
            validateInput(nameInput, nameInput.getText(), pwInput, pwInput.getText() );
        });

        // Login Layout
        GridPane.setConstraints(anmButton, 1, 4);

        gridPane.getChildren().addAll(loginLabel, nameInput, pwInput, anmButton);

        login = new Scene(gridPane, 190,160);

        //--- Dozenteninterface ---

        //--- MENU BAR ---
        //Main Menu
        Menu mainMenu = new Menu("", new ImageView(new Image("resources/menu.png")));
        Menu helpMenu = new Menu("Hilfe");

        //Menu items
        MenuItem logout = new MenuItem("Abmelden");
        logout.setOnAction(e -> {
            boolean ergebnis = BestaetigungsBox.display("Abmelden?", "Sind Sie sicher, dass Sie sich abmelden möchten?");
            if(ergebnis) {
                fenster.setScene(login);
                fenster.centerOnScreen();
            }
        });

        MenuItem close = new MenuItem("Beenden");
        close.setOnAction(e -> closeProgram());

        MenuItem userDoc = new MenuItem("Dokumentation");
        userDoc.setOnAction(e -> {
            try {Desktop.getDesktop().open(new File("src/resources/UserDokumentation.pdf"));}
            catch (IOException e1){e1.printStackTrace();}
        });

        mainMenu.getItems().addAll(logout,close);
        helpMenu.getItems().addAll(userDoc);

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(mainMenu,helpMenu);


        //Klausurfilter Elemente
        //TODO Import Modulnamen und Daten aus Datenbank! aktuell Dummyeinträge!
        //TODO Daten nach dem auswählen eines Moduls entsprechend des Moduls importieren
        ComboBox klausurFach = new ComboBox<String>();
        klausurFach.setPromptText("Modul");
        klausurFach.setPrefWidth(150);
        klausurFach.getItems().addAll("Schnubseln","Flubbeln","Knarzeln","Fiddeln");

        ComboBox klausurDatum = new ComboBox<String>();
        klausurDatum.setPromptText("Prüfungsdatum");
        klausurDatum.setPrefWidth(150);
        klausurDatum.setDisable(true);

        ObservableList<String> modulADate = FXCollections.observableArrayList("test1","test2");
        ObservableList<String> modulBDate = FXCollections.observableArrayList("test3","test4");
        ObservableList<String> modulCDate = FXCollections.observableArrayList("test5","test6");
        ObservableList<String> modulDDate = FXCollections.observableArrayList("test7","test8");

        klausurFach.setOnAction(e -> {
            klausurDatum.setDisable(false);

            if(klausurFach.getValue() == "Schnubseln")
                klausurDatum.setItems(modulADate);
            else if(klausurFach.getValue() == "Flubbeln")
                klausurDatum.setItems(modulBDate);
            else if(klausurFach.getValue() == "Knarzeln")
                klausurDatum.setItems(modulCDate);
            else if(klausurFach.getValue() == "Fiddeln")
                klausurDatum.setItems(modulDDate);
            else
                klausurDatum.setDisable(true);
        });

        //Noteneingabe Elemente
        Label eingabeInfo = new Label("Zu ändernden Studenten rechts auswählen.");
        Label sNachnameLabel = new Label(sNachname + ",");
        Label sVornameLabel = new Label(sVorname);
        TextField sNoteInput = new TextField();
        sNoteInput.setPrefWidth(50);
        sNoteInput.setPromptText("Note");

        Button noteEingBtn = new Button("Eingeben");

        Text noteExists = new Text();
        noteExists.setWrappingWidth(200);

        noteEingBtn.setOnAction(e -> validateNote(sNoteInput, sVornameLabel, sNachnameLabel));
        sNoteInput.setOnAction(e -> validateNote(sNoteInput, sVornameLabel, sNachnameLabel));

        //Datentabelle

        //Nachnamen Spalte
        TableColumn<StudentenID, String> nachnameColumn = new TableColumn<>("Nachname");
        nachnameColumn.setMinWidth(150);
        nachnameColumn.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        //Vornamen Spalte
        TableColumn<StudentenID, String> vornameColumn = new TableColumn<>("Vorname");
        vornameColumn.setMinWidth(150);
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        //Noten Spalte
        TableColumn<StudentenID, Double> noteColumn = new TableColumn<>("Note");
        noteColumn.setMinWidth(50);
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        studentenTable.setItems(getStudentenIDs());
        studentenTable.getColumns().addAll(nachnameColumn,vornameColumn,noteColumn);
        studentenTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> setTempStudentIDs(sNoteInput,sNachnameLabel,sVornameLabel,noteEingBtn,noteExists));


        //Layout Dozent
        VBox leftVBox = new VBox(8);
        leftVBox.getChildren().addAll(klausurFach,klausurDatum);
        leftVBox.setPadding(new Insets(0,0,0,15));

        HBox nameLabels = new HBox(8);
        nameLabels.getChildren().addAll(sNachnameLabel,sVornameLabel);
        nameLabels.setPadding(new Insets(16,0,0,0));

        HBox noteEingabeHBox = new HBox(8);
        noteEingabeHBox.getChildren().addAll(sNoteInput,noteEingBtn);
        noteEingabeHBox.setPadding(new Insets(8,0,8,0));

        VBox noteContainer = new VBox();
        noteContainer.getChildren().addAll(nameLabels,noteEingabeHBox,noteExists);
        noteContainer.setPadding(new Insets(0,0,0,15));
        noteContainer.setPrefWidth(250);

        AnchorPane containerPane = new AnchorPane();
        containerPane.setPadding(new Insets(10,10,10,15));
        containerPane.getChildren().addAll(leftVBox, noteContainer);
        containerPane.setTopAnchor(leftVBox, 10.0);
        containerPane.setTopAnchor(noteContainer, 180.0);

        BorderPane dozentPane = new BorderPane();
        dozentPane.setPadding(new Insets(0,0,10,0));
        dozentPane.setLeft(containerPane);
        dozentPane.setCenter(studentenTable);
        dozentPane.setTop(menuBar);

        dozentScene = new Scene(dozentPane, 720, 480);

        //Initiales Anzeigen bei Programmstart
        fenster.setScene(login);
        fenster.setResizable(false);
        fenster.sizeToScene();
        fenster.show();
    }
    // =^.^= TODO Musst die Methode noch zu Ende schreiben, damit die mit der Datenbank abgleicht.
    // TODO False Cases der if statements der Methode für das GUI einbinden (PopUp und clearen der jeweiligen Textbox)
    private void validateInput(TextField nameInput, String nameMsg, TextField pwInput, String pwMsg) {
        fenster.setScene(dozentScene);
        fenster.setResizable(true);
        fenster.centerOnScreen();
    }

    private void closeProgram() {
        Boolean schließen = BestaetigungsBox.display("Programm Beenden?", "Sind Sie sicher, dass Sie das Programm beenden möchten?");
        if(schließen)
            fenster.close();
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Double> toList(double[] doubles) {
        List<Double> list = new ArrayList<>(doubles.length);
        for (double x : doubles) {
            list.add(x);
        }
        return list;
    }

    private void validateNote(TextField textField, Label vorname, Label nachname) {
        if (!textField.getText().isEmpty())
            if (isDouble(textField.getText())) {
                if (valid.contains(Double.parseDouble(textField.getText())))
                    NoteBestBox.display("Bestätigen", vorname.getText(), nachname.getText(), Double.parseDouble(textField.getText()));
                else
                    BestaetigungsBox.display("Fehler!", "'" + textField.getText() + "'" + " ist keine zulässige Note!");
                textField.clear();
            } else
                BestaetigungsBox.display("Fehler!", "'" + textField.getText() +"'" + " ist keine zulässige Note!");
                textField.clear();
    }

    //TODO durch datenbankintegration ersetzen! Namen und Note sind Datenbank werte!
    public ObservableList<StudentenID> getStudentenIDs(){
        ObservableList<StudentenID> studenten = FXCollections.observableArrayList();
        studenten.add(new StudentenID("Maus","Snubbel",null));
        studenten.add(new StudentenID("Bot","Party",1.0));
        studenten.add(new StudentenID("Hase","Drecks",null));
        studenten.add(new StudentenID("Schwein","Böses",null));
        return studenten;
    }

    private void setTempStudentIDs(TextField noteInput, Label labelNachname, Label labelVorname, Button button, Text text){
        sNachname = studentenTable.getSelectionModel().getSelectedItem().getNachname() + ",";
        sVorname = studentenTable.getSelectionModel().getSelectedItem().getVorname();
        sNote = studentenTable.getSelectionModel().getSelectedItem().getNote();

        labelNachname.setText(sNachname);
        labelVorname.setText(sVorname);

        if (sNote == null) {
            button.setDisable(false);
            noteInput.setDisable(false);
            noteInput.setEditable(true);
            noteInput.clear();
            text.setText("");
        }
        else {
            button.setDisable(true);
            noteInput.setDisable(true);
            noteInput.setText(String.valueOf(sNote));
            text.setText("Es ist bereits eine Note eingegeben!");
            //text.setFill(Paint.valueOf("AB4642"));
            text.setStyle("-fx-fill: #ab4642");
        }
        //System.out.println(sNachname + sVorname + sNote);
    }
}