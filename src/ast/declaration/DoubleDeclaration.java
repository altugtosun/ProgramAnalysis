package ast.declaration;

import ast.expression.aexpression.AExpression;
import ast.expression.lexpression.LExpression;

public class DoubleDeclaration extends Declaration {

    private Declaration firstDeclaration;
    private Declaration secondDeclaration;

    public DoubleDeclaration(Declaration firstDec, Declaration secondDec) {
        this.firstDeclaration = firstDec;
        this.secondDeclaration = secondDec;
    }

    @Override
    public Declaration getFirstDeclaration() {
        return this.firstDeclaration;
    }

    @Override
    public Declaration getSecondDeclaration() {
        return this.secondDeclaration;
    }

    @Override
    public String getIdentifier() {
        return null;
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
}
