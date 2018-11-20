package ast.expression.booleanExpression;

import ast.expression.aexpression.AExpression;

public class BoolOperatorExpression extends BooleanExpression {

    private AExpression aExpression1;
    private AExpression aExpression2;
    private String boolOperator;

    public BoolOperatorExpression(AExpression aExp1, AExpression aExp2, String boolOp) {
        this.aExpression1 = aExp1;
        this.aExpression2 = aExp2;
        this.boolOperator = boolOp;
    }

    public AExpression getaExpression1() {
        return this.aExpression1;
    }

    public AExpression getaExpression2() {
        return this.aExpression2;
    }

    public String getBoolOperator() {
        return this.boolOperator;
    }
}
