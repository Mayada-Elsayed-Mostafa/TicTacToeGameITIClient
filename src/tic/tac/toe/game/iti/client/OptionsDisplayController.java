/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.toe.game.iti.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import tic.tac.toe.game.iti.client.ServerSide.MassageType;
import tic.tac.toe.game.iti.client.ServerSide.ServerHandler;

/**
 * FXML Controller class
 *
 * @author SHEREEN
 */
public class OptionsDisplayController extends Controller {
    
    Stage stage;

    @FXML
    private Label header;
    @FXML
    private Label players;
    @FXML
    private Label score;
    @FXML
    private Label winner;
    @FXML
    private Label winnerScore;
    @FXML
    private Label loser;
    @FXML
    private Label loserScore;
    @FXML
    private Button restartGameBtn;
    @FXML
    private Button endGameBtn;
    

    public void setStage(Stage stage, String msg) {
        this.stage = stage;
    }

    @Override
    public void askReplay() { }
    
    public void restartHandler(){
        restartRequest();
    }
    
    public void restartRequest() {   //sends restart request to the server
        JSONObject restart = new JSONObject();
        restart.put("type", MassageType.RESTART_REQUEST_MSG);
        try {
            ServerHandler.massageOut.writeUTF(restart.toJSONString());
        } catch (IOException ex) {
            Logger.getLogger(OnlineGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayVideo(String videoUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("video.fxml"));
            Parent root = loader.load();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("optionsDisplay.fxml"));
            Parent rot = loader1.load();

            VideoController controller = loader.getController();
            controller.setStage(ServerHandler.stage);
            
            controller.setController(this);
            controller.setVideoUrl(videoUrl);

            ServerHandler.stage.setScene(new Scene(rot));
            controller.setPreviousScene(ServerHandler.stage.getScene());
            ServerHandler.stage.setScene(new Scene(root));

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred, please try again", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
}
