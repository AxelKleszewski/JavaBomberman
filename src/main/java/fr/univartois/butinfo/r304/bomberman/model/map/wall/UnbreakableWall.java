package fr.univartois.butinfo.r304.bomberman.model.map.wall;

import fr.univartois.butinfo.r304.bomberman.model.map.IStateWall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code UnbreakableWall} class implements the {@link IStateWall} interface,
 * representing a wall in the Bomberman game that cannot be destroyed.
 * It maintains a fixed state and sprite throughout the game.
 */
public class UnbreakableWall implements IStateWall {

    /**
     * The {@link Sprite} representing the visual appearance of the unbreakable wall.
     */
    private final Sprite sprite;

    /**
     * Constructs an {@code UnbreakableWall} with the specified sprite.
     *
     * @param sprite The {@link Sprite} representing the visual appearance of the wall.
     */
    public UnbreakableWall(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Returns the next state of the wall.
     * Since this wall is unbreakable, it always returns itself as the next state.
     *
     * @return The current {@code UnbreakableWall} instance.
     */
    @Override
    public IStateWall nextState() {
        return this;
    }

    /**
     * Retrieves the sprite associated with this wall.
     *
     * @return The {@link Sprite} representing the unbreakable wall.
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
