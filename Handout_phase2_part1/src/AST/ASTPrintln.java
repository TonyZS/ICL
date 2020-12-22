package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTPrintln implements ASTNode {
	
	String value;
	
	public ASTPrintln() {
		this.value = "";
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		System.out.println();
		return new VString(value);
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		// TODO Auto-generated method stub

	}

}
