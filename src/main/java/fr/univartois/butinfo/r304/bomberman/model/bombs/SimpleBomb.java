package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBomb;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SimpleBomb} class implements the {@link IBomb} interface
 * and represents a column bomb in the Bomberman game.
 * It is responsible for handling the explosion logic of the bomb.
 */
public class SimpleBomb implements IBomb {

    /**
     * Triggers an explosion at the specified position in the game.
     * The explosion creates multiple {@link Explosion} instances at
     * the specified coordinates, affecting the surrounding cells.
     *
     * @param game The {@link BombermanGame} instance in which the explosion occurs.
     * @param xPosition The x-coordinate of the explosion's origin.
     * @param yPosition The y-coordinate of the explosion's origin.
     */
    public void explosion(BombermanGame game, double xPosition, double yPosition) {
        Sprite explosion = game.getSpriteStore().getSprite("explosion");
        int spriteSize = game.getSpriteStore().getSpriteSize();
        List<Explosion> list = new ArrayList<>();

        list.add(new Explosion(game, xPosition, yPosition + spriteSize, explosion));
        list.add(new Explosion(game, xPosition, yPosition - spriteSize, explosion));
        list.add(new Explosion(game, xPosition + spriteSize, yPosition, explosion));
        list.add(new Explosion(game, xPosition - spriteSize, yPosition, explosion));
        list.add(new Explosion(game, xPosition , yPosition, explosion));

        for (Explosion ex : list) {
            game.addMovable(ex);
            game.getCellAt(ex.getX(), ex.getY()).cellExplode(game);
        }
    }
}
