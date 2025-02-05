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
import it.unicam.cs.mpmgc.formula1.api.vector.Position;

import java.util.List;

/**
 *
 */
public class TileTrack implements Track{
    public final List<List<Tile>> track;

    public TileTrack(List<List<Tile>> track) {
        this.track = track;
    }

    @Override
    public List<List<Tile>> getWholeTrack() {
        return track;
    }

    @Override
    public boolean isPositionValid(Position pos) {
        if(pos.x() < 0 || pos.y() < 0)          return false;
        if(pos.y() > track.size())              return false;
        if(pos.x() > track.get(pos.y()).size()) return false;
        return true;
    }

    @Override
    public boolean isPositionInsideTrack(Position pos) {
        if(!isPositionValid(pos))                        return false;
        if(track.get(pos.y()).get(pos.x()) == Tile.WALL) return false;
        return true;
    }

    @Override
    public boolean isEntityInsideTrack(Entity entity) {
        Position entityPos = entity.getPosition();
        return isPositionInsideTrack(entityPos);
    }

}
