package ast.expression.lexpression;

import ast.expression.Expression;

public class LExpression extends Expression {

    protected String identifier;

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
