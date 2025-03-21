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

/**
 * Lists all possible Tiles for a {@link TileTrack} and what
 * {@link TrackElement} they rappresent.
 */
public enum Tile {
    WALL('#', TrackElement.WALL),
    START('^', TrackElement.START),
    VICTORY('-', TrackElement.VICTORY),
    ROAD('.', TrackElement.ROAD),
    AIR(' ', TrackElement.AIR);

    private final char tile;
    private final TrackElement element;

    Tile(char tile, TrackElement element) {
        this.tile = tile;
        this.element = element;
    }

    public static Tile charToTile(char character){
        for (Tile tile : Tile.values())
            if (tile.tile == character) return tile;

        return null;
    }

    public static Tile trackElementToTile(TrackElement element){
        for (Tile tile : Tile.values())
            if (tile.element == element) return tile;

        return null;
    }

    public char tile(){
        return tile;
    }

    public TrackElement element() {
        return element;
    }
}
