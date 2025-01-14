package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBomb;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.ArrayList;

/**
 * The {@code ColumnBomb} class implements the {@link IBomb} interface
 * and represents a column bomb in the Bomberman game.
 * It is responsible for handling the explosion logic of the bomb.
 */
public class ColumnBomb implements IBomb {

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
        ArrayList<Explosion> list = new ArrayList<>();

        list.add(new Explosion(game, xPosition, yPosition + spriteSize + spriteSize, explosion));
        list.add(new Explosion(game, xPosition, yPosition - spriteSize - spriteSize, explosion));
        list.add(new Explosion(game, xPosition, yPosition + spriteSize, explosion));
        list.add(new Explosion(game, xPosition, yPosition - spriteSize, explosion));
        list.add(new Explosion(game, xPosition , yPosition, explosion));

        for (Explosion ex : list) {
            game.addMovable(ex);
            try{
                game.getCellAt(ex.getX(), ex.getY()).cellExplode(game);
            }catch (IllegalArgumentException ignored){/*We prevent the bomb from spawning outside the map.*/}
        }
    }
}
