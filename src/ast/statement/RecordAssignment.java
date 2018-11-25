package ast.statement;

import ast.expression.aexpression.AExpression;
import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class RecordAssignment extends Statement {
    private String identifier;
    private AExpression fst;
    private AExpression snd;

    public RecordAssignment(String id, AExpression fst, AExpression snd) {
        this.identifier = id;
        this.fst = fst;
        this.snd = snd;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public LExpression getlExpression() {
        return null;
    }

    @Override
    public AExpression getaExpression() {
        return this.fst;
    }

    @Override
    public AExpression getaExpression2() {
        return this.snd;
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
}
