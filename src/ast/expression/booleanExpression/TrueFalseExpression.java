package ast.expression.booleanExpression;

public class TrueFalseExpression extends BooleanExpression {

    private Boolean value;

    public TrueFalseExpression(Boolean val) {
        this.value = val;
    }

    public Boolean getValue() {
        return this.value;
    }
}
