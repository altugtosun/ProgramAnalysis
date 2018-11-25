import analysis.dos.DetectionOfSigns;
import ast.Program;
import ast.declaration.ArrayDeclaration;
import ast.declaration.Declaration;
import ast.declaration.DoubleDeclaration;
import ast.declaration.IntegerDeclaration;
import ast.expression.aexpression.*;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.booleanExpression.RelationalExpression;
import ast.expression.lexpression.LArrayExpression;
import ast.expression.lexpression.LExpression;
import ast.expression.lexpression.LVariableExpression;
import ast.statement.*;
import programGraph.ControlFlowAnalysis;
import analysis.rda.ReachingDefinitions;
import worklist.Worklist;

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        System.out.println("Program Analysis");

        //declarations
        Declaration dec11 = new ArrayDeclaration("a", 3);
        Declaration dec12 = new IntegerDeclaration("x");
        Declaration dec1 = new DoubleDeclaration(dec11, dec12);

        LExpression exp111 = new LVariableExpression("x");
        AExpression exp112 = new AValueExpression(0);
        Statement sta11 = new Assignment(exp111, exp112);
        AExpression exp12111 = new AVariableExpression("x");
        AExpression exp12112 = new AValueExpression(3);
        BooleanExpression exp1211 = new RelationalExpression(exp12111, exp12112,"<");
        AExpression exp1212111 = new AVariableExpression("x");
        LExpression exp121211 = new LArrayExpression(exp1212111,"a");
        AExpression exp121212 = new AValueExpression(1);
        Statement sta12121 = new Assignment(exp121211, exp121212);
        AExpression exp1212211 = new AVariableExpression("x");
        AExpression exp1212212 = new AValueExpression(2);
        BooleanExpression exp121221 = new RelationalExpression(exp1212211, exp1212212, "==");
        AExpression exp12122211 = new AVariableExpression("x");
        LExpression exp1212221 = new LArrayExpression(exp12122211,"a");
        AExpression exp1212222 = new AValueExpression(3);
        Statement sta121222 = new Assignment(exp1212221, exp1212222);
        Statement sta12122 = new IfStatement(exp121221, sta121222);
        Statement sta1212 = new DoubleStatement(sta12121, sta12122);
        Statement sta121 = new WhileStatement(exp1211, sta1212);

        //assignment
        LExpression exp1221 = new LVariableExpression("x");
        AExpression exp1222 = new AValueExpression(5);
        Statement sta122 = new Assignment(exp1221, exp1222);

        //assignment and while
        Statement sta12 = new DoubleStatement(sta121, sta122);

        //main
        Statement sta1 = new DoubleStatement(sta11, sta12);

        Program testProgram = new Program(dec1, sta1);

        ControlFlowAnalysis programGraph = new ControlFlowAnalysis();
        ArrayList<String> variableList = new ArrayList<String>();
        variableList.add("x");
        variableList.add("a");
        programGraph.setVariableList(variableList);
        programGraph.createProgramGraph(testProgram);
        programGraph.print();

        ReachingDefinitions rda = new ReachingDefinitions(programGraph);
        Worklist rdaWorklist = new Worklist(rda, "LIFO");
        rdaWorklist.solve();
        rda.print();

        DetectionOfSigns ds = new DetectionOfSigns(programGraph);
        Worklist dsWorklist = new Worklist(ds, "LIFO");
        dsWorklist.solve();
        ds.print();
    }
}
