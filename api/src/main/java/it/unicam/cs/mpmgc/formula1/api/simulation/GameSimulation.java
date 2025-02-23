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

package it.unicam.cs.mpmgc.formula1.api.simulation;

import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.track.Track;
import it.unicam.cs.mpmgc.formula1.api.ui.UserInterface;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a Simulation.
 */
public class GameSimulation implements Simulation{
    private final Track<?> track;
    private final UserInterface UI;
    private final List<Entity> entityList;
    private final List<Entity> aliveEntities;
    private final int stepTime;
    private boolean isOngoing;

    public GameSimulation(Track<?> track, List<Entity> entities,
                          UserInterface UI) {
        this(track, entities, UI, 1);
    }

    public GameSimulation(Track<?> track, List<Entity> entities, UserInterface UI,
                          int secondsPerStep) {
        if(track == null) throw new NullPointerException("Track is null");
        if(entities == null) throw new NullPointerException("Entities are null");
        if(UI == null) throw new NullPointerException("UI is null");

        this.track = track;
        this.UI = UI;
        entityList = new ArrayList<>(entities);
        aliveEntities = new ArrayList<>(entities);
        stepTime = secondsPerStep * 1000;

        isOngoing = true;

        track.putEntitiesOnStart(entities);
    }

    @Override
    public void start() throws InterruptedException {
        while(isOngoing){
            for (Entity entity: entityList) {
                if(!isOngoing) break;
                updateUIandStep();
                handleEntity(entity);
            }
        }
    }

    private void handleEntity(Entity entity) {
        if(!entity.isAlive()) return;
        Vector2 before = entity.getPosition();
        entity.nextMove(new SimulationInfo(track, entityList, UI));

        if(track.hasEntityCrashed(before, entity.getPosition())) handleEntityCrash(entity);
        else if(track.isEntityOnFinishLine(entity) || aliveEntities.size() == 1) handlerEntityWin(entity);
    }

    private void updateUIandStep() throws InterruptedException {
        UI.updateUI(track, aliveEntities);

        if(UI.checkForAutomatic()) Thread.sleep(stepTime);
        else UI.checkForNextStep();

    }

    private void handleEntityCrash(Entity car) {
        car.kill();
        aliveEntities.remove(car);

        UI.showAfterUpdate(car.getName() +" crashed.");
    }

    private void handlerEntityWin(Entity car) {
        isOngoing = false;
        UI.showAfterUpdate(car.getName() +" won.");

        UI.updateUI(track, aliveEntities);
    }



}
