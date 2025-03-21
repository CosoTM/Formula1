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

package it.unicam.cs.mpmgc.formula1.app;

import it.unicam.cs.mpmgc.formula1.api.configurator.FileGameConfigurator;
import it.unicam.cs.mpmgc.formula1.api.file.FileLoader;
import it.unicam.cs.mpmgc.formula1.api.simulation.GameSimulation;

import java.io.File;

public class ConsoleApp {
    public static void main(String[] args) {
        File f = FileLoader.load("raceConfigTest1.txt");
        FileGameConfigurator conf = new FileGameConfigurator(f);
        GameSimulation sim = (GameSimulation) conf.configure(1);
        try {
            sim.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
