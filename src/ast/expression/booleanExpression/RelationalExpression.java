package ast.expression.booleanExpression;

import ast.expression.aexpression.AExpression;

public class RelationalExpression extends BooleanExpression {

    private AExpression aExpression1;
    private AExpression aExpression2;
    private String relationalOperator;

    public RelationalExpression(AExpression aExp1, AExpression aExp2, String relOp) {
        this.aExpression1 = aExp1;
        this.aExpression2 = aExp2;
        this.relationalOperator = relOp;
    }

    public AExpression getaExpression1() {
        return this.aExpression1;
    }

    public AExpression getaExpression2() {
        return this.aExpression2;
    }

    public String getRelationalOperator() {
        return this.relationalOperator;
    }
}
