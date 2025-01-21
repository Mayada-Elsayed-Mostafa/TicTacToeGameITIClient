package tic.tac.toe.game.iti.client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OnlineRecordsController  {
    
    Stage stage;
    @FXML
    private VBox filesList;
    @FXML
    private VBox buttonsList;
    public void setStage(Stage stage) {
        this.stage = stage;
        startController();
    }
    private void startController(){
        File files = new File("onlineRecords");
        File[] jsonFiles = files.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        for(File json : jsonFiles){
            Button button = new Button();
            button.setMnemonicParsing(false);
            button.setStyle("-fx-background-color: white; -fx-border-color: #946E3D; -fx-border-width: 2px; -fx-border-radius: 5px;");
            button.setText("Display Record");
            button.setTextFill(javafx.scene.paint.Color.valueOf("#946e3d"));
            VBox.setMargin(button, new Insets(5.0, 0.0, 0.0, 0.0));
            button.setFont(new Font("System Bold", 14.0));
            button.setOnAction((event) -> {
                handleFileRecored(json);
            });
            buttonsList.getChildren().add(button);
            Text text = new Text();
            text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            text.setStrokeWidth(0.0);
            text.setText(json.getName().substring(0, json.getName().length()-5));
            text.setWrappingWidth(348.6708984375);
            VBox.setMargin(text, new Insets(6.0, 0.0, 0.0, 2.0));
            text.setFont(new Font("System Bold", 23.0));
            filesList.getChildren().add(text);
       }
    }

    private void handleFileRecored(File json) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Record.fxml"));
            Parent root = loader.load();

            RecordController controller = loader.getController();
            controller.setStage(stage);
            controller.loadGameRecord(json);

            stage.setScene(new Scene(root));
            stage.setTitle("Record");
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
