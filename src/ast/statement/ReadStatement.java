package ast.statement;

import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class ReadStatement extends Statement {

    private LExpression lExpression;

    public ReadStatement(LExpression lExp) {
        this.lExpression = lExp;
    }

    public LExpression getlExpression() {
        return this.lExpression;
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
}
