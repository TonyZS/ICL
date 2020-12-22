package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTSub implements ASTNode {

	ASTNode lhs, rhs;

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		if (v1 instanceof VInt && v2 instanceof VInt) {
			return new VInt((((VInt)v1).getValue()) - (((VInt)v2).getValue()));
		} else {
			throw new ValueErrorException("Invalid argument to - operator");
		}
	}

	public ASTSub(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		lhs.compile(block, e);
		rhs.compile(block, e);
		String s = "isub";
		block.addinstruction(s);

	}
}
