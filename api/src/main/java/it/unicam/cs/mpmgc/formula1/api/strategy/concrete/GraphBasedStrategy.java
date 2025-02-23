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

package it.unicam.cs.mpmgc.formula1.api.strategy.concrete;

import it.unicam.cs.mpmgc.formula1.api.entity.CarEntity;
import it.unicam.cs.mpmgc.formula1.api.entity.Entity;
import it.unicam.cs.mpmgc.formula1.api.simulation.SimulationInfo;
import it.unicam.cs.mpmgc.formula1.api.strategy.Strategy;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.AdjacencyMatrixDirectedGraph;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.Graph;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.GraphEdge;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.GraphNode;
import it.unicam.cs.mpmgc.formula1.api.track.Track;
import it.unicam.cs.mpmgc.formula1.api.track.TrackElement;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Describes all those Strategy that are based on a
 * {@link it.unicam.cs.mpmgc.formula1.api.strategy.graph.Graph Graph}. These
 * strategy create a graph containing all achievable positions of a car (also
 * taking into account their acceleration and speed), and then returns a
 * position to move to accordingly to its traversing algorithm.
 */
public abstract class GraphBasedStrategy implements Strategy {
    protected Graph<Vector2> positionGraph;
    protected Stack<Vector2> positionStack;

    protected GraphBasedStrategy() {
        this.positionGraph = new AdjacencyMatrixDirectedGraph<>();
        this.positionStack = new Stack<>();
    }

    @Override
    public Vector2 decideNextMove(Vector2[] possiblePositions, Entity thisEntity, SimulationInfo sim) {
        if (positionGraph.isEmpty()) createPositionGraph(possiblePositions, thisEntity.getPosition(), sim.track());
        if (positionStack.isEmpty())
            positionStack = traverse(positionGraph, thisEntity.getPosition(), sim.track().getAllPositionsOfElement(TrackElement.VICTORY));

        return positionStack.pop().substr(thisEntity.getPosition());
    }

    private void createPositionGraph(Vector2[] possiblePositions, Vector2 current, Track track){
        Stack<Vector2> positionsToCheck = new Stack<>();

        GraphNode<Vector2> currentNode = new GraphNode<>(current);
        positionGraph.addNode(currentNode);
        for (Vector2 pos: possiblePositions) {
            Vector2 currentSum = current.sum(pos);
            if(track.hasEntityCrashed(current,currentSum) || current.equals(currentSum)) continue;

            GraphNode<Vector2> newNode = new GraphNode<>(currentSum);
            if(positionGraph.addNode(newNode)){
                positionsToCheck.push(pos);
                positionGraph.addEdge(new GraphEdge<>(currentNode, newNode, true));
            }
        }

        while(!positionsToCheck.isEmpty()){
            Vector2 newPos = positionsToCheck.pop();
            Vector2[] newPossiblePositions =
                    getPossiblePositionsInsideRoad(CarEntity.getPossibleMoves(newPos), current.sum(newPos), track);
            createPositionGraph(newPossiblePositions, current.sum(newPos), track);
        }
    }


    private Vector2[] getPossiblePositionsInsideRoad(Vector2[] pos, Vector2 current, Track track){
        return Arrays
                .stream(pos)
                .filter(x->track.isPositionInsideRoad(current.sum(x)))
                .toArray(Vector2[]::new);
    }

    /**
     * Given the graph, the start position and the list of position of victory,
     * a stack of positions is returned. This stack contains all the positions
     * from first to last the algorithm as taken to arrive at a position of
     * vistory.
     * @param graph The graph of positions.
     * @param start The start positions.
     * @param victories All the positions of victory.
     * @return A stack containing positions from the start to a victory position.
     */
    protected abstract Stack<Vector2> traverse(Graph<Vector2> graph, Vector2 start, List<Vector2> victories);
}
