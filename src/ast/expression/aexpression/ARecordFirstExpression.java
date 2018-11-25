package ast.expression.aexpression;

public class ARecordFirstExpression extends AExpression {

    private String identifier;

    public ARecordFirstExpression(String id) {
        this.identifier = id;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }
}
