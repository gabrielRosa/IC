package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

public class Builder implements Serializable{

    private static final long serialVersionUID = -90886196440093121L;
    
    Graph<String, DefaultEdge> graphBeInState;
    Graph<String, DefaultEdge> graphHasHyponym;
    Graph<String, DefaultEdge> graphNearAntonym;
    Graph<String, DefaultEdge> graphNearSynonym;
    Graph<String, DefaultEdge> graphSeeAlsoWN15;
    Graph<String, DefaultEdge> graphVerbGroup;
    Graph<String, DefaultEdge> graphRgloss;
    Graph<String, DefaultEdge> graphRelatedTo;
    Graph<String, DefaultEdge> graphHasHyperonym;

    public Builder() {
        this.graphBeInState = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphHasHyponym = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphNearAntonym = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphNearSynonym = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphSeeAlsoWN15 = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphVerbGroup = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphRgloss = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphRelatedTo = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.graphHasHyperonym = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public void build(BufferedReader bufferedReader) throws IOException {
        String line = null;

        bufferedReader.readLine();//read header
        
        while ((line = bufferedReader.readLine()) != null) {
            String[] words = line.split(",");

            switch (words[1]) {
                case "be_in_state":
                    this.graphBeInState.addVertex(words[0]);
                    this.graphBeInState.addVertex(words[2]);
                    this.graphBeInState.addEdge(words[0], words[2]);
                case "has_hyponym":
                    this.graphHasHyponym.addVertex(words[0]);
                    this.graphHasHyponym.addVertex(words[2]);
                    this.graphHasHyponym.addEdge(words[0], words[2]);
                case "near_antonym":
                    this.graphNearAntonym.addVertex(words[0]);
                    this.graphNearAntonym.addVertex(words[2]);
                    this.graphNearAntonym.addEdge(words[0], words[2]);
                case "near_synonym":
                    this.graphNearSynonym.addVertex(words[0]);
                    this.graphNearSynonym.addVertex(words[2]);
                    this.graphNearSynonym.addEdge(words[0], words[2]);
                case "see_also_wn15":
                    this.graphSeeAlsoWN15.addVertex(words[0]);
                    this.graphSeeAlsoWN15.addVertex(words[2]);
                    this.graphSeeAlsoWN15.addEdge(words[0], words[2]);
                case "verb_group":
                    this.graphVerbGroup.addVertex(words[0]);
                    this.graphVerbGroup.addVertex(words[2]);
                    this.graphVerbGroup.addEdge(words[0], words[2]);
                case "rgloss":
                    this.graphRgloss.addVertex(words[0]);
                    this.graphRgloss.addVertex(words[2]);
                    this.graphRgloss.addEdge(words[0], words[2]);
                case "related_to":
                    this.graphRelatedTo.addVertex(words[0]);
                    this.graphRelatedTo.addVertex(words[2]);
                    this.graphRelatedTo.addEdge(words[0], words[2]);
                case "has_hyperonym":
                    this.graphHasHyperonym.addVertex(words[0]);
                    this.graphHasHyperonym.addVertex(words[2]);
                    this.graphHasHyperonym.addEdge(words[0], words[2]);

            }
        }
    }
    
    public void save(final String output) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOutputStream;
        
        try (FileOutputStream fileOutputStream = new FileOutputStream(output)) {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        }
        
        objectOutputStream.close();
    }
    
    public Builder read(final String input) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream;
        Builder builder = null;
        
        try (FileInputStream fileInputStream = new FileInputStream(input)) {
            objectInputStream = new ObjectInputStream(fileInputStream);
            builder = (Builder) objectInputStream.readObject();
        }
        
        objectInputStream.close();
        
        return builder;
    }

    public Graph<String, DefaultEdge> getGraphBeInState() {
        return graphBeInState;
    }

    public Graph<String, DefaultEdge> getGraphHasHyponym() {
        return graphHasHyponym;
    }

    public Graph<String, DefaultEdge> getGraphNearAntonym() {
        return graphNearAntonym;
    }

    public Graph<String, DefaultEdge> getGraphNearSynonym() {
        return graphNearSynonym;
    }

    public Graph<String, DefaultEdge> getGraphSeeAlsoWN15() {
        return graphSeeAlsoWN15;
    }

    public Graph<String, DefaultEdge> getGraphVerbGroup() {
        return graphVerbGroup;
    }

    public Graph<String, DefaultEdge> getGraphRgloss() {
        return graphRgloss;
    }

    public Graph<String, DefaultEdge> getGraphRelatedTo() {
        return graphRelatedTo;
    }

    public Graph<String, DefaultEdge> getGraphHasHyperonym() {
        return graphHasHyperonym;
    }
    
    
}
