package programGraph;

import ast.ProgramStep;

public class Edge {

    private Node startNode;
    private Node endNode;
    private ProgramStep programStep;

    public Edge(Node startNode, Node endNode, ProgramStep programStep) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.programStep = programStep;
    }

    public Node getStartNode() {
        return this.startNode;
    }

    public Node getEndNode() {
        return this.endNode;
    }

    public ProgramStep getProgramStep() {
        return this.programStep;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public void setProgramStep(ProgramStep programStep) {
        this.programStep = programStep;
    }
}
