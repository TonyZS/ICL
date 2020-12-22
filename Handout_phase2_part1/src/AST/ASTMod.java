package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTMod implements ASTNode {
	private ASTNode lhs, rhs;

	public ASTMod(ASTNode l, ASTNode r) {
		this.lhs = l;
		this.rhs = r;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		if (v1 instanceof VInt && v2 instanceof VInt)
			return new VInt(((VInt) v1).getValue() % ((VInt) v2).getValue());

		throw new ValueErrorException("Invalid argument to % operator");
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		lhs.compile(block, e);
		rhs.compile(block, e);
		block.addinstruction("imod");

	}
}
