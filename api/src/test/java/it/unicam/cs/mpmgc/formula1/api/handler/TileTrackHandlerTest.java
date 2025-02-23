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

package it.unicam.cs.mpmgc.formula1.api.handler;

import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.file.FileLoader;
import it.unicam.cs.mpmgc.formula1.api.track.Tile;
import it.unicam.cs.mpmgc.formula1.api.track.TileTrack;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TileTrackHandlerTest {

    @Test
    void CheckForTileTrackExistence() throws FileNotFoundException {
        File f = FileLoader.load("raceConfigTest1.txt");
        TileTrackHandler e = new TileTrackHandler(f);
        TileTrack tiles = e.handle();

        assertNotNull(tiles);
    }

    @Test
    void CheckForNumberOfTilesTest() throws FileNotFoundException {
        File f = FileLoader.load("raceConfigTest1.txt");
        TileTrackHandler e = new TileTrackHandler(f);
        TileTrack tiles = e.handle();

        int i=0;
        for (List<Tile> rows: tiles.getWholeTrack()) {
            assertEquals(20, rows.size());
            i++;
        }
        assertEquals(13, i);
    }
}
