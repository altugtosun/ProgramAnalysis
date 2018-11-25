package ast.expression.aexpression;

public class ARecordSecondExpression extends AExpression {

    private String identifier;

    public ARecordSecondExpression(String id) {
        this.identifier = id;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
