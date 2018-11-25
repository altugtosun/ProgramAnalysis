package analysis.rda;

import analysis.Analysis;
import programGraph.ControlFlowAnalysis;
import programGraph.Edge;
import programGraph.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class ReachingDefinitions implements Analysis {

    public HashMap<Integer, ArrayList<ArrayList<String>>> analysisSet;
    public ControlFlowAnalysis cfa;
    public HashMap<Integer, ArrayList<Integer>> infl;

    public ReachingDefinitions(ControlFlowAnalysis cfa) {
        this.cfa = cfa;
        initialize();
    }

    public void initialize() {
        this.analysisSet = new HashMap<>();
        this.infl = new HashMap<>();
        for(int i=0; i<cfa.nodeList.size()-1; i++) {
            ArrayList<ArrayList<String>> temp = new ArrayList<>();
            this.analysisSet.put(i, temp);
            ArrayList<Integer> temp2 = new ArrayList<>();
            this.infl.put(i, temp2);
        }
        this.analysisSet.put(-1, new ArrayList<>());
        this.infl.put(-1, new ArrayList<Integer>());

        for(Node node : cfa.nodeList) {
            for(Edge edge : cfa.edgeList) {
                if(node.equals(edge.getStartNode())) {
                    this.infl.get(node.getLabelId()).add(edge.getEndNode().getLabelId());
                }
            }
        }
    }

    @Override
    public HashMap<Integer, ArrayList<ArrayList<String>>> getAnalysisSet() {
        return this.analysisSet;
    }

    @Override
    public void updateAnalysisSet(Integer label, ArrayList<ArrayList<String>> newSol) {
        for(ArrayList<String> arrayList : newSol) {
            if(!this.analysisSet.get(label).contains(arrayList)) {
                this.analysisSet.get(label).add(arrayList);
            }
        }
    }

    @Override
    public HashMap<Integer, ArrayList<Integer>> getInfl() {
        return this.infl;
    }

    @Override
    public ArrayList<ArrayList<String>> createConstraints(Integer label) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if(label == 0) {
            for(String variable : cfa.variableList) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(variable);
                temp.add("?");
                temp.add(label.toString());
                result.add(temp);
            }
        }
        else {
            for(Edge edge : cfa.edgeList) {
                if(label == edge.getEndNode().getLabelId()) {
                    ArrayList<ArrayList<String>> temp =
                            (ArrayList<ArrayList<String>>) analysisSet.get(edge.getStartNode().getLabelId()).clone();
                    temp.removeAll(kill(edge, analysisSet.get(edge.getStartNode().getLabelId())));
                    temp.addAll(gen(edge));
                    for(ArrayList<String> arrayList : temp) {
                        if(!result.contains(arrayList)) {
                            result.add(arrayList);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<ArrayList<String>> kill(Edge edge, ArrayList<ArrayList <String>> elementRD) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if(edge.getProgramStep().getClass().getName().equals("ast.declaration.IntegerDeclaration") || edge.getProgramStep().getClass().getName().equals("ast.declaration.ArrayDeclaration")) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(edge.getProgramStep().getIdentifier());
            temp.add("?");
            temp.add("0");
            result.add(temp);
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.declaration.RecordDeclaration")) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(edge.getProgramStep().getIdentifier() + ".fst");
            temp.add("?");
            temp.add("0");
            result.add(temp);
            ArrayList<String> temp2 = new ArrayList<>();
            temp2.add(edge.getProgramStep().getIdentifier() + ".snd");
            temp2.add("?");
            temp2.add("0");
            result.add(temp2);
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.statement.RecordAssignment")) {
            for(ArrayList<String> stringArray : elementRD) {
                for(String string : stringArray) {
                    if(string.equals(edge.getProgramStep().getIdentifier() + ".fst")) {
                        result.add(stringArray);
                    }
                    if(string.equals(edge.getProgramStep().getIdentifier() + ".snd")) {
                        result.add(stringArray);
                    }
                }
            }
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.statement.Assignment") || edge.getProgramStep().getClass().getName().equals("ast.statement.ReadStatement")) {
            for(ArrayList<String> stringArray : elementRD) {
                for(String string : stringArray) {
                    if(string.equals(edge.getProgramStep().getlExpression().getIdentifier())) {
                        result.add(stringArray);
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<ArrayList<String>> gen(Edge edge) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if(edge.getProgramStep().getClass().getName().equals("ast.declaration.IntegerDeclaration") || edge.getProgramStep().getClass().getName().equals("ast.declaration.ArrayDeclaration")) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(edge.getProgramStep().getIdentifier());
            temp.add(edge.getStartNode().getLabelId().toString());
            temp.add(edge.getEndNode().getLabelId().toString());
            result.add(temp);
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.declaration.RecordDeclaration") || edge.getProgramStep().getClass().getName().equals("ast.statement.RecordAssignment")) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(edge.getProgramStep().getIdentifier() + ".fst");
            temp.add(edge.getStartNode().getLabelId().toString());
            temp.add(edge.getEndNode().getLabelId().toString());
            result.add(temp);
            ArrayList<String> temp2 = new ArrayList<>();
            temp2.add(edge.getProgramStep().getIdentifier() + ".snd");
            temp2.add(edge.getStartNode().getLabelId().toString());
            temp2.add(edge.getEndNode().getLabelId().toString());
            result.add(temp2);
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.statement.Assignment") || edge.getProgramStep().getClass().getName().equals("ast.statement.ReadStatement")) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(edge.getProgramStep().getlExpression().getIdentifier());
            temp.add(edge.getStartNode().getLabelId().toString());
            temp.add(edge.getEndNode().getLabelId().toString());
            result.add(temp);
        }
        return result;
    }

    public void print() {
        for(Integer key : analysisSet.keySet()) {
            String temp = "RD" + key.toString() + " :";
            for(int j=0; j<analysisSet.get(key).size(); j++) {
                temp += analysisSet.get(key).get(j) + " ";
            }
            System.out.println(temp);
        }
        /*for(Integer key : infl.keySet()) {
            String temp = "infl(" + key.toString() + "): ";
            for(Integer i : infl.get(key)) {
                temp += i + " ";
            }
            System.out.println(temp);
        }*/
    }
}
