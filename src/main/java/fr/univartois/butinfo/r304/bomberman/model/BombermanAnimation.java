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

import javafx.animation.AnimationTimer;

/**
 * The {@link BombermanAnimation} class implements the animation system that moves
 * the various animated objects in the Bomberman game and triggers the explosion
 * of time-delayed bombs.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
final class BombermanAnimation extends AnimationTimer {

    /**
     * The list of objects that can move in the game.
     */
    private final List<IMovable> movableObjects;

    /**
     * The timestamp of the last update for the various objects.
     */
    private long previousTimestamp;

    /**
     * Creates a new instance of BombermanAnimation.
     *
     * @param movableObjects The list of objects that can move in the game.
     */
    public BombermanAnimation(List<IMovable> movableObjects) {
        this.movableObjects = movableObjects;
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.animation.AnimationTimer#start()
     */
    @Override
    public void start() {
        previousTimestamp = -1;
        super.start();
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.animation.AnimationTimer#handle(long)
     */
    @Override
    public void handle(long now) {
        // During the first update, we simply store the timestamp.
        if (previousTimestamp < 0) {
            previousTimestamp = now;
            return;
        }

        // We determine the elapsed time since the last update.
        long delta = (now - previousTimestamp) / 1000000;
        previousTimestamp = now;

        // We update the position of the objects.
        moveObjects(delta);
        checkCollisions();
    }

    /**
     * Updates the position of the various objects in the game.
     *
     * @param delta The time elapsed since the last update.
     */
    private void moveObjects(long delta) {
        for (IMovable movable : movableObjects) movable.move(delta);
    }

    /**
     * Checks if, during the last movement, any objects have collided.
     */
    private void checkCollisions() {
        for (IMovable movable : movableObjects) {
            for (IMovable other : movableObjects) {
                if ((movable != other) && movable.isCollidingWith(other)) {
                    // We inform both objects that they have collided.
                    movable.collidedWith(other);
                    other.collidedWith(movable);
                }
            }
        }
    }

}
