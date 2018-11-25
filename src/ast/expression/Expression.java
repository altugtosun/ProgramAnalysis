package ast.expression;

import ast.ProgramStep;
import ast.expression.aexpression.AExpression;
import ast.expression.lexpression.LExpression;

public class Expression implements ProgramStep {

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public LExpression getlExpression() {
        return null;
    }

    @Override
    public AExpression getaExpression() {
        return null;
    }

    @Override
    public AExpression getaExpression2() {
        return null;
    }
}
