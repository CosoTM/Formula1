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

import it.unicam.cs.mpmgc.formula1.api.entity.CarEntity;
import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TileTrack implements Track{
    public final List<List<Tile>> track;

    public TileTrack(List<List<Tile>> track) {
        // TODO: check if track is valid or throw exceptions.
        this.track = track;
    }

    public Tile getTileAtPosition(Vector2 pos){
        if(pos == null) throw new NullPointerException("Position is null");
        if(!isPositionValid(pos)) throw new IllegalArgumentException("Position " +
                "isnt valid.");
        return track.get(pos.y()).get(pos.x());
    }

    public List<Vector2> getPositionsOfTileType(Tile tileType){
        List<Vector2> positions = new ArrayList<>();
        for (int y = 0; y < track.size(); y++) {
            for (int x = 0; x < track.get(y).size(); x++) {
                Tile currentTile = track.get(y).get(x);
                if(currentTile == tileType) positions.add(new Vector2(x,y));
            }
        }
        return positions;
    }

    @Override
    public List<List<Tile>> getWholeTrack() {
        return track;
    }

    @Override
    public boolean isPositionValid(Vector2 pos) {
        if(pos == null) throw new NullPointerException("Position is null");
        if(pos.x() < 0 || pos.y() < 0)          return false;
        if(pos.y() > track.size())              return false;
        if(pos.x() > track.get(pos.y()).size()) return false;
        return true;
    }

    @Override
    public boolean isPositionInsideTrack(Vector2 pos) {
        if(pos == null) throw new NullPointerException("Position is null");
        if(!isPositionValid(pos))               return false;
        if(getTileAtPosition(pos) == Tile.WALL || getTileAtPosition(pos) == Tile.AIR) return false;
        return true;
    }

    @Override
    public boolean isEntityInsideTrack(Entity entity) {
        Vector2 entityPos = entity.getPosition();
        return isPositionInsideTrack(entityPos);
    }

    @Override
    public boolean isCarOnFinishLine(CarEntity car) {
        // TODO: finish
        return false;
    }

    private List<Tile> getTilesOnLine(Vector2 p1, Vector2 p2) {
        List<Tile> tiles = new ArrayList<>();

        // Basically just Bresenham Algorithm.
        // https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm#All_cases

        int x0 = p1.x();
        int y0 = p1.y();
        int x1 = p2.x();
        int y1 = p2.y();

        int dx = Math.abs(x1-x0);
        int sx = (x0 < x1) ? 1 : -1;
        int dy = -Math.abs(y1-y0);
        int sy = (y0 < y1) ? 1 : -1;
        int error = dx+dy;

        while(true){
            tiles.add(getTileAtPosition(new Vector2(x0, y0)));
            int e2 = 2 * error;

            if(e2 >= dy) {
                if(x0 == x1) break;
                error += dy;
                x0 += sx;
            }
            if(e2 <= dx){
                if(y0 == y1) break;
                error += dx;
                y0 += sy;
            }
        }

        return tiles;
    }
}
