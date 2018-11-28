package ast.statement;

import ast.expression.aexpression.AExpression;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class WhileStatement extends Statement {

    private BooleanExpression boolExpression;
    private Statement statement;

    public WhileStatement(BooleanExpression boolExp, Statement sta) {
        this.boolExpression = boolExp;
        this.statement = sta;
    }

    @Override
    public BooleanExpression getBoolExpression() {
        return this.boolExpression;
    }

    @Override
    public Statement getFirstStatement() {
        return this.statement;
    }

    @Override
    public Statement getSecondStatement() {
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

    @Override
    public AExpression getaExpression() {
        return null;
    }

    @Override
    public AExpression getaExpression2() {
        return null;
    }

    @Override
    public Integer getSize() {
        return null;
    }
}
