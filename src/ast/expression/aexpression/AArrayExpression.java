package ast.expression.aexpression;

public class AArrayExpression extends AExpression {

    private String identifier;
    private AExpression aExpression;

    public AArrayExpression(String id, AExpression aExp) {
        this.identifier = id;
        this.aExpression = aExp;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public AExpression getaExpression() {
        return this.aExpression;
    }
}
