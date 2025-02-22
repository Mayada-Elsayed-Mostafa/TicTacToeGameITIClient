package tic.tac.toe.game.iti.client;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoController {

    private Stage stage;
    private Scene previousScene;
    private Controller gameController;
    private String title;
    private String videoUrl;

    public void setStage(Stage stage) {
        this.stage = stage;
        video.setFitWidth(800);
        video.setFitHeight(600);
        video.setPreserveRatio(false);
    }

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    public void setController(Controller controller) {
        this.gameController = controller;
    }
   
    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        initializeMediaPlayer();
    }

    @FXML
    private MediaView video;
    private MediaPlayer mediaPlayer;

    private void initializeMediaPlayer() {
        if (videoUrl != null) {
            mediaPlayer = new MediaPlayer(new Media(getClass().getResource(videoUrl).toExternalForm())); //this.?????????????????????????????
            mediaPlayer.setAutoPlay(true);
            video.setMediaPlayer(mediaPlayer);

            mediaPlayer.setOnEndOfMedia(() -> {
                if (stage != null && previousScene != null) {
                    stage.setScene(previousScene);
                    if (gameController != null) {
                        gameController.askReplay();
                    }
                }
            });
        }

    }
}
