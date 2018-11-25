package ast.expression.aexpression;

public class AVariableExpression extends AExpression {

    private String identifier;

    public AVariableExpression(String id) {
        this.identifier = id;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
