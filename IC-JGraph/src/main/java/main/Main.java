package main;

import graph.Builder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.List;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.EdmondsKarpMaximumFlow;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Builder builder = new Builder();
        builder.build(new BufferedReader(new FileReader("source//relations.csv")));
        //builder.save("output/graph");
        builder = builder.read("output/graph");

        Graph<String, DefaultEdge> graph = builder.getGraphHasHyperonym();

        EdmondsKarpMaximumFlow<String, DefaultEdge> edmondsKarpMaximumFlow = new EdmondsKarpMaximumFlow<>((DirectedGraph) graph);
        DepthFirstIterator<String, DefaultEdge> iterator = new DepthFirstIterator(graph);

        NaiveLcaFinder<String, DefaultEdge> naiveLcaFinder = new NaiveLcaFinder<>((DefaultDirectedGraph) graph);

        String start = iterator.next();
        String last = null;
        
        Deque deque = iterator.getStack();
        while (iterator.hasNext()) {
            last = iterator.next();
        }

        String ancestor = naiveLcaFinder.findLca(args[0], args[1]);

        List<DefaultEdge> pathe = DijkstraShortestPath.findPathBetween(graph, start, last);

    }
}
