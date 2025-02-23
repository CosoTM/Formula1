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

public record GraphEdge<T>(GraphNode<T> node1, GraphNode<T> node2,
                           boolean directed) {
    public GraphEdge {
        if (node1 == null)
            throw new NullPointerException("Node1 is null");
        if (node2 == null)
            throw new NullPointerException("Nodo2 is null");
    }

    /**
     * Returns the first node of this edge or the source if the edge is directed.
     *
     * @return the first node of this edge
     */
    public GraphNode<T> getNode1() {
        return this.node1;
    }

    /**
     * Returns the second node of this edge or the destination if the edge is
     * directed.
     *
     * @return the second node of this edge
     */
    public GraphNode<T> getNode2() {
        return this.node2;
    }

    /**
     * Returns true if the edge is directed, false otherwise.
     *
     * @return true if the edge is directed, false otherwise.
     */
    public boolean isDirected() {
        return this.directed;
    }

    /*
     * Two edges are equals if they are both oriented o not oriented and if
     * they "link" the same two nodes. In the case of an non oriented graph,
     * the order in which the nodes are linked doesnt matter.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof GraphEdge))
            return false;
        GraphEdge<?> other = (GraphEdge<?>) obj;
        if (directed != other.directed)
            return false;
        if (directed) {
            if (!node1.equals(other.node1))
                return false;
            if (!node2.equals(other.node2))
                return false;
            return true;
        } else {
            if (node1.equals(other.node1) && node2.equals(other.node2))
                return true;
            if (node1.equals(other.node2) && node2.equals(other.node1))
                return true;
            return false;
        }
    }

    @Override
    public String toString() {
        if (this.directed)
            return "Edge [ " + this.node1.toString() + " --> "
                    + this.node2.toString() + " ]";
        else
            return "Edge [ " + this.node1.toString() + " -- "
                    + this.node2.toString() + " ]";
    }
}
