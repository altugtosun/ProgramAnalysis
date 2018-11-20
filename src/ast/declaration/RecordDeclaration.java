package ast.declaration;

import ast.expression.lexpression.LExpression;

public class RecordDeclaration extends Declaration {

    private String identifier;

    public RecordDeclaration(String id) {
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
    public Declaration getFirstDeclaration() {
        return null;
    }

    @Override
    public Declaration getSecondDeclaration() {
        return null;
    }
}
