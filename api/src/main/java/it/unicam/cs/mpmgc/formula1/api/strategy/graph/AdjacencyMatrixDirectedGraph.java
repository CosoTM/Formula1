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

package it.unicam.cs.mpmgc.formula1.api.strategy.graph;

import java.util.*;

// TODO: could maybe extract all node operations in a more general class,
//  because their operations are the same for directed and undirected.
public class AdjacencyMatrixDirectedGraph<T> implements Graph<T> {

    private final Map<GraphNode<T>, Integer> nodesIndex;
    private final List<List<GraphEdge<T>>> matrix;

    public AdjacencyMatrixDirectedGraph() {
        this.matrix = new ArrayList<>();
        this.nodesIndex = new HashMap<>();
    }

    @Override
    public int nodeCount() {
        return nodesIndex.size();
    }

    // TODO: rivisit
    @Override
    public int edgeCount() {
        int edges = 0;
        for (int i = 0; i < nodeCount(); i++) {
            for (int j = 0; j < nodeCount(); j++)
                if(this.matrix.get(i).get(j) != null) edges++;
        }
        return edges;
    }

    @Override
    public void clear() {
        this.matrix.clear();
        this.nodesIndex.clear();
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public Set<GraphNode<T>> getNodes() {
        return new HashSet<>(nodesIndex.keySet());
    }

    @Override
    public boolean addNode(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Node is null");

        if (containsNode(node)) return false;

        this.nodesIndex.put(node, nodeCount());

        for(List<GraphEdge<T>> edges : this.matrix) edges.add(null);

        this.matrix.add(new ArrayList<>());
        for (int i = 0; i < nodeCount(); i++)
            this.matrix.get(nodeCount()-1).add(null);

        return true;
    }

    @Override
    public boolean removeNode(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Il nodo passato come parametro è " +
                    "null.");
        if(!containsNode(node)) return false;

        int toRemoveIndex = this.nodesIndex.remove(node);

        this.matrix.remove(toRemoveIndex);
        for(List<GraphEdge<T>> edges : this.matrix) edges.remove(toRemoveIndex);

        for (Map.Entry<GraphNode<T>, Integer> entry :
                this.nodesIndex.entrySet()) {
            if(entry.getValue() > toRemoveIndex)
                this.nodesIndex.put(entry.getKey(), entry.getValue()-1);
        }
        return true;
    }

    @Override
    public boolean containsNode(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Node is null");
        return this.nodesIndex.containsKey(node);
    }

    @Override
    public GraphNode<T> getNodeOf(T label) {
        if (label == null)
            throw new  NullPointerException("Label is null");

        GraphNode<T> requestedNode = new GraphNode<>(label);

        for(GraphNode<T> checkedNode : this.nodesIndex.keySet())
            if(checkedNode.equals(requestedNode)) return checkedNode;

        return null;
    }

    @Override
    public int getNodeIndexOf(T label) {
        if (label == null)
            throw new NullPointerException("Label is null");

        GraphNode<T> node = new GraphNode<>(label);
        if(!containsNode(node))
            throw new IllegalArgumentException("The node of the label passed as" +
                    " parameter doesn't exist");

        return this.nodesIndex.get(node);
    }

    @Override
    public GraphNode<T> getNodeAtIndex(int i) {
        if(i > nodeCount()-1 || i < 0)
            throw new IndexOutOfBoundsException("The index goes beyond the " +
                    "limit of [0, node count-1]");

        for (Map.Entry<GraphNode<T>, Integer> entry : nodesIndex.entrySet())
            if(entry.getValue() == i) return entry.getKey();

        throw new IndexOutOfBoundsException("The indec doesnt correspond to any" +
                " existing node");
    }

    @Override
    public Set<GraphNode<T>> getAdjacentNodesOf(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Node is null");
        if(!containsNode(node))
            throw new IllegalArgumentException("The node doesn't exist");

        Set<GraphNode<T>> adjacentNodes = new HashSet<>();
        int nodeIndex = getNodeIndexOf(node.getLabel());

        for (GraphEdge<T> edge:matrix.get(nodeIndex))
            if(edge != null) adjacentNodes.add(edge.getNode2());

        return adjacentNodes;
    }

