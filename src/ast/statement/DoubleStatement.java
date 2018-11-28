package ast.statement;

import ast.expression.aexpression.AExpression;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class DoubleStatement extends Statement {

    private Statement firstStatement;
    private Statement secondStatement;

    public DoubleStatement(Statement firstSta, Statement secondSta) {
        this.firstStatement = firstSta;
        this.secondStatement = secondSta;
    }

    @Override
    public Statement getFirstStatement() {
        return this.firstStatement;
    }

    @Override
    public Statement getSecondStatement() {
        return this.secondStatement;
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
