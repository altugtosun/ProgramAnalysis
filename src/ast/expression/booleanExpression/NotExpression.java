package ast.expression.booleanExpression;

public class NotExpression extends BooleanExpression {

    private BooleanExpression boolExpression;

    public NotExpression(BooleanExpression boolExp) {
        this.boolExpression = boolExp;
    }

    public BooleanExpression getBoolExpression() {
        return this.boolExpression;
    }
}
