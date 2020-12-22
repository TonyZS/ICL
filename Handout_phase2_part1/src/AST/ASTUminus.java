package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTUminus implements ASTNode {

	ASTNode lhs;

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = lhs.eval(e);

		if (v1 instanceof VInt) {
			return new VInt(-(((VInt)v1).getValue()));
		} else {
			throw new ValueErrorException("Invalid argument to - operator");
		}
	}

	public ASTUminus(ASTNode l) {
		lhs = l;
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		lhs.compile(block, e);
		String s = "ineg";
		block.addinstruction(s);

	}
}
