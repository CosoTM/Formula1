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

package it.unicam.cs.mpmgc.formula1.api.entity;


import it.unicam.cs.mpmgc.formula1.api.simulation.Simulation;
import it.unicam.cs.mpmgc.formula1.api.vector.Position;

/**
 * Represents an Entity in the game, that moves and behaves in a specific way.
 *
 * Every entity has a name to recognize them, a position in-game, can decide
 * what to do next depending on the state of the Simulation, and could be
 * "alive" or not.
 */
public interface Entity {

    /**
     * Returns the Entity name.
     * @return the Entity name.
     */
    char getName();

    /**
     * Returns the current position of the Entity.
     * @return the current position of the Entity
     */
    Position getPosition();

    /**
     * Modifies the current position of the Entity.
     * @param pos The new position of the Entity.
     */
    void setPosition(Position pos);

    /**
     * Decides what the Entity should do given the current state of the
     * Simulation.
     * @param sim The simulation.
     */
    void nextMove(Simulation sim);

    /**
     * Return true if the Entity is alive, false otherwise.
     * @return true if the Entity is alive, false otherwise.
     */
    boolean isAlive();

}
