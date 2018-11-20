package ast.declaration;

import ast.expression.lexpression.LExpression;

public class ArrayDeclaration extends Declaration {

    private String identifier;
    private Integer size;

    public ArrayDeclaration(String id, Integer size) {
        this.identifier = id;
        this.size = size;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public LExpression getlExpression() {
        return null;
    }

    public Integer getSize() {
        return this.size;
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
