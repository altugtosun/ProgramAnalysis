package ast.expression.aexpression;

public class ARecordSecondExpression extends AExpression {

    private String identifier;

    public ARecordSecondExpression(String id) {
        this.identifier = id;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
