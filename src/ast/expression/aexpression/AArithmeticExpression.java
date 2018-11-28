package ast.expression.aexpression;

public class AArithmeticExpression extends AExpression {

    private AExpression aExpression1;
    private AExpression aExpression2;
    private String arithmeticOperator;

    public AArithmeticExpression(AExpression aExp1, AExpression aExp2, String arithOp) {
        this.aExpression1 = aExp1;
        this.aExpression2 = aExp2;
        this.arithmeticOperator = arithOp;
    }

    @Override
    public AExpression getaExpression() {
        return this.aExpression1;
    }

    @Override
    public AExpression getaExpression2() {
        return this.aExpression2;
    }

    @Override
    public String getArithmeticOperator() {
        return this.arithmeticOperator;
    }
}
