package ast.expression.lexpression;

import ast.expression.aexpression.AExpression;

public class LArrayExpression extends LExpression {

    private AExpression aExpression;

    public LArrayExpression(AExpression aExp, String id) {
        this.aExpression = aExp;
        this.identifier = id;
    }

    public AExpression getaExpression() {
        return this.aExpression;
    }
}
