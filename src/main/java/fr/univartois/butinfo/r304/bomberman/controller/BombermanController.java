/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without any warranty of any kind, express
 * or implied, including but not limited to warranties of merchantability,
 * fitness for a particular purpose, and non-infringement.
 * Under no circumstances shall the authors or copyright holders be liable
 * for any damage, claim, or other liability, whether in an action of contract,
 * tort, or otherwise, arising from, out of, or in connection with the software
 * or its use, or with other elements of the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman.controller;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IBombermanController;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import javafx.beans.binding.IntegerExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The {@link BombermanController} class provides the controller to play the
 * Bomberman game in a JavaFX interface.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class BombermanController implements IBombermanController {

    /**
     * The current Bomberman game session.
     */
    private BombermanGame game;

    /**
     * The window where the game takes place.
     */
    private Stage stage;

    /**
     * The container displaying the game's background.
     */
    @FXML
    private GridPane backgroundPane;

    /**
     * The container displaying the game's mobile objects.
     */
    @FXML
    private Pane movingPane;

    /**
     * The label displaying the player's score.
     */
    @FXML
    private Label score;

    /**
     * The label displaying the player's number of bombs.
     */
    @FXML
    private Label bombs;

    /**
     * The label displaying the player's number of lives.
     */
    @FXML
    private Label life;

    /**
     * The label displaying a message to the user.
     */
    @FXML
    private Label message;

    /**
     * A boolean indicating whether the game has started.
     * It is used to delay the start of the game, waiting for the user to press
     * a key on their keyboard.
     */
    private boolean started = false;

    /**
     * Associates the game display window with this controller.
     *
     * @param stage The window displaying the game.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        addKeyListeners();
    }

    /**
     * Sets the current Bomberman game for this controller.
     *
     * @param game The Bomberman game instance to associate with this controller.
     */
    @Override
    public void setGame(BombermanGame game) {
        this.game = game;
    }

    /**
     * Prepares the game by creating the background based on the provided map.
     *
     * @param map The game map used to create the background.
     */
    @Override
    public void prepare(GameMap map) {
        createBackground(map);
    }

    /**
     * Creates the game's background.
     *
     * @param map The game map to display.
     */
    private void createBackground(GameMap map) {
        backgroundPane.getChildren().clear();
        for (int row = 0; row < map.getHeight(); row++) {
            for (int column = 0; column < map.getWidth(); column++) {
                Cell cell = map.getAt(row, column);
                ImageView view = new ImageView(cell.getSprite().getImage());
                cell.getSpriteProperty().addListener((p, o, n) -> view.setImage(n.getImage()));
                backgroundPane.add(view, column, row);
            }
        }
    }

    /**
     * Adds the key listeners for the game.
     */
    private void addKeyListeners() {
        // A brief key press can have multiple effects.
        stage.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            if (!started) {
                // The game starts at the first key press.
                started = true;
                message.setVisible(false);
                game.start();

            } else if (" ".equals(e.getCharacter())) {
                // The game has started: it's time to place a bomb.
                game.dropBomb();
            }
        });

        // When the user presses an arrow key, the character is moved.
        stage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (started) {
                if (e.getCode() == KeyCode.UP) {
                    game.moveUp();

                } else if (e.getCode() == KeyCode.LEFT) {
                    game.moveLeft();

                } else if (e.getCode() == KeyCode.DOWN) {
                    game.moveDown();

                } else if (e.getCode() == KeyCode.RIGHT) {
                    game.moveRight();
                }
            }
        });

        // When the user releases an arrow key, the movement stops.
        stage.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            if (started && e.getCode().isArrowKey()) {
                game.stopMoving();
            }
        });
    }

    /**
     * Binds the score display to the provided score property.
     *
     * @param scoreProperty The property representing the current score.
     *                      The score will be automatically updated in the display when the property changes.
     */
    @Override
    public void bindScore(IntegerExpression scoreProperty) {
        score.textProperty().bind(scoreProperty.asString());
    }

    /**
     * Binds the bombs display to the provided bombs property.
     *
     * @param bombsProperty The property representing the current number of bombs.
     *                      The number of bombs will be automatically updated in the display when the property changes.
     */
    @Override
    public void bindBombs(IntegerExpression bombsProperty) {
        bombs.textProperty().bind(bombsProperty.asString());
    }

    /**
     * Binds the life display to the provided life property.
     *
     * @param lifeProperty The property representing the current number of lives.
     *                     The number of lives will be automatically updated in the display when the property changes.
     */
    @Override
    public void bindLife(IntegerExpression lifeProperty) {
        life.textProperty().bind(lifeProperty.asString());
    }

    /**
     * Adds a movable object to the game and updates its display.
     * The object's position is bound to its X and Y properties, and its sprite is displayed accordingly.
     * If the object's sprite changes, its image is updated, and if the object is consumed, it is removed from the display.
     *
     * @param movable The movable object to add to the game.
     */
    @Override
    public void addMovable(IMovable movable) {
        // Display the object at the correct position.
        ImageView view = new ImageView(movable.getSprite().getImage());
        view.xProperty().bind(movable.getXProperty());
        view.yProperty().bind(movable.getYProperty());
        movingPane.getChildren().add(view);

        // Update the image when the object's sprite changes.
        movable.getSpriteProperty().addListener((p, o, n) -> view.setImage(n.getImage()));

        // Remove the object from display when it is consumed.
        movable.isConsumedProperty().addListener((p, o, n) -> {
            if (n == Boolean.TRUE) {
                movingPane.getChildren().remove(view);
            }
        });
    }

    /**
     * Displays the game over message and pauses the game.
     * The end message is shown along with a prompt to restart the game by pressing any key.
     *
     * @param endMessage The message to display when the game ends.
     */
    @Override
    public void gameOver(String endMessage) {
        started = false;
        message.setVisible(true);
        message.setText(endMessage + "\nPRESS ANY KEY TO RESTART...");
    }
}
