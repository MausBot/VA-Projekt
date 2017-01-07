package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FehlerBox {

    public static void display(String titel, String nachricht, String btnName) {
        Stage fenster = new Stage();
        fenster.initModality(Modality.APPLICATION_MODAL);
        fenster.setTitle(titel);
        fenster.setMinWidth(360);
        Label label = new Label();
        label.setText(nachricht);
        label.setPadding(new Insets(0, 0, 20, 0));

        //Button zur Bestätigung
        Button okButton = new Button(btnName);

        okButton.setOnAction(e -> fenster.close());

        VBox topText = new VBox(10);
        topText.getChildren().addAll(label);
        topText.setAlignment(Pos.CENTER);

        HBox btnLayout = new HBox(15);
        btnLayout.getChildren().addAll(okButton);
        btnLayout.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 15, 10));
        borderPane.setCenter(topText);
        borderPane.setBottom(btnLayout);

        Scene scene = new Scene(borderPane);
        fenster.setScene(scene);
        fenster.showAndWait();
    }
}
