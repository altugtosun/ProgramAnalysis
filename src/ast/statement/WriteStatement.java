package ast.statement;

import ast.expression.aexpression.AExpression;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class WriteStatement extends Statement{

    private AExpression aExpression;

    public WriteStatement(AExpression aExp) {
        this.aExpression = aExp;
    }

    @Override
    public AExpression getaExpression() {
        return this.aExpression;
    }

    @Override
    public AExpression getaExpression2() {
        return null;
    }

    @Override
    public Statement getFirstStatement() {
        return null;
    }

    @Override
    public Statement getSecondStatement() {
        return null;
    }

    @Override
    public BooleanExpression getBoolExpression() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public LExpression getlExpression() {
        return null;
    }
}
