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

package fr.univartois.butinfo.r304.bomberman.view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javafx.scene.image.Image;

/**
 * The {@link SpriteStore} class is responsible for loading the various images used in
 * {@link Sprite} exactly once during the execution of the program.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class SpriteStore implements ISpriteStore {

    private static final SpriteStore INSTANCE = new SpriteStore();

    /**
     * The {@link Map} used to cache the various {@link Sprite} instances that have been loaded.
     */
    private final Map<String, Sprite> spriteCache = new HashMap<>();

    public static SpriteStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Sprite getSprite(String identifier) {
        // First, we check if the instance has already been loaded.
        Sprite cached = spriteCache.get(identifier);
        if (cached != null) {
            return cached;
        }

        // Now, we create the Sprite instance and cache it.
        Image image = loadImage(identifier);
        Sprite sprite = new Sprite(image);
        spriteCache.put(identifier, sprite);
        return sprite;
    }


    /**
     * Loads an image given its name.
     *
     * @param name The name of the image to load.
     *
     * @return The image with the given name.
     *
     * @throws NoSuchElementException If no image exists with the given name.
     */
    private Image loadImage(String name) {
        try {
            URL urlImage = getClass().getResource("sprites/" + name + ".png");
            assert urlImage != null;
            return new Image(urlImage.toExternalForm(), getSpriteSize(), getSpriteSize(), true, true);

        } catch (NullPointerException | IllegalArgumentException e) {
            throw new NoSuchElementException("Could not load image " + name, e);
        }
    }

}
