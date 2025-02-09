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

import it.unicam.cs.mpmgc.formula1.api.entity.CarEntity;
import it.unicam.cs.mpmgc.formula1.api.simulation.SimulationInfo;
import it.unicam.cs.mpmgc.formula1.api.track.Tile;
import it.unicam.cs.mpmgc.formula1.api.track.Track;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleUserInterface implements UserInterface{
    @Override
    public void updateUI(SimulationInfo sim) {
       List<List<Character>> toPrint = sim.track().getWholeTrack()
               .stream()
               .map(r -> r.stream()
                          .map(Tile::tile)
                          .collect(Collectors.toList()))
               .collect(Collectors.toList());

        for (CarEntity car : sim.cars()) {
            Vector2 pos = car.getPosition();
            toPrint.get(pos.y()).set(pos.x(), car.getName());
        }

        printEverything(toPrint);
    }

    private void printEverything(List<List<Character>> toPrint) {
        for (List<Character> row : toPrint) {
            for (Character tile : row) {
                System.out.print(tile);
            }
            System.out.println();
        }
    }


    @Override
    public void checkForNextStep() {
        Scanner s = new Scanner(System.in);
        System.out.println("Press Enter to advance Simulation.");
        s.nextLine();
    }

    @Override
    public void checkForAutomatic() {

    }
}
