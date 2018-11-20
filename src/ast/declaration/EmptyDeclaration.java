package ast.declaration;

import ast.expression.lexpression.LExpression;

public class EmptyDeclaration extends Declaration {

    @Override
    public Declaration getFirstDeclaration() {
        return null;
    }

    @Override
    public Declaration getSecondDeclaration() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public LExpression getlExpression() {
        return null;
    }
}
