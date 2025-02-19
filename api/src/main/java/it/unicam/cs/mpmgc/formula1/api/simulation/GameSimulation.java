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

import java.util.List;

public class GameSimulation implements Simulation{
    private final Track track;
    private final UserInterface UI;
    private final List<Entity> aliveEntities;
    private final int stepTime;
    private boolean isOngoing;

    public GameSimulation(Track track, List<Entity> entities, UserInterface UI) {
        // TODO: check for invalid input
        this(track, entities, UI, 1);
    }

    public GameSimulation(Track track, List<Entity> entities, UserInterface UI,
                          int secondsPerStep) {
        // TODO: check for invalid input
        this.track = track;
        this.UI = UI;
        aliveEntities = entities;
        stepTime = secondsPerStep * 1000;

        isOngoing = true;

        track.putEntitiesOnStart(entities);
    }

    @Override
    public void start() throws InterruptedException {
        while(isOngoing && !aliveEntities.isEmpty()){
            for (Entity entity: aliveEntities) {
                updateUIandStep();
                handleEntity(entity);
            }
        }
    }

    private void handleEntity(Entity entity) {
        Vector2 before = entity.getPosition();
        entity.nextMove(new SimulationInfo(track, aliveEntities, UI));

        if(track.hasEntityCrashed(before, entity.getPosition())) handleEntityCrash(entity);
        if(track.isEntityOnFinishLine(entity) || aliveEntities.size() == 1) handlerEntityWin(entity);
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
        // TODO: Winning logic
        // TODO: something with UI

        isOngoing = false;
        UI.showAfterUpdate(car.getName() +" won.");
    }



}
