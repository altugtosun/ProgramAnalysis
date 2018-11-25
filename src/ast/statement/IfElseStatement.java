package ast.statement;

import ast.expression.aexpression.AExpression;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class IfElseStatement extends Statement {

    private BooleanExpression boolExpression;
    private Statement firstStatement;
    private Statement secondStatement;

    public IfElseStatement(BooleanExpression boolExp, Statement firstSta, Statement secondSta) {
        this.boolExpression = boolExp;
        this.firstStatement = firstSta;
        this.secondStatement = secondSta;
    }

    @Override
    public BooleanExpression getBoolExpression() {
        return this.boolExpression;
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
