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

import it.unicam.cs.mpmgc.formula1.api.simulation.SimulationInfo;
import it.unicam.cs.mpmgc.formula1.api.strategy.Strategy;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

/**
 *
 */
public class CarEntity extends GameEntity{
    private final Strategy strategy;

    public CarEntity(Vector2 startPos, char name, Strategy strategy) {
        super(startPos, name);
        this.strategy = strategy;
    }

    @Override
    public void nextMove(SimulationInfo sim) {
        Vector2 decidedMove = strategy.decideNextMove(getPossibleMoves(),this,
                sim );

        setPosition(getPosition().sum(decidedMove));
        setAcceleration(decidedMove);
    }

    private Vector2[] getPossibleMoves(){
        // TODO: I can surely make this better but for now it works.

        Vector2 acceleration = getAcceleration();
        return new Vector2[]{
            new Vector2(-1,-1).sum(acceleration),
            new Vector2(0,-1).sum(acceleration),
            new Vector2(1,-1).sum(acceleration),
            new Vector2(-1,0).sum(acceleration),
            new Vector2(0,0).sum(acceleration),
            new Vector2(1,0).sum(acceleration),
            new Vector2(-1,1).sum(acceleration),
            new Vector2(0,1).sum(acceleration),
            new Vector2(1,1).sum(acceleration),
        };
    }
}
