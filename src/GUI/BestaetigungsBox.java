package GUI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class BestaetigungsBox {

    static boolean antwort;

    public static boolean display(String titel, String nachricht) {
        Stage fenster = new Stage();
        fenster.initModality(Modality.APPLICATION_MODAL);
        fenster.setTitle(titel);
        fenster.setMinWidth (360);
        Label label = new Label();
        label.setText(nachricht);
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