package GUI;

import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class NoteBestBox {

    static boolean antwort;

    public static boolean display(String titel, String vorname, String nachname, double note) {
        Stage fenster = new Stage();
        fenster.initModality(Modality.APPLICATION_MODAL);
        fenster.setTitle(titel);
        fenster.setMinWidth (300);

        Label label = new Label();
        label.setText("Sie sind im Begriff eine Note festzulegen. Nachdem Sie dies getan haben können Sie diese nicht mehr ändern!\n\n" + nachname + " " + vorname + ": " + note);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setPadding(new Insets(0, 0, 20, 0));

        //Buttons zur Bestätigung und Verneinung
        Button jaButton = new Button("Bestätigen");
        Button neinButton = new Button ("Abbrechen");

        jaButton.setOnAction(e -> {
            antwort = true;
            fenster.close();
        });

        neinButton.setOnAction(e -> {
            antwort = false;
            fenster.close();
        });

        //Nested Layout Erstellung
        VBox topText = new VBox(10);
        topText.getChildren().addAll(label);
        topText.setAlignment(Pos.CENTER);

        HBox btnLayout = new HBox(15);
        btnLayout.getChildren().addAll(jaButton, neinButton);
        btnLayout.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 15, 10));
        borderPane.setCenter(topText);
        borderPane.setBottom(btnLayout);

        Scene scene = new Scene(borderPane);
        fenster.setScene(scene);
        fenster.showAndWait();

        return antwort;
    }
}