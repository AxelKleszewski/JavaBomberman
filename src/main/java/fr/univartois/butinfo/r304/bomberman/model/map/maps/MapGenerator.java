package fr.univartois.butinfo.r304.bomberman.model.map.maps;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.UnbreakableWall;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class MapGenerator {

    MapGenerator (GameMap gameMap) {
        Sprite wall = SpriteStore.getInstance().getSprite("wall");
        int height = gameMap.getHeight();
        int width = gameMap.getWidth();
        SpriteStore spriteStore = SpriteStore.getInstance();

        // Fill the map with lawn cells
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameMap.setAt(i, j, new Cell(spriteStore.getSprite("lawn")));
            }
        }

        // Add indestructible walls on the borders
        for (int i = 0; i < height; i++) {
            gameMap.setAt(i, 0, new Cell(new Wall(new UnbreakableWall(wall))));
            gameMap.setAt(i, width - 1, new Cell(new Wall(new UnbreakableWall(wall))));
        }
        for (int j = 0; j < width; j++) {
            gameMap.setAt(0, j, new Cell(new Wall(new UnbreakableWall(wall))));
            gameMap.setAt(height - 1, j, new Cell(new Wall(new UnbreakableWall(wall))));
        }
    }

}
