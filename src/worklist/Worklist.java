package worklist;

import analysis.Analysis;

import java.util.ArrayList;

public class Worklist {

    public Analysis analysisType;
    public ArrayList<Integer> w;
    public String type; //either FIFO or LIFO

    public Worklist(Analysis analysis, String type) {
        this.type = type;
        this.analysisType = analysis;
        this.w = new ArrayList<>();
        for(Integer label : this.analysisType.getAnalysisSet().keySet()) {
            this.w.add(label);
        }
    }

    public void solve() {
        while(!w.isEmpty()) {
            Integer currentIndex = extract();
            ArrayList<ArrayList<String>> newSol = analysisType.createConstraints(currentIndex);

            //NEWEST ADDITION ******************************
            int flag2 = 0;
            for(String variable : analysisType.getCfa().variableList) {
                int flag = 0;
                for(ArrayList<String> arrayList : newSol) {
                    if(arrayList.contains(variable)) {
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0) flag2 = 1;
            }
            if(flag2 == 1) continue;
            //**********************************************

            if(newSol.containsAll(analysisType.getAnalysisSet().get(currentIndex)) && newSol.size() > analysisType.getAnalysisSet().get(currentIndex).size()) {
                analysisType.updateAnalysisSet(currentIndex, newSol);
                for(Integer i : analysisType.getInfl().get(currentIndex)) {
                    insert(i);
                }
            }
        }
    }

    public void insert(Integer constraint) {
        if(type.equals("LIFO")) {
            this.w.add(0, constraint);
        }
        else if(type.equals("FIFO")) {
            this.w.add(constraint);
        }
    }

    public Integer extract() {
        Integer result = this.w.get(0);
        this.w.remove(0);
        return result;
    }

}
