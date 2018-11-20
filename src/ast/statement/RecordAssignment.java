package ast.statement;

import ast.expression.booleanExpression.BooleanExpression;
import ast.expression.lexpression.LExpression;

public class RecordAssignment extends Statement {
    private String identifier;
    private Integer fst;
    private Integer snd;

    public RecordAssignment(String id, Integer fst, Integer snd) {
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

    public Integer getFst() {
        return this.fst;
    }

    public Integer getSnd() {
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
