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

import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import javax.swing.text.Position;

public abstract class GameEntity implements Entity{
    private final char name;
    private Vector2 currentPosition;
    private Vector2 currentAcceleration;
    private boolean isAlive;

    public GameEntity(Vector2 startPos, char name){
        currentPosition = startPos;
        currentAcceleration = new Vector2(0,0);
        this.name = name;

        isAlive = true;
    }

    @Override
    public char getName() {
        return name;
    }

    @Override
    public Vector2 getPosition() {
        return currentPosition;
    }

    @Override
    public void setPosition(Vector2 pos){
        if (pos == null) throw new NullPointerException("Position is null");
        currentPosition=pos;
    }

    @Override
    public Vector2 getAcceleration() {
        return currentAcceleration;
    }

    @Override
    public void setAcceleration(Vector2 acc) {
        if (acc == null) throw new NullPointerException("Acceleration is null");
        currentAcceleration=acc;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void kill() {
        isAlive = false;
    }
}
