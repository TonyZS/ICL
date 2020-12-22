package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTOr implements ASTNode {

	private ASTNode lhs, rhs;
	

	public ASTOr(ASTNode left, ASTNode right) {
		this.lhs = left;
		this.rhs = right;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {

		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		if (v1 instanceof VBoolean && v2 instanceof VBoolean)
			return new VBoolean(((VBoolean) v1).getValue() || ((VBoolean) v2).getValue());
		throw new ValueErrorException("Invalid argument to || operator");
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		lhs.compile(block, e);
		rhs.compile(block, e);
		block.addinstruction("ior");

	}

}
