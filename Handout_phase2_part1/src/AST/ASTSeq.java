/**
 * 
 */
package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTSeq implements ASTNode {

	ASTNode lhs, rhs;

	public ASTSeq(ASTNode left, ASTNode right) {
		this.lhs = left;
		this.rhs = right;
		
	}
	@SuppressWarnings("unused")
	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		return v2;
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		lhs.compile(block, e);
		block.addinstruction("pop");
		rhs.compile(block, e);

	}

}
