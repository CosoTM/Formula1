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

package it.unicam.cs.mpmgc.formula1.api.ui;

import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.track.Tile;
import it.unicam.cs.mpmgc.formula1.api.track.Track;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class ConsoleUserInterface implements UserInterface{

    private final String letterForAutomatic;
    private String lastInput = "";
    private List<List<Character>> unmodifiedTrack = null;

    private String toPrintAfterUpdate = "";


    public ConsoleUserInterface() {
        this('A');
    }

    public ConsoleUserInterface(char letterForAutomatic ) {
        // TODO: check for invalid input

        this.letterForAutomatic = String.valueOf(letterForAutomatic).toUpperCase();
    }

    @Override
    public void updateUI(Track track, List<? extends Entity> entities) {
        // TODO: check for invalid input

        trySetUnmodifiedTrack(track);
        //List<List<Character>> copy = new ArrayList<>(unmodifiedTrack);

        for (Entity entity : entities) {
            Vector2 pos = entity.getPosition();
            unmodifiedTrack.get(pos.y()).set(pos.x(), entity.getName());
        }

        printEverything(unmodifiedTrack);
    }

    @Override
    public void checkForNextStep() {
        Scanner s = new Scanner(System.in);
        System.out.println("Press Enter to advance Simulation. Press '"+letterForAutomatic+"' and " +
                "Enter to make the Simulation go automatically.");
        lastInput = s.nextLine().toUpperCase();
    }

    @Override
    public boolean checkForAutomatic() {
        return lastInput.equals(letterForAutomatic);
    }

    @Override
    public void showAfterUpdate(String string) {
        toPrintAfterUpdate += string + "\n";
    }

    private void trySetUnmodifiedTrack(Track track){
        //if (unmodifiedTrack == null)
        unmodifiedTrack = track.getWholeTrack()
                .stream()
                .map(r -> r.stream()
                        .map(Tile::tile)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private void printEverything(List<List<Character>> toPrint) {
        //System.out.println();
        for (List<Character> row : toPrint) {
            for (Character tile : row) {
                System.out.print(tile);
            }
            System.out.println();
        }
        System.out.print(toPrintAfterUpdate);
        toPrintAfterUpdate = "";
    }
}
