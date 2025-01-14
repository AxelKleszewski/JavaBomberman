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

package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import javafx.beans.binding.IntegerExpression;

/**
 * The {@link IBombermanController} interface defines the contract that any Bomberman game
 * controller must adhere to.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IBombermanController {

    /**
     * Associates the current Bomberman game with this controller.
     *
     * @param game The ongoing game.
     */
    void setGame(BombermanGame game);

    /**
     * Prepares the game display before it starts.
     *
     * @param map The game map to display.
     */
    void prepare(GameMap map);

    /**
     * Binds the player's score to its display in the view.
     *
     * @param scoreProperty The property that stores the player's score.
     */
    void bindScore(IntegerExpression scoreProperty);

    /**
     * Binds the player's number of bombs to its display in the view.
     *
     * @param bombsProperty The property that stores the number of bombs.
     */
    void bindBombs(IntegerExpression bombsProperty);

    /**
     * Binds the player's life to its display in the view.
     *
     * @param lifeProperty The property that stores the player's life.
     */
    void bindLife(IntegerExpression lifeProperty);

    /**
     * Adds a movable object to the game to allow it to be displayed.
     *
     * @param movable The object to display.
     */
    void addMovable(IMovable movable);

    /**
     * Displays a message when the game is over.
     *
     * @param endMessage The message to display.
     */
    void gameOver(String endMessage);
}