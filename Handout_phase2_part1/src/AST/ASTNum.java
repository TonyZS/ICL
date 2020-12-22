package AST;

import compile.CodeBlock;

public class ASTNum implements ASTNode {

	int val;

	@Override
	public IValue eval(Environment<IValue> e) {
		return new VInt(val);
	}

	public ASTNum(int n) {
		val = n;
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {

		String s = "sipush " + val;
		block.addinstruction(s);

	}

}
