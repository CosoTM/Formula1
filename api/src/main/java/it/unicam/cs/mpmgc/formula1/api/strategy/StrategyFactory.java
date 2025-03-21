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

package it.unicam.cs.mpmgc.formula1.api.strategy;

import it.unicam.cs.mpmgc.formula1.api.strategy.concrete.*;

/**
 * Factory for Strategies.
 */
public final class StrategyFactory {
    /**
     * Given a {@link StrategyString} it gives out the related
     * {@link Strategy} class. If null is passed for any reason, the
     * {@link StoppedStrategy} is returned.
     * @param strat The {@link StrategyString} enum
     * @return The related {@link Strategy} class. Precisely a
     * {@link StoppedStrategy} if null.
     */
    public static Strategy buildStrategy(StrategyString strat){
        return switch (strat){
            case STOPPED_STRATEGY-> new StoppedStrategy();
            case RANDOM_STRATEGY -> new RandomStrategy();
            case BFS_STRATEGY   -> new BFSStrategy();
            case DFS_STRATEGY   -> new DFSStrategy();
            case PLAYER_STRATEGY -> new PlayerStrategy();
            case null -> new BFSStrategy();
        };

    }
}
