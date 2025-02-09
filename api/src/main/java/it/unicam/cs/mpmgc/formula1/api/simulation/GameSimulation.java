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

import it.unicam.cs.mpmgc.formula1.api.entity.CarEntity;
import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.track.Track;
import it.unicam.cs.mpmgc.formula1.api.ui.UserInterface;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.List;

public class GameSimulation implements Simulation{
    private final Track track;
    private final List<CarEntity> cars;
    private final UserInterface UI;
    private boolean automatic;
    private final int stepTime = 5000;
    private boolean isOngoing;

    public GameSimulation(Track track, List<CarEntity> cars, UserInterface UI) {
        this.track = track;
        this.cars = cars;
        this.UI = UI;
        automatic = false;
        isOngoing = true;

        track.putEntitiesOnStart(cars);
    }

    @Override
    public void step() throws InterruptedException {
        // TODO: check for user input in UI Component or check if user pressed button for automatic simulation

        UI.updateUI(new SimulationInfo(track, cars));
        while(isOngoing){
            // TODO: Something with UI
            //Thread.sleep(stepTime);

            handleCars();
        }
    }

    private void handleCars() {
        for (Entity car: getAliveEntities(cars)) {
            UI.checkForNextStep();
            UI.updateUI(new SimulationInfo(track, cars));

            Vector2 before = car.getPosition();
            car.nextMove(new SimulationInfo(track, cars));

            if(track.hasEntityCrashed(before, car.getPosition())) handleCarCrash(car);
            if(track.isEntityOnFinishLine(car)) handlerCarWin(car);
        }
    }

    private List<? extends Entity> getAliveEntities(List<? extends Entity> entities){
        return entities.stream().filter(Entity::isAlive).toList();
    }

    private void handleCarCrash(Entity car) {
        // TODO: Something with UI
        car.kill();
        System.out.println(car.getName() +"crashed.");
    }

    private void handlerCarWin(Entity car) {
        // TODO: Winning logic
        // TODO: something with UI

        isOngoing = false;
        System.out.println(car.getName() +"won.");
    }


    @Override
    public void toggleManual() {
        automatic = !automatic;
    }
}
