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
import it.unicam.cs.mpmgc.formula1.api.strategy.Strategy;
import it.unicam.cs.mpmgc.formula1.api.strategy.StrategyFactory;
import it.unicam.cs.mpmgc.formula1.api.strategy.StrategyString;
import it.unicam.cs.mpmgc.formula1.api.vector.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Handles and loads everything that has to do with the {@link Entity Entities}
 * of the game simulation.
 */
public class EntityHandler implements Handler<List<Entity>>{
    private final File file;

    public EntityHandler(File file) {
        this.file = file;
    }

    @Override
    public List<Entity> handle() {
        // TODO: could remove the use of "$" as a separator to make code lighter
        try {
            Scanner scanner = new Scanner(file);
            String currentLine = "";

            while(!currentLine.equals("$"))
                currentLine = scanner.nextLine();


            List<Entity> entities = new ArrayList<>();
            while(scanner.hasNextLine()){
                String[] l = scanner.nextLine().split("\\s+");
                entities.add(createCarEntity(
                        StrategyString.stringToStrategy(l[0]),
                        l[1].charAt(0)) //TODO: this fails completely if in the config file there's nothing after the bot
                );
            }
            scanner.close();

            return entities;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private CarEntity createCarEntity(StrategyString strat, char name){
        return new CarEntity(
          new Position(0,0),
          name,
          StrategyFactory.buildStrategy(strat)
        );
    }
}
