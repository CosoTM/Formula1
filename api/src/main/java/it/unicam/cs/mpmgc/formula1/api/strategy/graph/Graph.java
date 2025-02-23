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

import java.util.Set;

/**
 * Descrives every operation that a Graph with {@code GraphNode<L>} e
 * {@code GraphEdge<L>} should be able to do. The graph can be directed or
 * undirected.
 *
 * @param <L> the node label
 */
 public interface Graph<L> {

    /**
     * Returns the number of nodes.
     * @return the number of nodes.
     */
    int nodeCount();

    /**
     * Returns the number of edges.
     * @return Returns the number of edges.
     */
    int edgeCount();

    /**
     * Completely clears the graph of all nodes and edges.
     */
    void clear();

    /**
     * Returns true if the graph is directed, false otherwise.
     * @return true if the graph is directed, false otherwise
     */
    boolean isDirected();

    /**
     * Return a set of all nodes of the graph. Could be empty.
     * @return a set of all nodes of the graph. Could be empty.
     */
    Set<GraphNode<L>> getNodes();

    /**
     * Adds a node to the graph.
     *
     * @param node the node to add
     * @return true if the node was added, false if it was already in the graph.
     * @throws NullPointerException if the node is null.
     */
    boolean addNode(GraphNode<L> node);

    /**
     * Removes the node and all its edges.
     * @param node the node to remove,
     * @return true if the node was removed, false if it wasn't already in the
     * graph.
     * presente
     * @throws NullPointerException          if the node is null
     */
    boolean removeNode(GraphNode<L> node);

    /**
     * Returns true if the passed node is inside the graph, false otherwise.
     *
     * @param node the node to search.
     * @return true if the passed node is inside the graph, false otherwise.
     * @throws NullPointerException if the node is null.
     */
    boolean containsNode(GraphNode<L> node);

    /**
     * Restituisce il nodo di questo grafo avente l'etichetta passata.
     * Returns a node with the given label.
     *
     * @param label the label of the node to search,
     * @return the node in the graph with the same label, null otherwise.
     * @throws NullPointerException if the label is null.
     */
    GraphNode<L> getNodeOf(L label);

    /**
     * Gives an index associated with a specific node in the graph given the
     * label.
     *
     * @param label the label of the node to search,
     * @return the index of the node
     * @throws NullPointerException          if node is null
     * @throws IllegalArgumentException      if a node with the label doesnt
     *                                       exist in the graph.
     */
    int getNodeIndexOf(L label);

    /**
     * Gives the specific node associated with the index.
     *
     * @param i the index of the node to search.
     * @return the node at index i
     * @throws IndexOutOfBoundsException     if the node doesnt exists, or the
     *                                       index goes outside the interval of 0 and the number of nodes-1.
     */
    GraphNode<L> getNodeAtIndex(int i);

    /**
     *
     * Gets all the adjacent nodes of the passed node.
     *
     * @param node the node
     * @return all the adjacent nodes to the passed null
     * @throws IllegalArgumentException if the node doesn't exist
     * @throws NullPointerException     if the node is null
     */
    Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node);

    /**
     * Returns all the nodes linked with an ingoing edge of the passed node.
     *
     * @param node the node
     * @return all the nodes linked with an incoming edge of the passed node.
     * @throws IllegalArgumentException      if the node doesn't exist
     * @throws NullPointerException          if the node is null
     */
    Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node);

    /**
     * Return a set of all edges of the graph. Could be empty.
     *
     * @return a set of all edges of the graph. Could be empty.
     */
    Set<GraphEdge<L>> getEdges();

    /**
     * Adds an edge.
     *
     * @param edge The edge to insert.
     * @return true if the edge was inserted, false if the edge already existed.
     * @throws NullPointerException     if edge is null.
     * @throws IllegalArgumentException if one of the node the edge link is not
     *                                  in the graph
     */
    boolean addEdge(GraphEdge<L> edge);

    /**
     * Removes an edge.
     *
     * @param edge The edge to remove.
     * @return  true if the edge was removed, false if the edge already existed.
     * @throws IllegalArgumentException     if one of the node the edge link is not
     *                                      in the graph
     * @throws NullPointerException         if edge is null.
     */
    boolean removeEdge(GraphEdge<L> edge);

    /**
     * Searches for a specific edge.
     *
     * @param edge The edge to find
     * @return true if the edge is in the graph, false otherwise
     * @throws NullPointerException     if edge is null.
     * @throws IllegalArgumentException if one of the node the edge link is not
     *                                  in the graph
     */
    boolean containsEdge(GraphEdge<L> edge);

    /**
     *
     * Gets a set of all the edges connected to a ceratin node in the graph.
     *
     * @param node The node to find the connected edges.
     * @return a set of all the edges connected to a ceratin node in the graph.
     * @throws IllegalArgumentException if the node doesn't exist
     * @throws NullPointerException     if the node is null
     */
    Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node);

    /**
     * Returns a set of all the ingoing edges of on a ceratin node.
     *
     * @param node the node to find the ingoing edges
     * @return a set of all the ingoing edges of on a ceratin node.
     * @throws IllegalArgumentException if the node doesn't exist
     * @throws NullPointerException     if the node is null
     */
    Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node);


    default int size() {
        return this.nodeCount() + this.edgeCount();
    }


    default boolean isEmpty() {
        // se non ci sono nodi non ci possono essere neanche archi
        return this.nodeCount() == 0;
    }


    default int getDegreeOf(GraphNode<L> node) {
        if (!this.isDirected())
            return this.getEdgesOf(node).size();
        else
            return this.getEdgesOf(node).size()
                    + this.getIngoingEdgesOf(node).size();
    }
}

