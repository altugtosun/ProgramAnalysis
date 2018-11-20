package ast.statement;

import ast.ProgramStep;
import ast.expression.booleanExpression.BooleanExpression;

public abstract class Statement implements ProgramStep {

    public abstract Statement getFirstStatement();

    public abstract Statement getSecondStatement();

    public abstract BooleanExpression getBoolExpression();
}
