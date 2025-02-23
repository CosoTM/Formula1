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

import it.unicam.cs.mpmgc.formula1.api.track.Tile;
import it.unicam.cs.mpmgc.formula1.api.track.TileTrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles and loads everything that has to do with the {@link TileTrack}
 * for the game simulation.
 */
public class TileTrackHandler extends ScannerFileHandler<TileTrack> {
    public TileTrackHandler(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public TileTrack handle() {
        List<List<Tile>> tileMatrix = new ArrayList<>();

        String currentLine = scanner.nextLine();
        int i = 0;
        while(!currentLine.equals("$")){
            tileMatrix.add(new ArrayList<>());
            for (int j = 0; j < currentLine.length(); j++)
                tileMatrix.get(i).add(Tile.charToTile(currentLine.charAt(j)));

            currentLine = scanner.nextLine();
            i++;
        }
        scanner.close();

        return new TileTrack(tileMatrix);
    }
}
