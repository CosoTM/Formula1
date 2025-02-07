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

package it.unicam.cs.mpmgc.formula1.api.configurator;

import it.unicam.cs.mpmgc.formula1.api.entity.CarEntity;
import it.unicam.cs.mpmgc.formula1.api.handler.CarEntityHandler;
import it.unicam.cs.mpmgc.formula1.api.handler.TileTrackHandler;
import it.unicam.cs.mpmgc.formula1.api.simulation.GameSimulation;
import it.unicam.cs.mpmgc.formula1.api.simulation.Simulation;
import it.unicam.cs.mpmgc.formula1.api.track.Track;
import it.unicam.cs.mpmgc.formula1.api.ui.ConsoleUserInterface;

import java.io.File;
import java.util.List;

/**
 * Configures a {@link it.unicam.cs.mpmgc.formula1.api.simulation.Simulation
 * Simulation} via a file.
 */
public class FileGameConfigurator implements Configurator{

    private final File gameFile;

    public FileGameConfigurator(File gameFile) {
        this.gameFile = gameFile;
    }

    @Override
    public Simulation configure() {
        TileTrackHandler trackHandler = new TileTrackHandler(gameFile);
        Track track = trackHandler.handle();

        CarEntityHandler entityHandler = new CarEntityHandler(gameFile);
        List<CarEntity> entities = entityHandler.handle();

        ConsoleUserInterface UI = new ConsoleUserInterface();

        return new GameSimulation(track, entities, UI);
    }
}
