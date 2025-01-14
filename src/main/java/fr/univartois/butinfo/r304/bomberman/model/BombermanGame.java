/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without warranty of any kind, express or
 * implied, including but not limited to the warranties of merchantability,
 * fitness for a particular purpose and non-infringement.
 * In no event shall the authors or copyright holders be liable for any claim,
 * damages or other liability, whether in contract, tort or otherwise, arising
 * from, out of or in connection with the software or the use or other dealings
 * in the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - University of Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman.model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.r304.bomberman.model.bombs.*;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingEnemy;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingPlayer;
import fr.univartois.butinfo.r304.bomberman.model.level.EasyGameFactory;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.movables.*;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;

/**
 * The {@link BombermanGame} class manages a game of Bomberman.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class BombermanGame {

    /**
     * The random number generator used in the game.
     */
    public static final Random RAND = new Random();

    /**
     * The player's movement speed (in pixels/s).
     */
    public static final int DEFAULT_SPEED = 75;

    /**
     * The initial number of bombs available to the player.
     */
    public static final int DEFAULT_BOMBS = 400;

    /**
     * The width of the game map (in pixels).
     */
    private final int width;

    /**
     * The height of the game map (in pixels).
     */
    private final int height;

    /**
     * The instance of {@link ISpriteStore} used to create the {@link Sprite} objects of the game.
     */
    private final ISpriteStore spriteStore;

    /**
     * An instance of {@code LevelAbstractFactory} used to create game-related objects
     */
    private LevelAbstractFactory gameFactory;

    /**
     * The game map.
     */
    private GameMap gameMap;

    /**
     * The player's character.
     */
    private LivingPlayer player;

    /**
     * The initial number of enemies in the game.
     */
    private final int nbEnemies;

    /**
     * The number of remaining enemies in the game.
     */
    private int remainingEnemies;

    /**
     * The list of movable objects in the game.
     */
    private final List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * The game animation, ensuring that the different objects move.
     */
    private final AnimationTimer animation = new BombermanAnimation(movableObjects);

    /**
     * The game controller.
     */
    private IBombermanController controller;

    /**
     * Creates a new instance of BombermanGame.
     *
     * @param gameWidth The width of the game map.
     * @param gameHeight The height of the game map.
     * @param spriteStore The instance of {@link ISpriteStore} used to create the {@link Sprite} objects of the game.
     * @param nbEnemies The number of enemies in the game.
     */
    public BombermanGame(int gameWidth, int gameHeight, ISpriteStore spriteStore, int nbEnemies) {
        this.width = gameWidth;
        this.height = gameHeight;
        this.spriteStore = spriteStore;
        this.nbEnemies = nbEnemies;
        this.gameFactory = EasyGameFactory.getInstance();
    }

    /**
     * Changes the controller with which to interact to update the display.
     *
     * @param controller The controller with which to interact.
     */
    public void setController(IBombermanController controller) {
        this.controller = controller;
    }

    /**
     * Returns the instance of {@link ISpriteStore} used to create the {@link Sprite} objects of the game.
     *
     * @return The instance of {@link ISpriteStore} used to create the {@link Sprite} objects of the game.
     */
    public ISpriteStore getSpriteStore() {
        return spriteStore;
    }

    /**
     * Returns the width of the game map (in pixels).
     *
     * @return The width of the game map.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the game map (in pixels).
     *
     * @return The height of the game map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Prepares a game of Bomberman before it starts.
     */
    public void prepare() {
        gameMap = createMap();
        controller.prepare(gameMap);
    }

    /**
     * Creates the game map, respecting the window dimensions.
     *
     * @return The created game map.
     */
    private GameMap createMap() {
        gameMap = new GameMap(height / 30, width / 30);
        gameFactory.createMap(gameMap);
        return gameMap;
    }

    /**
     * Starts the game of Bomberman.
     */
    public void start() {
        prepare();
        createMovables();
        initStatistics();
        animation.start();
    }

    /**
     * Creates the different movable objects present at the start of the game.
     */
    private void createMovables() {
        // First, remove all movable elements still present.
        clearAllMovables();

        /* Add the player */
        player = gameFactory.createPlayer(this, spriteStore.getSprite("guy"));
        movableObjects.add(player);
        spawnMovable(player);

        /* Add the enemies */
        List<Cell> emptyCell;
        Cell cell;
        String[] enemies = new String[]{"goblin", "minotaur", "punker", "rourke"};

        for (int i = 0; i < nbEnemies; i++) {
            emptyCell = gameMap.getEmptyCells();
            cell = emptyCell.get(RAND.nextInt(emptyCell.size()));
            LivingEnemy enemy = gameFactory.createEnemy(cell, spriteStore.getSprite(enemies[RAND.nextInt(enemies.length)]), this, player.getPlayer());
            movableObjects.add(enemy);
            spawnMovable(enemy);
        }
        IBomb iBomb;
        String sprite;
        // Add the player's initial bombs.
        for (int i = 0; i < DEFAULT_BOMBS; i++) {
            switch (RAND.nextInt(4)) {
                case 0 -> {iBomb = new LargeBomb(); sprite = "large-bomb";}
                case 1 -> {iBomb = new SimpleBomb(); sprite = "bomb";}
                case 2 -> {iBomb = new RowBomb(); sprite = "row-bomb";}
                default -> {iBomb = new ColumnBomb(); sprite = "column-bomb";}
            }
            Bomb bomb = new Bomb(this, 0.0, 0.0, spriteStore.getSprite(sprite), iBomb);
            player.addBombs(bomb);
        }
    }

    /**
     * Initializes the statistics for this game.
     */
    private void initStatistics() {
        controller.bindLife(player.getLivesProperty());
        controller.bindScore(player.getScoreProperty());
        controller.bindBombs(Bindings.size(player.getListBombs()));
        remainingEnemies = nbEnemies;
    }

    /**
     * Spawns a movable object on the game map.
     *
     * @param movable The object to spawn.
     */
    public void spawnMovable(IMovable movable) {
        List<Cell> spawnableCells = gameMap.getEmptyCells();
        if (!spawnableCells.isEmpty()) {
            Cell cell = spawnableCells.get(RAND.nextInt(spawnableCells.size()));
            movable.setX(cell.getColumn() * spriteStore.getSpriteSize());
            movable.setY(cell.getRow() * spriteStore.getSpriteSize());
            addMovable(movable);
        }
    }

    /**
     * Moves the player's character up.
     */
    public void moveUp() {
        stopMoving();
        player.setVerticalSpeed(-DEFAULT_SPEED);
    }

    /**
     * Moves the player's character to the right.
     */
    public void moveRight() {
        stopMoving();
        player.setHorizontalSpeed(DEFAULT_SPEED);
    }

    /**
     * Moves the player's character down.
     */
    public void moveDown() {
        stopMoving();
        player.setVerticalSpeed(DEFAULT_SPEED);
    }

    /**
     * Moves the player's character to the left.
     */
    public void moveLeft() {
        stopMoving();
        player.setHorizontalSpeed(-DEFAULT_SPEED);
    }

    /**
     * Stops the player's movement.
     */
    public void stopMoving() {
        player.setVerticalSpeed(0);
        player.setHorizontalSpeed(0);
    }

    /**
     * Drops a bomb on the tile where the player is located and schedules the explosion of this bomb.
     */
    public void dropBomb() {
        // Check if the player still has bombs
        if (!player.getListBombs().isEmpty()) {
            Bomb bomb = player.getListBombs().getFirst();
            player.removeBomb(bomb);
            dropBomb(bomb);
        }
    }

    /**
     * Drops a bomb on the tile where the player is located and schedules the explosion of this bomb.
     *
     * @param bomb The bomb to drop.
     */
    public void dropBomb(Bomb bomb) {
        // Get the cell where the player is located.
        Cell playerCell = getCellAt(player.getX(), player.getY());

        // Move the bomb to the cell where the player is located.
        bomb.setX(playerCell.getColumn() * spriteStore.getSpriteSize());
        bomb.setY(playerCell.getRow() * spriteStore.getSpriteSize());
        bomb.positionBombe();

        // Add the bomb to the list of movable objects.
        addMovable(bomb);
    }

    /**
     * Returns the cell at the given position on the map.
     *
     * @param x The x position of the cell.
     * @param y The y position of the cell.
     *
     * @return The cell at the given position.
     */
    public Cell getCellAt(int x, int y) {
        // Translate this position into a position in the map.
        int row = y / spriteStore.getSpriteSize();
        int column = x / spriteStore.getSpriteSize();

        // Finally, retrieve the cell at this position in the map.
        return gameMap.getAt(row, column);
    }

    /**
     * Adds a movable object to the game.
     *
     * @param object The object to add.
     */
    public void addMovable(IMovable object) {
        movableObjects.add(object);
        controller.addMovable(object);
    }

    /**
     * Removes a movable object from the game.
     *
     * @param object The object to remove.
     */
    public void removeMovable(IMovable object) {
        movableObjects.remove(object);
        object.consume();
    }

    /**
     * Removes all movable objects from the game.
     */
    private void clearAllMovables() {
        for (IMovable movable : movableObjects) {
            movable.consume();
        }
        movableObjects.clear();
    }

    /**
     * Updates the player's score when an enemy is killed.
     * If it was the last enemy, the player wins the game.
     *
     * @param enemy The enemy that was killed.
     */
    public void enemyIsDead(IMovable enemy) {
        player.setScore(player.getScore() + 5);
        remainingEnemies--;
        removeMovable(enemy);

        if (remainingEnemies == 0) {
            // All enemies have been killed: the game is over.
            gameFactory = gameFactory.getNextLevelFactory();
            gameOver("YOU WIN!");
        }
    }

    /**
     * Ends the game when the player is killed.
     */
    public void playerIsDead() {
        gameFactory = EasyGameFactory.getInstance();
        gameOver("YOU HAVE BEEN KILLED!");
    }

    /**
     * Ends the current game.
     *
     * @param message The message indicating the result of the game.
     */
    private void gameOver(String message) {
        animation.stop();
        controller.gameOver(message);
    }
}
