/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without any kind of warranty, either express or implied,
 * including but not limited to implied warranties of merchantability, fitness for a
 * particular purpose, and non-infringement.
 * In no event shall the authors or copyright holders be liable for any damage, claim,
 * or other liability, whether in an action of contract, tort, or otherwise, arising from,
 * out of, or in connection with the software or the use or other dealings in the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman;

import java.io.IOException;
import java.util.Objects;

import fr.univartois.butinfo.r304.bomberman.controller.BombermanController;
import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Bomberman class enables the implementation of the Bomberman game in JavaFX.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Bomberman extends Application {

    /**
     * The width (in pixels) of the window displaying the game.
     */
    private static final int GAME_WIDTH = 1110;

    /**
     * The height (in pixels) of the window displaying the game.
     */
    private static final int GAME_HEIGHT = 750;

    /**
     * The number of enemies to fight in the game.
     */
    private static final int NB_ENEMIES = 3;

    /**
     * The game's music.
     */
    private static final Media MUSIC = new Media(Bomberman.class.getResource("view/sounds/bomberman_ds_battle_theme.wav").toString());

    /**
     * The game's music player.
     */
    public static final MediaPlayer MUSIC_PLAYER = new MediaPlayer(MUSIC);

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws IOException {
        // First, we load the view and its controller.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/bomberman.fxml"));
        Parent viewContent = fxmlLoader.load();
        BombermanController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.getIcons().add(new Image(Objects.requireNonNull(Bomberman.class.getResource("view/icons/bomberman_icon.png")).toString()));

        // Next, we create the game and link it to the controller.
        BombermanGame game = new BombermanGame(
                GAME_WIDTH, GAME_HEIGHT, new SpriteStore(), NB_ENEMIES);
        controller.setGame(game);
        game.setController(controller);
        game.prepare();

        // We start the music to get in the mood.
        MUSIC_PLAYER.setCycleCount(MediaPlayer.INDEFINITE);
        MUSIC_PLAYER.play();

        // We can now display the scene and the window.
        Scene scene = new Scene(viewContent, GAME_WIDTH, GAME_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("BombermanFX");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Runs the JavaFX application for the Bomberman game.
     *
     * @param args The command-line arguments (which are ignored).
     *
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }
}
