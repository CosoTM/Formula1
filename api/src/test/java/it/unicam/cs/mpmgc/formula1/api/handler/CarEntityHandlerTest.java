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

import it.unicam.cs.mpmgc.formula1.api.entity.CarEntity;
import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.file.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarEntityHandlerTest {
    @Test
    void CheckForTileTrackExistence() throws FileNotFoundException {
        File f = FileLoader.load("raceConfigTest1.txt");
        CarEntityHandler e = new CarEntityHandler(f);
        List<Entity> entities = e.handle();

        assertNotNull(entities);
        assertNotEquals(0, entities.size());
    }

    @Test
    void CheckNumberOfEntitiesTest() throws FileNotFoundException {
        File f1 = FileLoader.load("raceConfigTest1.txt");
        CarEntityHandler e1 = new CarEntityHandler(f1);
        List<Entity> entities1 = e1.handle();

        assertEquals(5, entities1.size());

        File f2 = FileLoader.load("raceConfigTest2.txt");
        CarEntityHandler e2 = new CarEntityHandler(f2);
        List<Entity> entities2 = e2.handle();

        assertEquals(1, entities2.size());
    }

    @Test
    void TestForWrongFormatOfFileNoName() throws FileNotFoundException {
        File f = FileLoader.load("raceConfigTest3.txt");
        CarEntityHandler e = new CarEntityHandler(f);
        assertThrows(IllegalArgumentException.class, e::handle);
    }

    @Test
    void TestForWrongFormatOfFileNoStrategy() throws FileNotFoundException {
        File f = FileLoader.load("raceConfigTest4.txt");
        CarEntityHandler e = new CarEntityHandler(f);
        assertThrows(IllegalArgumentException.class, e::handle);
    }

    @Test
    void TestForWrongFormatOfFileNothing() throws FileNotFoundException {
        File f = FileLoader.load("raceConfigTest5.txt");
        CarEntityHandler e = new CarEntityHandler(f);
        assertThrows(IllegalArgumentException.class, e::handle);
    }
}
