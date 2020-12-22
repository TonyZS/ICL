package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTWhile implements ASTNode {
	private ASTNode cond, exp;


	public ASTWhile(ASTNode cond, ASTNode exp) {
		this.cond = cond;
		this.exp = exp;
	}
	
	@SuppressWarnings("unused")
	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue eCond = cond.eval(e);
		IValue eExp = exp.eval(e);

		if (eCond instanceof VBoolean) {
			while ( ((VBoolean) eCond).getValue()) {
				eExp = exp.eval(e);
				eCond = cond.eval(e);

				if (!(eCond instanceof VBoolean))
					throw new ValueErrorException("Invalid argument to while operator");
			}
			return eCond;
		}

		throw new ValueErrorException("Invalid argument to while operator");
	}
	

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		int nextFlagL1 = block.getCurrFlag();
		int nextFlagL2 = block.getCurrFlag();

		block.addinstruction("L" + nextFlagL1 + ":");
		cond.compile(block, e);

		block.addinstruction("ifeq L" + nextFlagL2);
		exp.compile(block, e);

		block.addinstruction("pop");

		block.addinstruction("goto L" + nextFlagL1);
		block.addinstruction("L" + nextFlagL2 + ":");
		block.addinstruction("sipush 0");

	}

}


	
