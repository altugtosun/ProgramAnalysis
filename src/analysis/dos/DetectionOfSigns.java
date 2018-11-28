package analysis.dos;

import analysis.Analysis;
import ast.expression.aexpression.AExpression;
import programGraph.ControlFlowAnalysis;
import programGraph.Edge;
import programGraph.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class DetectionOfSigns implements Analysis {

    public HashMap<Integer, ArrayList<ArrayList<String>>> analysisSet;
    public ControlFlowAnalysis cfa;
    public HashMap<Integer, ArrayList<Integer>> infl;

    public DetectionOfSigns(ControlFlowAnalysis cfa) {
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
    public ControlFlowAnalysis getCfa() {
        return this.cfa;
    }

    @Override
    public ArrayList<ArrayList<String>> createConstraints(Integer label) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if(label == 0) {
            for(String variable : cfa.variableList) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(variable);
                temp.add("-");
                result.add(temp);
                ArrayList<String> temp2 = new ArrayList<>();
                temp2.add(variable);
                temp2.add("0");
                result.add(temp2);
                ArrayList<String> temp3 = new ArrayList<>();
                temp3.add(variable);
                temp3.add("+");
                result.add(temp3);
            }
        }
        else {
            for(Edge edge : cfa.edgeList) {
                if(label == edge.getEndNode().getLabelId()) {
                    ArrayList<ArrayList<String>> temp = transferFunction(edge, analysisSet.get(edge.getStartNode().getLabelId()));
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

    public ArrayList<ArrayList<String>> transferFunction(Edge edge, ArrayList<ArrayList<String>> elementDS) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if(edge.getProgramStep().getClass().getName().equals("ast.declaration.IntegerDeclaration")) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(edge.getProgramStep().getIdentifier());
            temp.add("0");
            for(ArrayList<String> arrayList : elementDS) {
                if(!arrayList.contains(edge.getProgramStep().getIdentifier())) {
                    result.add(arrayList);
                }
            }
            result.add(temp);
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.declaration.ArrayDeclaration")) {
            ArrayList<String> temp = new ArrayList<>();
            for(ArrayList<String> arrayList : elementDS) {
                if(!arrayList.contains(edge.getProgramStep().getIdentifier())) {
                    result.add(arrayList);
                }
            }
            if(edge.getProgramStep().getSize() < 0) {
                result.clear();
            }
            else {
                temp.add(edge.getProgramStep().getIdentifier());
                temp.add("0");
                result.add(temp);
            }
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.declaration.RecordDeclaration")) {
            ArrayList<String> temp = new ArrayList<>();
            for(ArrayList<String> arrayList : elementDS) {
                if(!arrayList.contains(edge.getProgramStep().getIdentifier() + ".fst") && !arrayList.contains(edge.getProgramStep().getIdentifier() + ".snd")) {
                    result.add(arrayList);
                }
            }
            temp.add(edge.getProgramStep().getIdentifier() + ".fst");
            temp.add("0");
            result.add(temp);
            ArrayList<String> temp2 = new ArrayList<>();
            temp2.add(edge.getProgramStep().getIdentifier() + ".snd");
            temp2.add("0");
            result.add(temp2);
        }
        else if(edge.getProgramStep().getClass().getName().equals("ast.statement.ReadStatement")) {
            ArrayList<String> temp = new ArrayList<>();
            for(ArrayList<String> arrayList : elementDS) {
                if(!arrayList.contains(edge.getProgramStep().getlExpression().getIdentifier())) {
                    result.add(arrayList);
                }
            }
            if(edge.getProgramStep().getlExpression().getClass().getName().equals("ast.expression.lexpression.LArrayExpression")) {
                if(signFunction(edge.getProgramStep().getlExpression().getaExpression(), elementDS).contains("-") && signFunction(edge.getProgramStep().getlExpression().getaExpression(), elementDS).size() == 1) {
                    result.clear();
                }
                else {
                    temp.add(edge.getProgramStep().getlExpression().getIdentifier());
                    temp.add("-");
                    result.add(temp);
                    ArrayList<String> temp2 = new ArrayList<>();
                    temp2.add(edge.getProgramStep().getlExpression().getIdentifier());
                    temp2.add("0");
                    result.add(temp2);
                    ArrayList<String> temp3 = new ArrayList<>();
                    temp3.add(edge.getProgramStep().getlExpression().getIdentifier());
                    temp3.add("+");
                    result.add(temp3);
                }
            }
            else {
                temp.add(edge.getProgramStep().getlExpression().getIdentifier());
                temp.add("-");
                result.add(temp);
                ArrayList<String> temp2 = new ArrayList<>();
                temp2.add(edge.getProgramStep().getlExpression().getIdentifier());
                temp2.add("0");
                result.add(temp2);
                ArrayList<String> temp3 = new ArrayList<>();
                temp3.add(edge.getProgramStep().getlExpression().getIdentifier());
                temp3.add("+");
                result.add(temp3);
            }
        }
        else if (edge.getProgramStep().getClass().getName().equals("ast.statement.WriteStatement")) {
            if(edge.getProgramStep().getaExpression().getClass().getName().equals("ast.expression.aexpression.AArrayExpression")) {
                if(signFunction(edge.getProgramStep().getaExpression().getaExpression(), elementDS).contains("-") && signFunction(edge.getProgramStep().getaExpression().getaExpression(), elementDS).size() == 1) {
                    for(ArrayList<String> arrayList : elementDS) {
                        if(!arrayList.contains(edge.getProgramStep().getaExpression().getIdentifier())) {
                            result.add(arrayList);
                        }
                    }
                    result.clear();
                }
            }
            else {
                result = elementDS;
            }
        }
        else if (edge.getProgramStep().getClass().getName().equals("ast.statement.RecordAssignment")) {
            for(ArrayList<String> arrayList : elementDS) {
                if(!arrayList.contains(edge.getProgramStep().getIdentifier() + ".fst") && !arrayList.contains(edge.getProgramStep().getIdentifier() + ".snd")) {
                    result.add(arrayList);
                }
            }
            ArrayList<String> signsFirst = signFunction(edge.getProgramStep().getaExpression(), elementDS);
            for(String sign : signsFirst) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(edge.getProgramStep().getIdentifier() + ".fst");
                temp.add(sign);
                result.add(temp);
            }
            ArrayList<String> signsSecond = signFunction(edge.getProgramStep().getaExpression2(), elementDS);
            for(String sign : signsSecond) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(edge.getProgramStep().getIdentifier() + ".snd");
                temp.add(sign);
                result.add(temp);
            }
        }
        else if (edge.getProgramStep().getClass().getName().equals("ast.statement.Assignment")) {
            if(edge.getProgramStep().getlExpression().getClass().getName().equals("ast.expression.lexpression.LArrayExpression")) {
                if(signFunction(edge.getProgramStep().getlExpression().getaExpression(), elementDS).contains("-") && signFunction(edge.getProgramStep().getlExpression().getaExpression(), elementDS).size() == 1) {
                    for(ArrayList<String> arrayList : elementDS) {
                        if(!arrayList.contains(edge.getProgramStep().getlExpression().getIdentifier())) {
                            result.add(arrayList);
                        }
                    }
                    result.clear();
                }
                else {
                    for(ArrayList<String> arrayList : elementDS) {
                        result.add(arrayList);
                    }
                    ArrayList<String> signs = signFunction(edge.getProgramStep().getaExpression(), elementDS);
                    for(String sign : signs) {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(edge.getProgramStep().getlExpression().getIdentifier());
                        temp.add(sign);
                        if(!result.contains(temp)) {
                            result.add(temp);
                        }
                    }
                }
            }
            else {
                for(ArrayList<String> arrayList : elementDS) {
                    if(!arrayList.contains(edge.getProgramStep().getlExpression().getIdentifier())) {
                        result.add(arrayList);
                    }
                }
                ArrayList<String> signs = signFunction(edge.getProgramStep().getaExpression(), elementDS);
                for(String sign : signs) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(edge.getProgramStep().getlExpression().getIdentifier());
                    temp.add(sign);
                    result.add(temp);
                }
            }

        }
        else {
            result = elementDS;
        }
        return result;
    }

    public ArrayList<String> signFunction(AExpression aExpression, ArrayList<ArrayList<String>> elementDS) {
        ArrayList<String> result = new ArrayList<>();
        if(aExpression.getClass().getName().equals("ast.expression.aexpression.AValueExpression")) {
            if(aExpression.getValue() > 0) {
                result.add("+");
            }
            else if(aExpression.getValue() < 0) {
                result.add("-");
            }
            else {
                result.add("0");
            }
        }
        else if(aExpression.getClass().getName().equals("ast.expression.aexpression.AVariableExpression")) {
            for(ArrayList<String> arrayList : elementDS) {
                if(arrayList.contains(aExpression.getIdentifier())) {
                    result.add(arrayList.get(1));
                }
            }
        }
        else if(aExpression.getClass().getName().equals("ast.expression.aexpression.AArrayExpression")) {
            if(signFunction(aExpression.getaExpression(), elementDS).contains("-") && signFunction(aExpression.getaExpression(), elementDS).size() == 1) {
                result.clear();
            }
            else {
                for(ArrayList<String> arrayList : elementDS) {
                    if(arrayList.contains(aExpression.getIdentifier())) {
                        result.add(arrayList.get(1));
                    }
                }
            }
        }
        else if(aExpression.getClass().getName().equals("ast.expression.aexpression.ARecordFirstExpression")) {
            for(ArrayList<String> arrayList : elementDS) {
                if(arrayList.contains(aExpression.getIdentifier())) {
                    result.add(arrayList.get(1));
                }
            }
        }
        else if(aExpression.getClass().getName().equals("ast.expression.aexpression.ARecordSecondExpression")) {
            for(ArrayList<String> arrayList : elementDS) {
                if(arrayList.contains(aExpression.getIdentifier())) {
                    result.add(arrayList.get(1));
                }
            }
        }
        else if(aExpression.getClass().getName().equals("ast.expression.aexpression.AArithmeticExpression")) {
            //TODO check array index
            if(aExpression.getArithmeticOperator().equals("+")) {
                result = plus(signFunction(aExpression.getaExpression(), elementDS), signFunction(aExpression.getaExpression2(), elementDS));
            }
            else if(aExpression.getArithmeticOperator().equals("-")) {
                result = minus(signFunction(aExpression.getaExpression(), elementDS), signFunction(aExpression.getaExpression2(), elementDS));
            }
            else if(aExpression.getArithmeticOperator().equals("*")) {
                result = multiply(signFunction(aExpression.getaExpression(), elementDS), signFunction(aExpression.getaExpression2(), elementDS));
            }
            else if(aExpression.getArithmeticOperator().equals("/")) {
                result = divide(signFunction(aExpression.getaExpression(), elementDS), signFunction(aExpression.getaExpression2(), elementDS));
            }
        }
        return result;
    }

    public ArrayList<String> plus(ArrayList<String> first, ArrayList<String> second) {
        ArrayList<String> result = new ArrayList<>();
        for(String firstSign : first) {
            for(String secondSign : second) {
                if(firstSign.equals("-")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("-")) result.add("-");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("-")) result.add("-");
                    }
                    else {
                        if(!result.contains("-")) result.add("-");
                        if(!result.contains("0")) result.add("0");
                        if(!result.contains("+")) result.add("+");
                    }
                }
                else if(firstSign.equals("0")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("-")) result.add("-");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else {
                        if(!result.contains("+")) result.add("+");
                    }
                }
                else {
                    if(secondSign.equals("-")) {
                        if(!result.contains("-")) result.add("-");
                        if(!result.contains("0")) result.add("0");
                        if(!result.contains("+")) result.add("+");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("+")) result.add("+");
                    }
                    else {
                        if(!result.contains("+")) result.add("+");
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> minus(ArrayList<String> first, ArrayList<String> second) {
        ArrayList<String> result = new ArrayList<>();
        for(String firstSign : first) {
            for(String secondSign : second) {
                if(firstSign.equals("-")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("-")) result.add("-");
                        if(!result.contains("0")) result.add("0");
                        if(!result.contains("+")) result.add("+");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("-")) result.add("-");
                    }
                    else {
                        if(!result.contains("-")) result.add("-");
                    }
                }
                else if(firstSign.equals("0")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("+")) result.add("+");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else {
                        if(!result.contains("-")) result.add("-");
                    }
                }
                else {
                    if(secondSign.equals("-")) {
                        if(!result.contains("+")) result.add("+");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("+")) result.add("+");
                    }
                    else {
                        if(!result.contains("-")) result.add("-");
                        if(!result.contains("0")) result.add("0");
                        if(!result.contains("+")) result.add("+");
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> multiply(ArrayList<String> first, ArrayList<String> second) {
        ArrayList<String> result = new ArrayList<>();
        for(String firstSign : first) {
            for(String secondSign : second) {
                if(firstSign.equals("-")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("+")) result.add("+");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else {
                        if(!result.contains("-")) result.add("-");
                    }
                }
                else if(firstSign.equals("0")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else {
                        if(!result.contains("0")) result.add("0");
                    }
                }
                else {
                    if(secondSign.equals("-")) {
                        if(!result.contains("-")) result.add("-");
                    }
                    else if(secondSign.equals("0")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else {
                        if(!result.contains("+")) result.add("+");
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> divide(ArrayList<String> first, ArrayList<String> second) {
        ArrayList<String> result = new ArrayList<>();
        for(String firstSign : first) {
            for(String secondSign : second) {
                if(firstSign.equals("-")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("0")) result.add("0");
                        if(!result.contains("+")) result.add("+");
                    }
                    else if(secondSign.equals("0")) {
                        //zero division
                        result.clear();
                    }
                    else {
                        if(!result.contains("-")) result.add("-");

                    }
                }
                else if(firstSign.equals("0")) {
                    if(secondSign.equals("-")) {
                        if(!result.contains("0")) result.add("0");
                    }
                    else if(secondSign.equals("0")) {
                        result.clear();
                    }
                    else {
                        if(!result.contains("0")) result.add("0");
                    }
                }
                else {
                    if(secondSign.equals("-")) {
                        if(!result.contains("-")) result.add("-");
                    }
                    else if(secondSign.equals("0")) {
                        result.clear();
                    }
                    else {
                        if(!result.contains("0")) result.add("0");
                        if(!result.contains("+")) result.add("+");
                    }
                }
            }
        }
        return result;
    }

    public void print() {
        for(Integer key : analysisSet.keySet()) {
            String temp = "DS" + key.toString() + " :";
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
