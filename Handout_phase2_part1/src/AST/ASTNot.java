package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;


public class ASTNot implements ASTNode {

	private ASTNode exp;
	

	public ASTNot(ASTNode exp) {
		this.exp = exp;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue eExp = exp.eval(e);

		if (eExp instanceof VBoolean) {
			return new VBoolean(!((VBoolean) (eExp)).getValue());
		}

		throw new ValueErrorException("Invalid argument to ~ operator");

	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		exp.compile(block, e);

		int nextFlagL1 = block.getCurrFlag();

		block.addinstruction("ifeq L" + nextFlagL1);
		block.addinstruction("sipush 0");

		int nextFlagL2 = block.getCurrFlag();

		block.addinstruction("goto L" + nextFlagL2);
		block.addinstruction("L" + nextFlagL1 + ":");
		block.addinstruction("sipush 1");
		block.addinstruction("L" + nextFlagL2 + ":");

	}

	

}
