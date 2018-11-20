package ast;

import ast.declaration.Declaration;
import ast.statement.Statement;

public class Program {
    private Declaration declaration;
    private Statement statement;

    public Program(Declaration dec, Statement sta) {
        this.declaration = dec;
        this.statement = sta;
    }

    public Declaration getDeclaration() {
        return this.declaration;
    }

    public Statement getStatement() {
        return this.statement;
    }
}
