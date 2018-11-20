package ast.statement;

import ast.expression.aexpression.AExpression;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class Assignment extends Statement {
    private LExpression lExpression;
    private AExpression aExpression;

    public Assignment(LExpression lExp, AExpression aExp) {
        this.lExpression = lExp;
        this.aExpression = aExp;
    }

    public LExpression getlExpression() {
        return this.lExpression;
    }

    public AExpression getaExpression() {
        return this.aExpression;
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