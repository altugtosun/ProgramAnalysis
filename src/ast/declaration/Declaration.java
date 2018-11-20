package ast.declaration;

import ast.ProgramStep;

public abstract class Declaration implements ProgramStep {

    public abstract Declaration getFirstDeclaration();
    public abstract Declaration getSecondDeclaration();

}
