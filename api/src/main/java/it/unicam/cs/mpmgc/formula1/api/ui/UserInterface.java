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
import it.unicam.cs.mpmgc.formula1.api.track.Track;

import java.util.List;

/**
 * Defines all the operations that the User Interface should have for this game.
 */
public interface UserInterface {

    /**
     * Updates UI given the Track and the list of entities.
     * @param track the Track.
     * @param entities the Entities.
     */
   void updateUI(Track track, List<? extends Entity> entities);

    /**
     * Checks for when the User wants to advance to the next step of the
     * Simulation.
     */
   void checkForNextStep();

    /**
     * Checks for when the User wants the simulation to go automatically
     */
   boolean checkForAutomatic();

    /**
     * The string passed as a parameter will be shown (according to the
     * implementation) right after the UI updates.
     * @param string the string to print after the UI Updates.
     */
   void showAfterUpdate(String string);
}
