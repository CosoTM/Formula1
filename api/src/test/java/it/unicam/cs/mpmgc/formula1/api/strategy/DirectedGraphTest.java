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

import it.unicam.cs.mpmgc.formula1.api.strategy.graph.AdjacencyMatrixDirectedGraph;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.Graph;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.GraphEdge;
import it.unicam.cs.mpmgc.formula1.api.strategy.graph.GraphNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DirectedGraphTest {
    @Test
    final void testNodeCount() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertEquals(0, g.nodeCount());
        g.addNode(new GraphNode<String>("s"));
        assertEquals(1, g.nodeCount());
        g.addNode(new GraphNode<String>("u"));
        assertEquals(2, g.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertEquals(0, g.edgeCount());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(0, g.edgeCount());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true);
        g.addEdge(esx);
        assertEquals(2, g.edgeCount());
    }

    @Test
    final void testClear() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testIsDirected() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertTrue(g.isDirected());
    }

    @Test
    final void testGetNodes() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<GraphNode<String>>();
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertTrue(nodes.equals(testNodes));
        GraphNode<String> nuTestBis = new GraphNode<String>("u");
        g.addNode(nuTestBis);
        nodes = g.getNodes();
        assertTrue(nodes.equals(testNodes));
    }

    @Test
    final void testAddNode() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(ns));
        g.addNode(ns);
        assertTrue(g.containsNode(nsTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.containsNode(nuTest));
    }

    @Test
    final void testContainsNode() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.containsNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(nsTest));
        g.addNode(ns);
        assertTrue(g.containsNode(nsTest));
    }

    @Test
    final void testGetNodeOf() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNodeOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphNode<String> node = g.getNodeOf("s");
        assertEquals("s", node.getLabel());
        node = g.getNodeOf("u");
        assertEquals("u", node.getLabel());
        assertTrue(g.getNodeOf("p") == null);
    }

    @Test
    final void testGetNodeIndexOf() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNodeIndexOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.getNodeIndexOf("s") == 0);
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.getNodeIndexOf("u") == 1);
        assertTrue(g.getNodeIndexOf("s") == 0);
    }

    @Test
    final void testGetNodeAtIndex() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getNodeAtIndex(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getNodeAtIndex(1));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertTrue(nsTest.equals(g.getNodeAtIndex(0)));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getNodeAtIndex(2));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertTrue(nuTest.equals(g.getNodeAtIndex(1)));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getAdjacentNodesOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphNode<String>> adjNodes = new HashSet<GraphNode<String>>();
        assertTrue(g.getAdjacentNodesOf(ns).equals(adjNodes));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getAdjacentNodesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true);
        g.addEdge(esx);
        adjNodes.add(nxTest);
        adjNodes.add(nuTest);
        assertTrue(g.getAdjacentNodesOf(nsTest).equals(adjNodes));
        adjNodes.clear();
        adjNodes.add(nsTest);
        assertFalse(g.getAdjacentNodesOf(nxTest).equals(adjNodes));
        assertFalse(g.getAdjacentNodesOf(nuTest).equals(adjNodes));
        GraphNode<String> np = new GraphNode<String>("p");
        GraphNode<String> npTest = new GraphNode<String>("p");
        g.addNode(np);
        adjNodes.clear();
        assertTrue(g.getAdjacentNodesOf(npTest).equals(adjNodes));
    }

    @Test
    final void testGetEdges() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        GraphEdge<String> esuTest1 = new GraphEdge<String>(nu, ns, true);
        GraphEdge<String> esuTest2 = new GraphEdge<String>(ns, nu, true);
        edgesTest.add(esuTest1);
        assertFalse(g.getEdges().equals(edgesTest));
        edgesTest.clear();
        edgesTest.add(esuTest2);
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true);
        g.addEdge(exu);
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertTrue(g.getEdges().equals(edgesTest));
        g.clear();
        edgesTest.clear();
        assertTrue(g.getEdges().equals(edgesTest));
    }

    @Test
    final void testAddEdge() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(ns, nu, true)));
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(nu, ns, true)));
        g.addNode(nu);
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(ns, nu, false)));
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        assertTrue(g.addEdge(esu));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, true)));
        assertFalse(g.containsEdge(new GraphEdge<String>(nu, ns, true)));

        assertFalse(g.addEdge(new GraphEdge<String>(ns, nu, true)));
        assertTrue(g.addEdge(new GraphEdge<String>(nu, ns, true)));
    }

    @Test
    final void testContainsEdge() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.containsEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.containsEdge(new GraphEdge<String>(ns, nu, true)));
        assertThrows(IllegalArgumentException.class,
                () -> g.containsEdge(new GraphEdge<String>(nu, ns, true)));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        assertFalse(g.containsEdge(new GraphEdge<String>(ns, nu, true)));
        g.addEdge(esu);
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, true)));
    }

    @Test
    final void testGetEdgesOf() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertThrows(NullPointerException.class,
                () -> g.getEdgesOf(null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getEdgesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true);
        g.addEdge(eux);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        edgesTest.add(esu);
        edgesTest.add(esx);
        edgesTest.add(eys);
        assertFalse(g.getEdgesOf(ns).equals(edgesTest));
        edgesTest.clear();
        edgesTest.add(esu);
        edgesTest.add(esx);
        assertTrue(g.getEdgesOf(ns).equals(edgesTest));
        edgesTest.clear();
        edgesTest.add(exy);
        assertTrue(g.getEdgesOf(nx).equals(edgesTest));
        edgesTest.clear();
        assertTrue(g.getEdgesOf(nw).equals(edgesTest));
    }

    @Test
    final void testGetIngoingEdgesOf(){

    }

    @Test
    final void testAdjacencyMatrixDirectedGraph() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testSize() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertTrue(g.size() == 0);
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.size() == 1);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.size() == 2);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        assertTrue(g.size() == 3);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        assertTrue(g.size() == 4);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true);
        g.addEdge(esx);
        assertTrue(g.size() == 5);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true);
        g.addEdge(eux);
        assertTrue(g.size() == 6);
        g.addEdge(new GraphEdge<String>(nx, nu, true));
        assertTrue(g.size() == 7);
        g.clear();
        assertTrue(g.size() == 0);
    }

    @Test
    final void testIsEmpty() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testGetDegreeOf() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.getDegreeOf(ns) == 0);
        assertThrows(NullPointerException.class,
                () -> g.getDegreeOf(null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getDegreeOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true);
        g.addEdge(esx);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        GraphEdge<String> euw = new GraphEdge<String>(nu, nw, true);
        g.addEdge(euw);
        GraphNode<String> nz = new GraphNode<String>("z");
        g.addNode(nz);
        GraphEdge<String> ezy = new GraphEdge<String>(nz, ny, true);
        g.addEdge(ezy);
        assertTrue(g.getDegreeOf(ns)==3);
        assertTrue(g.getDegreeOf(nu)==3);
        assertTrue(g.getDegreeOf(nx)==3);
        assertTrue(g.getDegreeOf(ny)==3);
        assertTrue(g.getDegreeOf(nz)==1);
        assertTrue(g.getDegreeOf(nw)==1);
    }

    @Test
    void testRemoveNodeValid() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeA);
        graph.addNode(nodeB);

        // Aggiungi un arco per verificare l'effetto della rimozione del nodo
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true);
        graph.addEdge(edgeAB);

        // Rimuovi il nodo "A"
        assertTrue(graph.removeNode(nodeA));

        // Controlla che il nodo sia stato rimosso
        assertFalse(graph.containsNode(nodeA));

        // Controlla che l'arco associato sia stato rimosso
        assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(edgeAB));
    }

    @Test
    void testRemoveNodeNonExistent() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeA);

        // Prova a rimuovere un nodo non presente
        assertFalse(graph.removeNode(nodeB));
    }

    @Test
    void testRemoveNodeNull() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);

        // Prova a rimuovere un nodo null
        assertThrows(NullPointerException.class, () -> graph.removeNode(null));
    }

    @Test
    void testRemoveNodeEffectOnIndexes() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);

        // Rimuovi il nodo "B"
        assertTrue(graph.removeNode(nodeB));

        // Controlla che il nodo "B" non sia più presente
        assertFalse(graph.containsNode(nodeB));

        // Controlla che gli indici siano stati aggiornati
        assertEquals(0, graph.getNodeIndexOf("A"));
        assertEquals(1, graph.getNodeIndexOf("C"));
    }

    @Test
    void testRemoveEdgeValid() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeA);
        graph.addNode(nodeB);

        // Aggiungi un arco
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true);
        assertTrue(graph.addEdge(edgeAB));

        // Rimuovi l'arco
        assertTrue(graph.removeEdge(edgeAB));

        // Controlla che l'arco sia stato rimosso
        assertFalse(graph.containsEdge(edgeAB));
    }

    @Test
    void testRemoveEdgeNonExistent() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        // Prova a rimuovere un arco non esistente
        assertFalse(graph.removeEdge(edgeAB));
    }

    @Test
    void testRemoveEdgeNull() {
        AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();

        // Prova a rimuovere un arco null
        assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    }
}
