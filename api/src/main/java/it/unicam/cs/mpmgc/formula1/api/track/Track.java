/*
 * MIT License
 *
 * Copyright (c) 2025 Mattia Valeri
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpmgc.formula1.api.track;

import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.List;

/**
 * Represents a Formula1 track.
 */
public interface Track {
    /**
     * Returns the whole Track.
     * @return the whole Track.
     */
    List<List<Tile>> getWholeTrack();

    /**
     * Checks if the position is valid inside the Track. "Valid" means being in
     * the limits of the Track.
     * @param pos Position to check.
     * @return true if its valid, false otherwise.
     */
    boolean isPositionValid(Vector2 pos);

    /**
     * Checks if a position is inside the Track. "Inside" means being inside the
     * walls of the track. If the position is on a wall for example, it's NOT
     * inside the Track.
     * @param pos Position to Check
     * @return true if its inside, false otherwise.
     */
    boolean isPositionInsideTrack(Vector2 pos);

    /**
     * Checks if an entity is inside the Track. "Inside" means being inside the
     * walls of the track. If the entity is on a wall for example, it's NOT
     * inside the Track.
     * @param entity Entity to Check
     * @return true if its inside, false otherwise.
     */
    boolean isEntityInsideTrack(Entity entity);

    /**
     * Checks if an Entity is on the finish line.
     * @param entity the Entity that we want to check.
     * @return true if on the finish line, false otherwise.
     */
    boolean isEntityOnFinishLine(Entity entity);

    /**
     * Put the list of entities passed as a parameter on the starting line. If
     * there's no space left on the starting line, some entities could be left out
     * of the game.
     * @param entities the list of cars.
     */
    void putEntitiesOnStart(List<? extends Entity> entities);

    /**
     * Given the start and end position of the movement of an Entity, this method
     * return true if the entity has crashed in any way, or false otherwise.
     * @param start start position of the entity before a movement.
     * @param end end position of the entity after a movement.
     * @return true if the entity has crashed in any way, or false otherwise.
     */
    boolean hasEntityCrashed(Vector2 start, Vector2 end);
}
