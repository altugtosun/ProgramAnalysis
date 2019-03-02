import analysis.dos.DetectionOfSigns;
import ast.Program;
import ast.declaration.*;
import ast.expression.aexpression.*;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.booleanExpression.RelationalExpression;
import ast.expression.lexpression.*;
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

        System.out.println("Reaching Definitions:");
        ReachingDefinitions rda = new ReachingDefinitions(programGraph);
        Worklist rdaWorklist = new Worklist(rda, "LIFO");
        rdaWorklist.solve();
        rda.print();

        System.out.println("Detection of Signs:");
        DetectionOfSigns ds = new DetectionOfSigns(programGraph);
        Worklist dsWorklist = new Worklist(ds, "LIFO");
        dsWorklist.solve();
        ds.print();


        /*********************************************/

        //declarations
        Declaration d122 = new RecordDeclaration("r");
        Declaration d121 = new ArrayDeclaration("a", 10);
        Declaration d12 = new DoubleDeclaration(d121, d122);
        Declaration d11 = new IntegerDeclaration("x");
        Declaration d1 = new DoubleDeclaration(d11, d12);

        //write r.fst
        AExpression a1222222 = new ARecordFirstExpression("r.fst");
        Statement s1222222 = new WriteStatement(a1222222);
        //r.fst=a[x]+r.snd
        AExpression a12222212 = new ARecordSecondExpression("r.snd");
        AExpression a122222112 = new AVariableExpression("x");
        AExpression a12222211 = new AArrayExpression("a", a122222112);
        AExpression a1222221 = new AArithmeticExpression(a12222211, a12222212, "+");
        LExpression l1222221 = new LRecordFirstExpression("r.fst");
        Statement s1222221 = new Assignment(l1222221, a1222221);
        //r.fst=a[x]+r.snd and write r.fst
        Statement s122222 = new DoubleStatement(s1222221, s1222222);
        //read r.snd
        LExpression l122221 = new LRecordSecondExpression("r.snd");
        Statement s122221 = new ReadStatement(l122221);
        //read r.snd and double
        Statement s12222 = new DoubleStatement(s122221, s122222);

        //a[x]=3
        AExpression a12221 = new AValueExpression(3);
        AExpression a122211 = new AVariableExpression("x");
        LExpression l12221 = new LArrayExpression(a122211, "a");
        Statement s12221 = new Assignment(l12221, a12221);
        //a[x]=3 and double
        Statement s1222 = new DoubleStatement(s12221, s12222);

        //x=1
        AExpression a1221 = new AValueExpression(1);
        LExpression l1221 = new LVariableExpression("x");
        Statement s1221 = new Assignment(l1221, a1221);
        //x=1 and double
        Statement s122 = new DoubleStatement(s1221, s1222);

        //x=x-2
        AExpression a121222 = new AValueExpression(2);
        AExpression a121221 = new AVariableExpression("x");
        AExpression a12122 = new AArithmeticExpression(a121221, a121222, "-");
        LExpression l12122 = new LVariableExpression("x");
        Statement s12122 = new Assignment(l12122, a12122);

        //a[2]=3
        AExpression a122112 = new AValueExpression(3);
        AExpression a1221121 = new AValueExpression(2);
        LExpression l122112 = new LArrayExpression(a1221121, "a");
        Statement s122112 = new Assignment(l122112, a122112);
        //x=x-1
        AExpression a1221112 = new AValueExpression(1);
        AExpression a1221111 = new AVariableExpression("x");
        AExpression a122111 = new AArithmeticExpression(a1221111, a1221112, "-");
        LExpression l122111 = new LVariableExpression("x");
        Statement s122111 = new Assignment(l122111, a122111);
        //x=x-1 and a[2]=3
        Statement s12121 = new DoubleStatement(s122111, s122112);

        //x==2
        AExpression a12212 = new AValueExpression(2);
        AExpression a12211 = new AVariableExpression("x");
        BooleanExpression b1212 = new RelationalExpression(a12211, a12212, "==");
        Statement s1212 = new IfElseStatement(b1212, s12121, s12122);

        //x>0
        AExpression a1222 = new AValueExpression(0);
        AExpression a1211 = new AVariableExpression("x");
        BooleanExpression b121 = new RelationalExpression(a1211, a1222, ">");
        Statement s121 = new WhileStatement(b121, s1212);

        //while and double
        Statement s12 = new DoubleStatement(s121, s122);

        //x=10
        AExpression a11 = new AValueExpression(10);
        LExpression l11 = new LVariableExpression("x");
        Statement s11 = new Assignment(l11, a11);

        //x=10 and double
        Statement s1 = new DoubleStatement(s11, s12);

        Program testProgram2 = new Program(d1, s1);
        ControlFlowAnalysis programGraph2 = new ControlFlowAnalysis();
        ArrayList<String> variableList2 = new ArrayList<String>();
        variableList2.add("x");
        variableList2.add("a");
        variableList2.add("r.fst");
        variableList2.add("r.snd");
        programGraph2.setVariableList(variableList2);
        programGraph2.createProgramGraph(testProgram2);
        programGraph2.print();

        System.out.println("Reaching Definitions:");
        ReachingDefinitions rda2 = new ReachingDefinitions(programGraph2);
        Worklist rdaWorklist2 = new Worklist(rda2, "LIFO");
        rdaWorklist2.solve();
        rda2.print();

        System.out.println("Detection of Signs:");
        DetectionOfSigns ds2 = new DetectionOfSigns(programGraph2);
        Worklist dsWorklist2 = new Worklist(ds2, "LIFO");
        dsWorklist2.solve();
        ds2.print();

    }
}
