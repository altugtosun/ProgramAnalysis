package ast.expression.aexpression;

public class AValueExpression extends AExpression {

    private Integer value;

    public AValueExpression(Integer val) {
        this.value = val;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
