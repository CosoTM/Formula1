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

import it.unicam.cs.mpmgc.formula1.api.strategy.graph.Graph;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.GraphNode;
import it.unicam.cs.mpmgc.formula1.api.vector.Vector2;

import java.util.*;

/**
 * A DFS Strategy is a {@link GraphBasedStrategy} that traverses the graph with
 * a DFS algorithm. It doesn't necessarily find the shortest path, but
 * will always eventually reach victory.
 */
public class DFSStrategy extends GraphBasedStrategy{
    @Override
    protected Stack<Vector2> traverse(Graph<Vector2> graph, Vector2 start, List<Vector2> victories) {
        Map<Vector2, Vector2> paths = new HashMap<>();
        List<Vector2> visited = new ArrayList<>();

        Stack<Vector2> nodeStack = new Stack<>();
        nodeStack.push(start);
        while (!nodeStack.isEmpty()){
            Vector2 v = nodeStack.pop();
            if(victories.contains(v)) {
                return findPath(paths, start, v);
            }
            if(!visited.contains(v)){
                visited.add(v);
                for (GraphNode<Vector2> node :positionGraph.getAdjacentNodesOf(new GraphNode<>(v))) {
                    if(!visited.contains(node.getLabel())) {
                        paths.put(node.getLabel(), v);
                        nodeStack.push(node.getLabel());
                    }
                }
            }
        }
        return null;
    }

    private Stack<Vector2> findPath(Map<Vector2, Vector2> paths, Vector2 start,
                                    Vector2 end){
        Stack<Vector2> path = new Stack<>();
        path.push(end);

        Vector2 current = end;
        while (current != start && current != null){
            path.push(paths.get(current));
            current = paths.get(current);
        }

        path.pop();
        return path;
    }
}
