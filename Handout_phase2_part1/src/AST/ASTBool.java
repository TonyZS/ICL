package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTBool implements ASTNode {

	private boolean bool;

	public ASTBool(boolean bool) {
		this.bool = bool;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		return new VBoolean(bool);
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		if (bool)
			block.addinstruction("sipush 1");
		else
			block.addinstruction("sipush 0");
	}

}
