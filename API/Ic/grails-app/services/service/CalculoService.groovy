package service

import org.jgraph.graph.DefaultEdge
import org.jgrapht.DirectedGraph
import org.jgrapht.Graph
import org.jgrapht.alg.DijkstraShortestPath
import org.jgrapht.alg.EdmondsKarpMaximumFlow
import org.jgrapht.alg.NaiveLcaFinder
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.traverse.DepthFirstIterator
import org.jgrapht.traverse.GraphIterator
import graph.Calculos

/**
 * Created by Gabriel da Rosa on 29/05/2017.
 */
class CalculoService {

    public Map calculo(Graph graph, String palavra1, String palavra2){
        Double ocorrenciaPalavra1 = graph.edgesOf(palavra1).size()
        Double ocorrenciaPalavra2 = graph.edgesOf(palavra2).size()
        Double total = graph.vertexSet().size()
        DepthFirstIterator<String, DefaultEdge> iterator = new DepthFirstIterator(graph)

        NaiveLcaFinder<String, DefaultEdge> naiveLcaFinder = new NaiveLcaFinder<>((DefaultDirectedGraph) graph)

        String start = iterator.next()
        String last = null
        while(iterator.hasNext()){
            last = iterator.next()
        }


        Deque deque = iterator.getStack()
        List<DefaultEdge> pathe = new DijkstraShortestPath<>(graph, start, last);
        String ancestor = naiveLcaFinder.findLca(palavra1, palavra2)

        Double path = null

        if (!path.isInfinite()) {
            Double simLch = Calculos.simLch(path, edmondsKarpMaximumFlow.getMaximumFlowValue())
        }

        path = new DijkstraShortestPath<>(graph, start, palavra1).getPathLength()
        Double path2 = new DijkstraShortestPath<>(graph, ancestor, palavra2).getPathLength()
        Double path3 = new DijkstraShortestPath<>(graph, start, ancestor).getPathLength()

        if (!(path.isInfinite() || path2.isInfinite() || path3.isInfinite())) {
            Double simWup = graph.Calculos.simWup(path3, path, path2);
        }

        Map ret = [
            simLchPalavra1: Calculos.simLch(pathLength, maxLength),
            simLchPalavra2: Calculos.simLch(pathLength, maxLength),
            simWup: Calculos.simWup(profundidadeDoPai, profundidadeDoFilho1, profundidadeDoFilho2),
            simResPalavra1: Calculos.simRes(ocorrenciaPalavra1, total),
            simResPalavra2: Calculos.simRes(ocorrenciaPalavra2, total),
            simLinPalavra1: Calculos.simLin(ocorrenciaPalavra1, total),
            simLinPalavra2: Calculos.simLin(ocorrenciaPalavra2, total),
            simJncPalavra1: Calculos.simJnc(ocorrenciaPalavra1, total),
            simJncPalavra2: Calculos.simJnc(ocorrenciaPalavra2, total)
        ]

        return ret
    }
}
