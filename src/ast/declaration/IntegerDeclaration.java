package ast.declaration;

import ast.expression.aexpression.AExpression;
import ast.expression.lexpression.LExpression;

public class IntegerDeclaration extends Declaration {

    private String identifier;

    public IntegerDeclaration(String id) {
        this.identifier = id;
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

    @Override
    public Declaration getFirstDeclaration() {
        return null;
    }

    @Override
    public Declaration getSecondDeclaration() {
        return null;
    }
}
