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
import it.unicam.cs.mpmgc.formula1.api.strategy.StrategyString;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * A TileTrack uses tiles to build a Track. Each tile is a position an Entity
 * could be at.
 */
public class TileTrack implements Track<List<Tile>>{
    private final List<List<Tile>> track;

    public TileTrack(List<List<Tile>> track) {
        if(track == null) throw new NullPointerException("Track is null");
        if(track.isEmpty()) throw new IllegalArgumentException("The track is " +
                "empty.");
        this.track = track;
    }

    @Override
    public List<List<Tile>> getWholeTrack() {
        return track;
    }

    @Override
    public List<Vector2> getAllPositionsOfElement(TrackElement element) {
        if(element == null) throw new NullPointerException("Track Element is null");
        return getPositionsOfTileType(Tile.trackElementToTile(element));
    }

    @Override
    public boolean isPositionValid(Vector2 pos) {
        if(pos == null) throw new NullPointerException("Position is null");
        if(pos.x() < 0 || pos.y() < 0)            return false;
        if(pos.y() > track.size()-1)              return false;
        if(pos.x() > track.get(pos.y()).size()-1) return false;
        return true;
    }

    @Override
    public boolean isPositionInsideRoad(Vector2 pos) {
        if(pos == null) throw new NullPointerException("Position is null");
        if(!isPositionValid(pos))               return false;
        if(getTileAtPosition(pos) == Tile.WALL || getTileAtPosition(pos) == Tile.AIR) return false;
        return true;
    }

    @Override
    public boolean isEntityOnFinishLine(Entity entity) {
        List<Vector2> victories = getPositionsOfTileType(Tile.VICTORY);
        return victories.contains(entity.getPosition());
    }

    @Override
    public void putEntitiesOnStart(List<? extends Entity> entities) {
        if(entities == null) throw new NullPointerException("Entities are null");
        if(entities.isEmpty()) return;

        List<Vector2> positionOfStart = getPositionsOfTileType(Tile.START);
        if(entities.size() > positionOfStart.size()) System.out.println("There are " +
                "more entities than positions available for the start. Some " +
                "entities will not be placed.");
        for (int i = 0; i < Math.min(positionOfStart.size(), entities.size()); i++) {
            entities.get(i).setPosition(positionOfStart.get(i));
        }
    }

    @Override
    public boolean hasEntityCrashed(Vector2 start, Vector2 end) {
        if(start == null) throw new NullPointerException("Start vector is null");
        if(end == null) throw new NullPointerException("End vector is null");

        if(!isPositionInsideRoad(end)) return true;
        if(getTilesOnSegment(start, end).contains(Tile.WALL)) return true;
        return false;
    }

    private Tile getTileAtPosition(Vector2 pos){
        return track.get(pos.y()).get(pos.x());
    }

    // Gets all the position a specific type of tile in the track.
    private List<Vector2> getPositionsOfTileType(Tile tileType){
        List<Vector2> positions = new ArrayList<>();
        for (int y = 0; y < track.size(); y++) {
            for (int x = 0; x < track.get(y).size(); x++) {
                Tile currentTile = track.get(y).get(x);
                if(currentTile == tileType) positions.add(new Vector2(x,y));
            }
        }
        return positions;
    }

    // Returns all the tiles on a segment starting at p1 and ending at p2.
    private List<Tile> getTilesOnSegment(Vector2 p1, Vector2 p2) {
        List<Tile> tiles = new ArrayList<>();
        for (Vector2 vec : Vector2.getAllVecsOfSegment(p1, p2))
            tiles.add(getTileAtPosition(vec));
        return tiles;
    }
}
