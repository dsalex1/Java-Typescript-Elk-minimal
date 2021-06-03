package VoronoiLayouter.main;

import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class Node {
    String id;
    double x;
    double y;
}

class Edge {
    String from;
    String to;
}

class LayoutStep {
    List<Node> graphNodes;
    List<Edge> graphEdges;
    List<Edge> delaunayEdges;
    
    List<Node> voronoiNodes;
    List<Node> voronoiEdges;
    
    List<Node> centroids;
}

public class MainClass {

    public static void main(String[] args) throws ScriptException{
        ElkNode testGraph = ElkGraphUtil.createGraph();
        testGraph.setIdentifier("root");

        ElkNode n1 = ElkGraphUtil.createNode(testGraph);
        n1.setIdentifier("n1");
        ElkNode n2 = ElkGraphUtil.createNode(testGraph);
        n2.setIdentifier("n2");

        ElkGraphUtil.createSimpleEdge(n1, n2);
        
        System.out.println("input graph: "+testGraph.getChildren()+"\n");

        
        computeLayoutSteps(testGraph);
    }

    public static List<LayoutStep> computeLayoutSteps(ElkNode graph){
        try {
    
            // getting the nashorn engine
            ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
            
            //making the javascript amd module syntax work, so we can have multiple ts files, kinda
            nashorn.eval("exports={};console = { log: print };");
            nashorn.eval(new FileReader("JavaScript/main.js"));
    
            Invocable invocable = (Invocable) nashorn;
    
            ElkNode result = (ElkNode) invocable.invokeFunction("layout", graph) ;
            
            System.out.println("resulting graph from ts: "+result.getChildren()+"\n");
    
            return null;
            
        }catch(FileNotFoundException|ScriptException|NoSuchMethodException e) {
            throw new Error(e); //rethrow as unhandled exception
        }
    }

}
