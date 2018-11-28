package programGraph;

import ast.Program;
import ast.declaration.Declaration;
import ast.expression.booleanExpression.NotExpression;
import ast.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class ControlFlowAnalysis {

    public List<Edge> edgeList;
    public List<Node> nodeList;
    public List<String> variableList;

    public ControlFlowAnalysis() {
        this.edgeList = new ArrayList<>();
        this.nodeList = new ArrayList<>();
        this.variableList = new ArrayList<>();
    }

    public void setVariableList(List<String> variableList) {
        this.variableList = variableList;
    }

    public Integer getMaximumLabel() {
        Integer max = 0;
        for(Node node : nodeList) {
            if(node.getLabelId() > max) {
                max = node.getLabelId();
            }
        }
        return max;
    }

    public Node edgeDeclaration(Declaration declaration, Node startNode, Node endNode) {
        if(declaration.getClass().getName().equals("ast.declaration.IntegerDeclaration")) {
           Edge tempEdge = new Edge(startNode, endNode, declaration);
           this.edgeList.add(tempEdge);
        }
        else if(declaration.getClass().getName().equals("ast.declaration.ArrayDeclaration")) {
            Edge tempEdge = new Edge(startNode, endNode, declaration);
            this.edgeList.add(tempEdge);
        }
        else if(declaration.getClass().getName().equals("ast.declaration.RecordDeclaration")) {
            Edge tempEdge = new Edge(startNode, endNode, declaration);
            this.edgeList.add(tempEdge);
        }
        else if(declaration.getClass().getName().equals("ast.declaration.EmptyDeclaration")) {
            //do nothing
        }
        else if(declaration.getClass().getName().equals("ast.declaration.DoubleDeclaration")) {
            Node tempNode = new Node(startNode.getLabelId()+1);
            this.nodeList.add(tempNode);
            edgeDeclaration(declaration.getFirstDeclaration(), startNode, tempNode);

            if(declaration.getSecondDeclaration().getClass().getName().equals("ast.declaration.DoubleDeclaration")) {
                Node newEnd = new Node(endNode.getLabelId()+1);
                edgeDeclaration(declaration.getSecondDeclaration(), tempNode, newEnd);
                return newEnd;
            }
            if(!declaration.getSecondDeclaration().getClass().getName().equals("ast.declaration.DoubleDeclaration")) {
                edgeDeclaration(declaration.getSecondDeclaration(), tempNode, endNode);
                return endNode;
            }
        }
        return null;
    }

    public void edgeStatement(Statement statement, Node startNode, Node endNode) {
        if(statement.getClass().getName().equals("ast.statement.EmptyStatement")) {
            //do nothing
        }
        else if(statement.getClass().getName().equals("ast.statement.Assignment")) {
            Edge tempEdge = new Edge(startNode, endNode, statement);
            this.edgeList.add(tempEdge);
        }
        else if(statement.getClass().getName().equals("ast.statement.RecordAssignment")) {
            Edge tempEdge = new Edge(startNode, endNode, statement);
            this.edgeList.add(tempEdge);
        }
        else if(statement.getClass().getName().equals("ast.statement.ReadStatement")) {
            Edge tempEdge = new Edge(startNode, endNode, statement);
            this.edgeList.add(tempEdge);
        }
        else if(statement.getClass().getName().equals("ast.statement.WriteStatement")) {
            Edge tempEdge = new Edge(startNode, endNode, statement);
            this.edgeList.add(tempEdge);
        }
        else if(statement.getClass().getName().equals("ast.statement.IfStatement")) {
            //Node tempNode = new Node(startNode.getLabelId()+1);
            Node tempNode = new Node(getMaximumLabel()+1);
            this.nodeList.add(tempNode);
            Edge tempEdge = new Edge(startNode, tempNode, statement.getBoolExpression());
            this.edgeList.add(tempEdge);
            Edge tempEdge2 = new Edge(startNode, endNode, new NotExpression(statement.getBoolExpression()));
            this.edgeList.add(tempEdge2);
            edgeStatement(statement.getFirstStatement(), tempNode, endNode);
        }
        else if(statement.getClass().getName().equals("ast.statement.IfElseStatement")) {
            //Node tempNode = new Node(startNode.getLabelId()+2);
            Node tempNode = new Node(getMaximumLabel()+1);
            this.nodeList.add(tempNode);
            Edge tempEdge = new Edge(startNode, tempNode, statement.getBoolExpression());
            this.edgeList.add(tempEdge);
            //Node tempNode2 = new Node(startNode.getLabelId()+1);
            Node tempNode2 = new Node(getMaximumLabel()+1);
            this.nodeList.add(tempNode2);
            Edge tempEdge2 = new Edge(startNode, tempNode2, new NotExpression(statement.getBoolExpression()));
            this.edgeList.add(tempEdge2);
            edgeStatement(statement.getFirstStatement(), tempNode, endNode);
            edgeStatement(statement.getSecondStatement(), tempNode2, endNode);
        }
        else if(statement.getClass().getName().equals("ast.statement.WhileStatement")) {
            Edge tempEdge = new Edge(startNode, endNode, new NotExpression(statement.getBoolExpression()));
            this.edgeList.add(tempEdge);
            //Node tempNode2 = new Node(startNode.getLabelId()+2);
            Node tempNode2 = new Node(getMaximumLabel()+1);
            this.nodeList.add(tempNode2);
            Edge tempEdge2 = new Edge(startNode, tempNode2, statement.getBoolExpression());
            this.edgeList.add(tempEdge2);
            edgeStatement(statement.getFirstStatement(), tempNode2, startNode);
        }
        else if(statement.getClass().getName().equals("ast.statement.DoubleStatement")) {
            //Node tempNode = new Node(startNode.getLabelId()+1);
            Node tempNode = new Node(getMaximumLabel()+1);
            this.nodeList.add(tempNode);
            edgeStatement(statement.getFirstStatement(), startNode, tempNode);
            edgeStatement(statement.getSecondStatement(), tempNode, endNode);
        }
    }

    public void createProgramGraph(Program program) {
        Node startNode = new Node(0);
        Node endNode = new Node(2);
        this.nodeList.add(startNode);
        endNode = edgeDeclaration(program.getDeclaration(), startNode, endNode);
        this.nodeList.add(endNode);
        Node tempNode = new Node(-1);
        edgeStatement(program.getStatement(), endNode, tempNode);
        this.nodeList.add(tempNode);
    }

    public void print() {
        for(Edge edge : this.edgeList) {
            System.out.println(edge.getStartNode().getLabelId() + "-" + edge.getProgramStep().getClass().getName() + "-" + edge.getEndNode().getLabelId());
        }
        /*for(Node node : this.nodeList) {
            System.out.println(node.getLabelId());
        }*/
    }
}
