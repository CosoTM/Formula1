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

package it.unicam.cs.mpmgc.formula1.api.strategy;

import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.simulation.SimulationInfo;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

/**
 * A Strategy represents how an
 * {@link it.unicam.cs.mpmgc.formula1.api.entity.Entity Entity} decides to move
 * inside a {@link  it.unicam.cs.mpmgc.formula1.api.track.Track Track},
 * depending on any type of data related to the Simulation.
 */
public interface Strategy {
    /**
     * Given the possible moves, the entity that applies the strategy and the
     * rest of the simulation, this method will return one of the possible
     * moves, decided accordingly to the strategy.
     * @param possiblePositions The possible positions the Entity can move to.
     * @param thisEntity The entity following the strategy.
     * @param sim The simulation.
     * @return One of the possible positions.
     */
    Vector2 decideNextMove(Vector2[] possiblePositions, Entity thisEntity,
                           SimulationInfo sim);

    /**
     * Returns a random Vector2 from the array sent.
     * @param possiblePositions The array of possible positions.
     * @return a random one from the array.
     */
    default Vector2 decideRandomMove(Vector2[] possiblePositions){
        return possiblePositions[(int) (Math.random() * possiblePositions.length)];
    }
}