    @Override
    public Set<GraphNode<T>> getPredecessorNodesOf(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Node is null");
        if(!containsNode(node))
            throw new IllegalArgumentException("The node doesn't exist");

        Set<GraphNode<T>> predecessorNodes = new HashSet<>();
        int nodeIndex = getNodeIndexOf(node.getLabel());

        for (List<GraphEdge<T>> edgeList:matrix)
            if(edgeList.get(nodeIndex) != null)
                predecessorNodes.add(edgeList.get(nodeIndex).node1());

        return predecessorNodes;
    }

    @Override
    public Set<GraphEdge<T>> getEdges() {
        Set<GraphEdge<T>> edgeSet = new HashSet<>();

        for (List<GraphEdge<T>> list : matrix) {
            for (GraphEdge<T> edge: list)
                if(edge != null) edgeSet.add(edge);
        }
        return edgeSet;
    }

    @Override
    public boolean addEdge(GraphEdge<T> edge) {
        if (edge == null)
            throw new NullPointerException("Edge is null");
        if (!edge.isDirected())
            throw new IllegalArgumentException(
                    "The edge is undirected. This graph is directed.");
        if (!containsNode(edge.getNode1())
                || !containsNode(edge.getNode2()))
            throw new IllegalArgumentException(
                    "One of the nodes the edge is linked to doesn't exist.");

        if(containsEdge(edge)) return false;

        int index1 = getNodeIndexOf(edge.getNode1().getLabel());
        int index2 = getNodeIndexOf(edge.getNode2().getLabel());

        this.matrix.get(index1).set(index2, edge);

        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<T> edge) {
        if (edge == null)
            throw new NullPointerException("Edge is null");
        if (!containsNode(edge.getNode1())
                || !containsNode(edge.getNode2()))
            throw new IllegalArgumentException(
                    "One of the nodes the edge is linked to doesn't exist.");

        if(!containsEdge(edge)) return false;

        int index1 = getNodeIndexOf(edge.getNode1().getLabel());
        int index2 = getNodeIndexOf(edge.getNode2().getLabel());

        this.matrix.get(index1).set(index2, null);
        return true;
    }

    @Override
    public boolean containsEdge(GraphEdge<T> edge) {
        if (edge == null)
            throw new NullPointerException("Edge is null");
        if (!containsNode(edge.getNode1())
                || !containsNode(edge.getNode2()))
            throw new IllegalArgumentException(
                    "One of the nodes the edge is linked to doesn't exist.");

        return this.matrix
                .get(getNodeIndexOf(edge.getNode1().getLabel()))
                .get(getNodeIndexOf(edge.getNode2().getLabel())) != null;
    }

    @Override
    public Set<GraphEdge<T>> getEdgesOf(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Node is null");
        if(!containsNode(node))
            throw new IllegalArgumentException("The node doesn't exist");

        Set<GraphEdge<T>> edgesOf = new HashSet<>();
        int nodeIndex = getNodeIndexOf(node.getLabel());

        for (int j = 0; j < nodeCount(); j++) {
            GraphEdge<T> currentEdge = matrix.get(nodeIndex).get(j);
            if(currentEdge != null) edgesOf.add(currentEdge);
        }
        return edgesOf;
    }

    @Override
    public Set<GraphEdge<T>> getIngoingEdgesOf(GraphNode<T> node) {
        if (node == null)
            throw new NullPointerException("Node is null");
        if(!containsNode(node))
            throw new IllegalArgumentException("The node doesn't exist");

        Set<GraphEdge<T>> predecessorNodes = new HashSet<>();
        int nodeIndex = getNodeIndexOf(node.getLabel());

        for (List<GraphEdge<T>> edgeList:matrix)
            if(edgeList.get(nodeIndex) != null)
                predecessorNodes.add(edgeList.get(nodeIndex));

        return predecessorNodes;
    }
}
