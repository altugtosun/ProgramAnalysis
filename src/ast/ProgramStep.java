package ast;

import ast.expression.lexpression.LExpression;

public interface ProgramStep {

    public String getIdentifier();
    public LExpression getlExpression();
}
