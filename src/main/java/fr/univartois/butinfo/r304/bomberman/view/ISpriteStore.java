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

import java.util.NoSuchElementException;

/**
 * The {@link ISpriteStore} interface provides a method to create instances
 * of {@link Sprite} from their identifier.
 * Typically, this identifier is used to associate an image of a game element
 * with the {@link Sprite} instance that will be created.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
@FunctionalInterface
public interface ISpriteStore {

    /**
     * Loads an instance of {@link Sprite} given its identifier.
     *
     * @param identifier The identifier of the {@link Sprite} instance to load.
     *
     * @return The {@link Sprite} instance corresponding to the given identifier.
     *
     * @throws NoSuchElementException If no {@link Sprite} instance corresponds to
     *         the given identifier.
     */
    Sprite getSprite(String identifier);

    /**
     * Returns the size of the sprites to be loaded.
     *
     * @return The size of the sprites (in pixels).
     */
    default int getSpriteSize() {
        return 30;
    }
}
