package analysis;

import programGraph.ControlFlowAnalysis;

import java.util.ArrayList;
import java.util.HashMap;

public interface Analysis {

    HashMap<Integer, ArrayList<ArrayList<String>>> getAnalysisSet();
    void updateAnalysisSet(Integer label, ArrayList<ArrayList<String>> newSol);
    HashMap<Integer, ArrayList<Integer>> getInfl();
    ArrayList<ArrayList<String>> createConstraints(Integer label);
    ControlFlowAnalysis getCfa();
}
