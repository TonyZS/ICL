package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTIf implements ASTNode {

	private ASTNode condition, condTrue, condFalse;

	public ASTIf(ASTNode condition, ASTNode condTrue, ASTNode condFalse) {
		this.condition = condition;
		this.condTrue = condTrue;
		this.condFalse = condFalse;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {

		if (condFalse == null)
			return ifThen(e);
		else
			return ifThenElse(e);
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		// TODO Auto-generated method stub

	}

	public IValue ifThen(Environment<IValue> e) throws ValueErrorException {
		IValue eCond = condition.eval(e);
		IValue eCondTrue = condTrue.eval(e);
		// IValue eCondFalse = condFalse.eval(e);
		if (eCond instanceof VBoolean) {
			if (((VBoolean) eCond).getValue())
				return eCondTrue;
			else return new VString("");

		}
		throw new ValueErrorException("Invalid argument to if operator");
	}

	public IValue ifThenElse(Environment<IValue> e) throws ValueErrorException {
		IValue eCond = condition.eval(e);
		IValue eCondTrue = condTrue.eval(e);
		IValue eCondFalse = condFalse.eval(e);
		if (eCond instanceof VBoolean) {
			if (((VBoolean) eCond).getValue())
				return eCondTrue;
			return eCondFalse;
		}
		throw new ValueErrorException("Invalid argument to if operator");
	}

}
