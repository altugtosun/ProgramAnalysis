package ast;

import ast.expression.aexpression.AExpression;
import ast.expression.lexpression.LExpression;

public interface ProgramStep {

    String getIdentifier();
    LExpression getlExpression();
    AExpression getaExpression();
    AExpression getaExpression2();
}
