package configuracao

import graph.Builder
import org.jgraph.graph.DefaultEdge
import org.jgrapht.Graph

class Configuracao {

    static Builder builder = new Builder()

    static def run(){
        //builder.build(new BufferedReader(new FileReader('src//input//relations.csv')))
        //builder.save('src//output//graph')
        Builder builder1 = builder.read('src//output//graph')
    }
}
